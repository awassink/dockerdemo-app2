package app;

import nl.marcenschede.dozerdemo.melding.v1_0.Melding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static dozerdemo.utils.MarshallUtils.unmarshalStringToObject;

/**
 * Created by marc on 15/01/15.
 */
@Component
@Transactional
public class BerichtOntvanger {

    @Autowired
    private MeldingRepository meldingRepository;

    @ServiceActivator(inputChannel = "BerichtIn")
    public void verwerker(Message<String> msg)
            throws JAXBException {
        
        System.out.println("Bericht ontvangen: " + msg);
        
        Melding melding = (Melding) unmarshalStringToObject(msg.getPayload(), Melding.class);
        System.out.println("Bericht nl.marcenschede.dozerdemo.datadefs.melding: " + melding.getResult());

        app.Melding appMelding = new app.Melding(melding);
        appMelding.setHostprocessed(getHostname());
        meldingRepository.save(appMelding);
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "Hostname not retrieved";
        }
    }
}
