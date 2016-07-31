package uk.co.cbsoftware.serendipity.util.spring.logging;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Loggable {
    
    private static final Logger log = LoggerFactory.getLogger(Loggable.class);
    
    public abstract Object log (MethodInvocation ctx) throws Throwable;
    
    protected void log(MethodInvocation ctx, String message) {
        
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
