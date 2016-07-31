package uk.co.cbsoftware.serendipity.util.logging;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import uk.co.cbsoftware.serendipity.util.spring.logging.ToLog;

public class LogResultInterceptorTest {
    
    @Test
    public void test() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("uk.co.cbsoftware.serendipity.util.spring.logging");
        assertTrue(ctx.containsBean("LogResultConfigurer"));
        assertTrue(ctx.containsBean("LogResultInterceptor"));
        assertTrue(ctx.containsBean("ToLog"));
        assertNotNull(ctx.getBean(ProxyFactoryBean.class));
        
        ToLog toLog = ctx.getBean(ToLog.class);
        toLog.logThis();
    }

}
