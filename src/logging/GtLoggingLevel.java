package logging;

import java.util.logging.Level;

/**
 *
 * @author Linda
 */
public class GtLoggingLevel extends Level {
    public static final Level GT_INFO = new GtLoggingLevel("GT_INFO", Level.INFO.intValue());
    public static final Level GT_WARNING = new GtLoggingLevel("GT_WARNING", Level.INFO.intValue());
    
    
    private GtLoggingLevel(String name, int value) {
        super(name, value);
    }
}
