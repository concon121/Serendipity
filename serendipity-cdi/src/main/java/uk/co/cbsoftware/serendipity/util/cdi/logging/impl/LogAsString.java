package uk.co.cbsoftware.serendipity.util.cdi.logging.impl;

import javax.interceptor.InvocationContext;

import uk.co.cbsoftware.serendipity.util.cdi.logging.Loggable;

public class LogAsString extends Loggable {
    
    @Override
    public Object log(InvocationContext ctx) throws Exception {
        Object result = ctx.proceed();
        if (!Void.TYPE.equals(ctx.getMethod().getReturnType()) && result != null) {
            log(ctx, result.toString());
        }
        return result;
    }

}
