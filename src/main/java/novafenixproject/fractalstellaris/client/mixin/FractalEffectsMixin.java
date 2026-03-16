package novafenixproject.fractalstellaris.client.mixin;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import novafenixproject.fractalstellaris.client.Global;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DimensionSpecialEffects.class)
public abstract class FractalEffectsMixin {

    // O segredo para o erro sumir é usar Object2ObjectMap em vez de Map
    @Shadow
    @Final
    private static Object2ObjectMap<ResourceLocation, DimensionSpecialEffects> EFFECTS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void fractalstellaris$registerCustomEffects(CallbackInfo ci) {
        // Usamos seu ResourceLocation centralizado
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Global.NAMESPACE, "fractal_type");

        // Criamos o efeito espacial
        DimensionSpecialEffects fractalEffect = new DimensionSpecialEffects(
                Float.NaN, // Sem nuvens
                false,     // Sem "chão" sólido no horizonte
                DimensionSpecialEffects.SkyType.NONE, // Desativa Sol/Lua/Estrelas vanilla
                false,     // Sem brilho forçado no lightmap
                false      // Sem luz ambiente constante
        ) {
            @Override
            public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float sunHeight) {
                // Cor da névoa (ajuste para o tom roxo/azul escuro da nebulosa)
                return new Vec3(0.02, 0.01, 0.05);
            }

            @Override
            public boolean isFoggyAt(int x, int z) {
                return false;
            }
        };

        // Agora o EFFECTS será localizado e o put funcionará
        EFFECTS.put(id, fractalEffect);
    }
}