package uk.co.cbsoftware.serendipity.util.spring.logging.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import uk.co.cbsoftware.serendipity.util.spring.logging.LogResult;
import uk.co.cbsoftware.serendipity.util.spring.logging.Loggable;

@Component("LogResultInterceptor")
public class LogResultInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation ctx) throws Throwable {

        if (ctx.getMethod().isAnnotationPresent(LogResult.class)) {
            Class<? extends Loggable> clazz = ctx.getMethod().getAnnotation(LogResult.class).implementation();
            Loggable loggable = clazz.newInstance();
            return loggable.log(ctx);
        } else {
            return ctx.proceed();
        }

    }

}
