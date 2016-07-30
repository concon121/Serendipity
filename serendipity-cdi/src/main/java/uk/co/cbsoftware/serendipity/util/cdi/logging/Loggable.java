package uk.co.cbsoftware.serendipity.util.cdi.logging;

import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Loggable {
    
    private static final Logger log = LoggerFactory.getLogger(Loggable.class);
    
    public abstract Object log (InvocationContext ctx) throws Exception;
    
    protected void log(InvocationContext ctx, String message) {
        
        LogLevel logLevel = ctx.getMethod().getAnnotation(LogResult.class).logLevel();
        
        switch (logLevel) {
        case TRACE:
            log.trace(message);
        case DEBUG:
            log.debug(message);
        case INFO:
            log.info(message);
        case WARN:
            log.warn(message);
        case ERROR:
            log.error(message);
        default:
            log.debug(message);
        }
        
    }

}
