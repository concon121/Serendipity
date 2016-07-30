package uk.co.cbsoftware.serendipity.util.cdi.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

import uk.co.cbsoftware.serendipity.util.cdi.logging.impl.LogAsXml;

@InterceptorBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogResult {
    
    @Nonbinding
    public Class<? extends Loggable> implementation() default LogAsXml.class;
    
    @Nonbinding
    public LogLevel logLevel() default LogLevel.DEBUG;
    
}
