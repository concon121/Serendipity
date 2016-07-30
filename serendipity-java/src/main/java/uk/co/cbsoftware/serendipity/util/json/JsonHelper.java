package uk.co.cbsoftware.serendipity.util.json;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.jersey.api.json.JSONJAXBContext;
import com.sun.jersey.api.json.JSONMarshaller;
import com.sun.jersey.api.json.JSONUnmarshaller;

public class JsonHelper {
    
    private static final JsonNodeFactory factory = JsonNodeFactory.instance;

    @SuppressWarnings("unchecked")
    public static <T> T unmarshal(Class<?> toBind, String source) throws JAXBException {
        JAXBContext jc = JSONJAXBContext.newInstance(toBind);
        Unmarshaller unmarshal = jc.createUnmarshaller();
        JSONUnmarshaller unmarshalJson = JSONJAXBContext.getJSONUnmarshaller(unmarshal, jc);
        InputStream stream = JsonHelper.class.getClassLoader().getResourceAsStream(source);
        return (stream != null) ? (T) unmarshalJson.unmarshalFromJSON(stream, toBind) : null;
    }

    public static <T> String marshal(T toMarshal) throws JAXBException {
        JAXBContext jc = JSONJAXBContext.newInstance(toMarshal.getClass());
        Marshaller marshal = jc.createMarshaller();
        JSONMarshaller marshalJson = JSONJAXBContext.getJSONMarshaller(marshal, jc);
        Writer writer = new StringWriter();
        marshalJson.marshallToJSON(toMarshal, writer);
        return writer.toString();
    }
    
    
    public static String toJSON(Map<String, String> reqs) {

        ArrayNode arry = factory.arrayNode();
        
        Set<Entry<String, String>> entries = reqs.entrySet();
        
        for (Entry<String, String> entry : entries) {
            ObjectNode obj = arry.addObject();
            obj.put("key", entry.getKey());
            obj.put("value", entry.getValue());
        }

        String json = arry.toString();
        json = json.replace("\"", "\\\"");

        return json;
    }

}
