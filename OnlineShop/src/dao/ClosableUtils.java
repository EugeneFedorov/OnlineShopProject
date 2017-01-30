package dao;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by laonen on 30.01.2017.
 */
public final class ClosableUtils {
    private static final Logger log = Logger.getLogger(ClosableUtils.class.getName());

    private ClosableUtils(){
    }

    public static void silentClose(AutoCloseable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (Exception e) {
                log.log(Level.WARNING, String.format("Was unable to close: %s%n", closable), e);
            }
        }
    }
}
