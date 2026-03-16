package novafenixproject.fractalstellaris.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import org.joml.Matrix4f;
import com.mojang.math.Axis;

public class FractalSkyRenderer {
    private static final ResourceLocation NEBULA_TEXTURE = ResourceLocation.fromNamespaceAndPath("fractalstellaris", "textures/environment/fractal_stellaris_sky.png");

    public static void render(PoseStack poseStack, Matrix4f projectionMatrix, float tickDelta, Camera camera) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();

        Tesselator tesselator = Tesselator.getInstance();
        Matrix4f matrix = poseStack.last().pose();

        // --- PASSO 1: DESENHAR O FUNDO ESCURO (O "ESPAÇO") ---
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        BufferBuilder bgBuffer = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        float s = 100.0F;
        // Cor do espaço (Roxo bem escuro: 15, 5, 25)
        int br = 15, bg = 5, bb = 25, ba = 255;

        // Criamos o cubo de fundo para tampar o céu vanilla
        addCube(bgBuffer, matrix, s, br, bg, bb, ba);
        BufferUploader.drawWithShader(bgBuffer.buildOrThrow());

        // --- PASSO 2: DESENHAR AS ESTRELAS ---
        // Mudamos o Shader para que as estrelas brilhem sobre o fundo
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        BufferBuilder starBuffer = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        RandomSource random = RandomSource.create(10842L);
        for (int i = 0; i < 1500; ++i) {
            double x = random.nextFloat() * 2.0F - 1.0F;
            double y = random.nextFloat() * 2.0F - 1.0F;
            double z = random.nextFloat() * 2.0F - 1.0F;
            double d = x * x + y * y + z * z;

            if (d < 1.0D && d > 0.01D) {
                d = 1.0D / Math.sqrt(d);
                double dist = 80.0D; // Estrelas um pouco à frente do fundo
                double rx = x * dist;
                double ry = y * dist;
                double rz = z * dist;
                float starSize = random.nextFloat() * 0.12F + 0.05F;

                // Estrelas com cintilação (alpha variado)
                int alpha = (int)((random.nextFloat() * 0.7F + 0.3F) * 255);
                starBuffer.addVertex(matrix, (float)(rx - starSize), (float)ry, (float)rz).setColor(255, 255, 255, alpha);
                starBuffer.addVertex(matrix, (float)(rx + starSize), (float)ry, (float)rz).setColor(255, 255, 255, alpha);
                starBuffer.addVertex(matrix, (float)(rx + starSize), (float)(ry + starSize), (float)rz).setColor(255, 255, 255, alpha);
                starBuffer.addVertex(matrix, (float)(rx - starSize), (float)(ry + starSize), (float)rz).setColor(255, 255, 255, alpha);
            }
        }

        BufferUploader.drawWithShader(starBuffer.buildOrThrow());

        // Resetar estados
        RenderSystem.enableCull();
        RenderSystem.depthMask(true);
        RenderSystem.disableBlend();
    }

    // Método auxiliar para desenhar o cubo de fundo rapidamente
    private static void addCube(BufferBuilder buffer, Matrix4f matrix, float s, int r, int g, int b, int a) {
        // Baixo
        buffer.addVertex(matrix, -s, -s, -s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, -s, -s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, -s, s).setColor(r, g, b, a);
        buffer.addVertex(matrix, -s, -s, s).setColor(r, g, b, a);
        // Cima
        buffer.addVertex(matrix, -s, s, -s).setColor(r, g, b, a);
        buffer.addVertex(matrix, -s, s, s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, s, s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, s, -s).setColor(r, g, b, a);
        // Frente
        buffer.addVertex(matrix, -s, -s, -s).setColor(r, g, b, a);
        buffer.addVertex(matrix, -s, s, -s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, s, -s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, -s, -s).setColor(r, g, b, a);
        // Trás
        buffer.addVertex(matrix, -s, -s, s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, -s, s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, s, s).setColor(r, g, b, a);
        buffer.addVertex(matrix, -s, s, s).setColor(r, g, b, a);
        // Esquerda
        buffer.addVertex(matrix, -s, -s, s).setColor(r, g, b, a);
        buffer.addVertex(matrix, -s, s, s).setColor(r, g, b, a);
        buffer.addVertex(matrix, -s, s, -s).setColor(r, g, b, a);
        buffer.addVertex(matrix, -s, -s, -s).setColor(r, g, b, a);
        // Direita
        buffer.addVertex(matrix, s, -s, s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, -s, -s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, s, -s).setColor(r, g, b, a);
        buffer.addVertex(matrix, s, s, s).setColor(r, g, b, a);
    }
}