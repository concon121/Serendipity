package uk.co.cbsoftware.serendipity.util.cdi.logging.impl;

import javax.interceptor.InvocationContext;

import uk.co.cbsoftware.serendipity.util.cdi.logging.Loggable;
import uk.co.cbsoftware.serendipity.util.json.JsonHelper;

public class LogAsJson extends Loggable {

    @Override
    public Object log(InvocationContext ctx) throws Exception {
        Object result = ctx.proceed();
        if (!Void.TYPE.equals(ctx.getMethod().getReturnType()) && result != null) {
            String json = JsonHelper.marshal(result);
            log(ctx, json);
        }
        return result;
    }

}
