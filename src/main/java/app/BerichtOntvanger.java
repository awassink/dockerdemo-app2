package app;

import static dozerdemo.utils.MarshallUtils.unmarshalStringToObject;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
    private CreditRequestRepository creditRequestRepository;

    @ServiceActivator(inputChannel = "BerichtIn")
    public void verwerker(Message<String> msg) throws JAXBException {

        CreditRequestAvailable creditRequestAvailable = unmarshalMessage(msg);

        CreditRequest creditRequest = creditRequestRepository.findOne(creditRequestAvailable.getId());

        markAsInBehandeling(creditRequest);

        waitSomeMoments();
        processCreditRequest(creditRequest);
    }

    private void waitSomeMoments() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Put the interrupt back on the Thread and go on
            Thread.currentThread().interrupt();
        }
    }

    private void processCreditRequest(final CreditRequest creditRequest) {
        if (creditRequest.getBedrag() != null && creditRequest.getBedrag().compareTo(new BigDecimal(200000)) > 0) {
            creditRequest.setStatus(Status.AFGEWEZEN);
            creditRequest.setAfwijsreden("Bedrag is te hoog");
        } else {
            creditRequest.setStatus(Status.AKKOORD);
        }
        creditRequest.setVerwerktdoor(getHostname());
        creditRequestRepository.save(creditRequest);
    }

    private CreditRequestAvailable unmarshalMessage(final Message<String> msg) throws JAXBException {
        System.out.println("Bericht ontvangen: " + msg);

        CreditRequestAvailable creditRequestAvailable =
                (CreditRequestAvailable) unmarshalStringToObject(msg.getPayload(), CreditRequestAvailable.class);
        System.out.println("Bericht nl.marcenschede.dozerdemo.datadefs.creditRequestAvailable: " + creditRequestAvailable.getId());
        return creditRequestAvailable;
    }

    private void markAsInBehandeling(final CreditRequest creditRequest) {
        creditRequest.setStatus(Status.IN_BEHANDELING);
        creditRequestRepository.save(creditRequest);
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "Hostname not retrieved";
        }
    }
}
