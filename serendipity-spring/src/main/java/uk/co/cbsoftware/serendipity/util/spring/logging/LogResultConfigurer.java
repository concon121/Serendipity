package uk.co.cbsoftware.serendipity.util.spring.logging;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("LogResultConfigurer")
public class LogResultConfigurer implements ApplicationContextAware {

    private static final String INTERCEPTOR_NAME = "LogResultInterceptor";
    private static final Logger log = LoggerFactory.getLogger(LogResultConfigurer.class);

    private ApplicationContext applicationContext;

    @PostConstruct
    public void setup() {
        
        log.info("Setting up the configurer");
        
        String [] names = applicationContext.getBeanDefinitionNames();
        
        for (String name : names) {
            
            log.info("Configuring: " + name);
            
            Object bean = applicationContext.getBean(name);
            Method[] methods = bean.getClass().getMethods();
            
            for (Method method : methods) {
                
                Annotation[] annotations = method.getAnnotations();
                
                for (Annotation annotation : annotations) {
                    
                    log.info(annotation.annotationType().getName());
                    
                    if (testAnnotation(annotation.annotationType())) {
                        ProxyFactoryBean proxy = applicationContext.getAutowireCapableBeanFactory().createBean(ProxyFactoryBean.class);
                        proxy.setTarget(bean);
                        proxy.setInterceptorNames(INTERCEPTOR_NAME);
//                        applicationContext.get
//                        applicationContext.getAutowireCapableBeanFactory().initializeBean(arg0, arg1)
                    }
                }
            }
            
        }
        
//        Map<String, Object> annotatedBeans = applicationContext.getBeansWithAnnotation(LogResult.class);
//        for (Entry<String, Object> entry : annotatedBeans.entrySet()) {
//            
//            log.info("Setting up: " + entry.getKey());
//            
//            Method[] methods = entry.getValue().getClass().getMethods();
//            for (Method method : methods) {
//                Annotation[] annotations = method.getAnnotations();
//                for (Annotation annotation : annotations) {
//                    if (testAnnotation(annotation.annotationType())) {
//                        ProxyFactoryBean proxy = applicationContext.getAutowireCapableBeanFactory().createBean(ProxyFactoryBean.class);
//                        proxy.setTarget(entry.getValue());
//                        proxy.setInterceptorNames(INTERCEPTOR_NAME);
//                    }
//                }
//            }
//        }
    }

    private <T> boolean testAnnotation(Class<T> clazz) {
        return (clazz.equals(LogResult.class));
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static String getInterceptorName() {
        return INTERCEPTOR_NAME;
    }

}
