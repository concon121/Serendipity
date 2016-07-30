package uk.co.cbsoftware.serendipity.util.xml;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.saxon.lib.NamespaceConstant;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.xpath.XPathFactoryImpl;

public class XPathHelper {
    
    public static final XPathFactory factory = configureXPathFactory();
    private static final Logger log = LoggerFactory.getLogger(XPathHelper.class);

    private XPathHelper() {}
    
    private static XPathFactory configureXPathFactory() {
        XPathFactory factory = null;
        try {
            System.setProperty("javax.xml.xpath.XPathFactory:" + NamespaceConstant.OBJECT_MODEL_SAXON,
                    "net.sf.saxon.xpath.XPathFactoryImpl");
            factory = XPathFactoryImpl.newInstance(NamespaceConstant.OBJECT_MODEL_SAXON);
        } catch (XPathFactoryConfigurationException e) {
            log.error("Couldnt configure xpath factory", e);
        }
        return factory;

    }
    
    @SuppressWarnings("unchecked")
    public static <T> T xpath(NodeInfo source, String query, QName returnQName) {
        T results = null;
        try {
            XPath xPath = factory.newXPath();
            XPathExpression xPathExpression = xPath.compile(query);
            results = (T) xPathExpression.evaluate(source, returnQName);
        } catch (XPathExpressionException e) {
            log.warn("Please check your xpath query is correct: {}", query);
            log.error("Failed to evaluate xpath.", e);
        }
        return results;

    }

}
