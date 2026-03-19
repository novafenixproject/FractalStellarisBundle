package novafenixproject.fractalstellaris.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import novafenixproject.fractalstellaris.client.FractalSkyRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void fractalstellaris$customSky(Matrix4f frustumMatrix, Matrix4f projectionMatrix, float tickDelta, Camera camera, boolean isFoggy, Runnable fogSetter, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        // Verifica se o jogador está na sua dimensão
        if (client.level != null && client.level.dimension().location().getNamespace().equals("fractalstellaris")) {
            // Executa o seu renderizador customizado
            PoseStack poseStack = new PoseStack();
            poseStack.mulPose(frustumMatrix);
            FractalSkyRenderer.render(poseStack, projectionMatrix, tickDelta, camera);

            // Cancela a execução do método original para não renderizar Sol, Lua ou Estrelas vanilla
            ci.cancel();
        }
    }
}