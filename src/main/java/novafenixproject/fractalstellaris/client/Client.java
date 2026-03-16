package novafenixproject.fractalstellaris.client;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.phys.Vec3;
import novafenixproject.fractalstellaris.client.mixin.DimensionSpecialEffectsAccessor;
import org.jetbrains.annotations.NotNull;


public class Client implements ClientModInitializer {
    // No seu ClientModInitializer
    ResourceLocation id = ResourceLocation.fromNamespaceAndPath("fractalstellaris", "fractal_type");

    DimensionSpecialEffects spaceEffect = new DimensionSpecialEffects(Float.NaN, false, DimensionSpecialEffects.SkyType.NONE, false, false) {
        @Override
        public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float sunHeight) {
            // Cor de neblina para combinar com a nebulosa da sua foto (tom roxo/escuro)
            return new Vec3(0.02, 0.01, 0.05);
        }

        @Override
        public boolean isFoggyAt(int x, int z) {
            return false;
        }
    };
    public void onServerStarted(MinecraftServer server) {
        Global.initialize(server);
    }
    @Override
    public void onInitializeClient() {
        ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStarted);
        DimensionSpecialEffectsAccessor.getEffects().put(id, spaceEffect);
    }
}
