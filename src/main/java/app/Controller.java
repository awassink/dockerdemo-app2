package app;

import nl.marcenschede.dozerdemo.melding.v1_0.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
public class Controller {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/env")
    public String env()
            throws JAXBException {

        StringBuilder sb = new StringBuilder();
        for (String item : applicationContext.getBeanDefinitionNames())
            sb.append(item + "<br>");
        return sb.toString();
    }

}
