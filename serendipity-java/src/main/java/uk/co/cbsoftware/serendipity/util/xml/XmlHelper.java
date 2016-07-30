package uk.co.cbsoftware.serendipity.util.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;

import net.sf.saxon.Configuration;
import net.sf.saxon.om.TreeInfo;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.xpath.XPathFactoryImpl;

public class XmlHelper {

    @SuppressWarnings("unchecked")
    public static <T> XdmNode marshal(T toMarshal) throws JAXBException, XPathException, IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            
            String className = toMarshal.getClass().getName();
            String packageName = toMarshal.getClass().getPackage().getName();

            JAXBContext jc = JAXBContext.newInstance(toMarshal.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
            
            QName qName = new QName(packageName, className);
            JAXBElement<T> root = new JAXBElement<T>(qName, (Class<T>) toMarshal.getClass(), toMarshal);
            
            marshaller.marshal(root, out);
            
            try (InputStream in = new ByteArrayInputStream(out.toByteArray())) {
                InputSource input = new InputSource(in);
                return getNode(input);
            }
        } 
    }
    
    public static XdmNode getNodeFromFile(String filePath) throws XPathException {
        InputSource input = new InputSource(XmlHelper.class.getResourceAsStream("/" + filePath));
        return getNode(input);
    }
    
    public static XdmNode getNode(InputSource input) throws XPathException {
        SAXSource saxSource = new SAXSource(input);
        Configuration config = ((XPathFactoryImpl) XPathHelper.factory).getConfiguration();
        TreeInfo document = config.buildDocumentTree(saxSource);
        return new XdmNode(document.getRootNode());
    }

    @SuppressWarnings("unchecked")
    public static <T> JAXBElement<T> unmarshal(Class<?> objectFactory, String source) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(objectFactory);
        Unmarshaller unmarshal = jc.createUnmarshaller();
        unmarshal.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
        InputStream stream = XmlHelper.class.getClassLoader().getResourceAsStream(source);
        return (stream != null) ? (JAXBElement<T>) unmarshal.unmarshal(stream) : null;
    }

}
