package novafenixproject.fractalstellaris.client;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Debbuger {
    private static final boolean DEBUG = true;
    private static final Logger logger = Logger.getLogger(Debbuger.class.getName());

    public static void debug(String message) {
        if (DEBUG) {
            logger.log(Level.INFO, message);
        }
    }
}
