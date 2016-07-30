package uk.co.cbsoftware.serendipity.util.cdi.logging.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import uk.co.cbsoftware.serendipity.util.cdi.logging.LogResult;
import uk.co.cbsoftware.serendipity.util.cdi.logging.Loggable;

@LogResult
@Interceptor
public class LogResultInterceptor {

    @AroundInvoke
    public Object handle(InvocationContext ctx) throws Exception {

        Class<? extends Loggable> clazz = ctx.getMethod().getAnnotation(LogResult.class).implementation();
        Loggable loggable = clazz.newInstance();
        return loggable.log(ctx);
        
    }



}
