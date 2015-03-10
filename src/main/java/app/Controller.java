package app;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/env")
    public String env() throws JAXBException {

        StringBuilder sb = new StringBuilder();
        for (String item : applicationContext.getBeanDefinitionNames())
            sb.append(item + "<br>");
        return sb.toString();
    }

}
