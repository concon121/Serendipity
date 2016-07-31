package uk.co.cbsoftware.serendipity.util.spring.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import uk.co.cbsoftware.serendipity.util.spring.logging.impl.LogAsXml;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogResult {
    
    public Class<? extends Loggable> implementation() default LogAsXml.class;
    
    public LogLevel logLevel() default LogLevel.DEBUG;
    
}
