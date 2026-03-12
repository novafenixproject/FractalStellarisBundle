package novafenixproject.fractalstellaris.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class Client implements ClientModInitializer {
    public void onServerStarted(MinecraftServer server) {
        Global.initialize(server);
    }
    @Override
    public void onInitializeClient() {
        ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStarted);

    }
}
