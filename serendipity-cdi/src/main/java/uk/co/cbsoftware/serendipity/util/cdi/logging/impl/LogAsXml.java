package uk.co.cbsoftware.serendipity.util.cdi.logging.impl;

import javax.interceptor.InvocationContext;

import net.sf.saxon.s9api.XdmNode;
import uk.co.cbsoftware.serendipity.util.cdi.logging.Loggable;
import uk.co.cbsoftware.serendipity.util.xml.XmlHelper;

public class LogAsXml extends Loggable {

    @Override
    public Object log(InvocationContext ctx) throws Exception {
        Object result = ctx.proceed();
        if (!Void.TYPE.equals(ctx.getMethod().getReturnType()) && result != null) {
            XdmNode xml = XmlHelper.marshal(result);
            log(ctx, xml.toString());
        }
        return result;
    }

}
