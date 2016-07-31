package uk.co.cbsoftware.serendipity.util.spring.logging.impl;

import org.aopalliance.intercept.MethodInvocation;

import net.sf.saxon.s9api.XdmNode;
import uk.co.cbsoftware.serendipity.util.spring.logging.Loggable;
import uk.co.cbsoftware.serendipity.util.xml.XmlHelper;

public class LogAsXml extends Loggable {

    @Override
    public Object log(MethodInvocation ctx) throws Throwable {
        Object result = ctx.proceed();
        if (!Void.TYPE.equals(ctx.getMethod().getReturnType()) && result != null) {
            XdmNode xml = XmlHelper.marshal(result);
            log(ctx, xml.toString());
        }
        return result;
    }

}
