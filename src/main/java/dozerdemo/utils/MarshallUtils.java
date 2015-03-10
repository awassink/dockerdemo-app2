package dozerdemo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Created by marc on 15/01/15.
 */
public class MarshallUtils {

    public static String mashalObjectToString(Object messageForQueue)
            throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(messageForQueue.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        jaxbMarshaller.marshal(messageForQueue, outputStream);

        return outputStream.toString();
    }

    public static Object unmarshalStringToObject(String messageFromQueue, Class clazz)
            throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(messageFromQueue.getBytes());
        return jaxbUnmarshaller.unmarshal(inputStream);
    }

}
