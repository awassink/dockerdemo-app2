package app;

import static dozerdemo.utils.MarshallUtils.unmarshalStringToObject;

import javax.xml.bind.JAXBException;

import nl.marcenschede.dozerdemo.melding.v1_0.CreditRequestAvailable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by marc on 15/01/15.
 */
@Component
@Transactional
public class BerichtOntvanger {

    @Autowired
    CreditRequestRepositoryHandler creditRequestRepositoryHandler;

    @ServiceActivator(inputChannel = "BerichtIn")
    public void verwerker(Message<String> msg) throws JAXBException {

        CreditRequestAvailable creditRequestAvailable = unmarshalMessage(msg);

        creditRequestRepositoryHandler.markAsInBehandeling(creditRequestAvailable.getId());
        waitSomeMoments();
        creditRequestRepositoryHandler.processCreditRequest(creditRequestAvailable.getId());
    }

    private void waitSomeMoments() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // Put the interrupt back on the Thread and go on
            Thread.currentThread().interrupt();
        }
    }

    private CreditRequestAvailable unmarshalMessage(final Message<String> msg) throws JAXBException {
        System.out.println("Bericht ontvangen: " + msg);

        CreditRequestAvailable creditRequestAvailable =
                (CreditRequestAvailable) unmarshalStringToObject(msg.getPayload(), CreditRequestAvailable.class);
        System.out.println("Bericht nl.marcenschede.dozerdemo.datadefs.creditRequestAvailable: " + creditRequestAvailable.getId());
        return creditRequestAvailable;
    }

}
