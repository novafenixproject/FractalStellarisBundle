package novafenixproject.fractalstellaris.client;

import net.minecraft.server.MinecraftServer;

import java.util.logging.Logger;

public class Global {
    public static final String NAMESPACE = "fractalstellaris";
    private static MinecraftServer minecraftServer;
    public static Logger LOGGER = Logger.getLogger(NAMESPACE);

    public static void initialize(MinecraftServer server) {
        minecraftServer = server;
    }
    //metodos getters
    public static MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }
}
