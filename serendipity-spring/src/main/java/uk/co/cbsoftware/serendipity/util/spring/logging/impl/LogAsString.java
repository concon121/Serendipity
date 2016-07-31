package uk.co.cbsoftware.serendipity.util.spring.logging.impl;

import org.aopalliance.intercept.MethodInvocation;

import uk.co.cbsoftware.serendipity.util.spring.logging.Loggable;

public class LogAsString extends Loggable {
    
    @Override
    public Object log(MethodInvocation ctx) throws Throwable {
        Object result = ctx.proceed();
        if (!Void.TYPE.equals(ctx.getMethod().getReturnType()) && result != null) {
            log(ctx, result.toString());
        }
        return result;
    }

}
