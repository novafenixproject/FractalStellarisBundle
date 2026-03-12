package novafenixproject.fractalstellaris.client;

import net.minecraft.server.MinecraftServer;

public class Global {
    private static MinecraftServer minecraftServer;

    public static void initialize(MinecraftServer server) {
        minecraftServer = server;
    }
    //metodos getters
    public static MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }
}
