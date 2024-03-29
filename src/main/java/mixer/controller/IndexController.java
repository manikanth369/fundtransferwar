package mixer.controller;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mixer2.Mixer2Engine;
import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.springmvc.Mixer2XhtmlView;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mixer.view.IndexHelper;
import mixer.view.M2staticHelper;

@Controller
public class IndexController {

    private static Log log = LogFactory.getLog(IndexController.class);

    @Autowired
    protected Mixer2Engine mixer2Engine;

    @Autowired
    protected ResourceLoader resourceLoader;

    private String mainTemplate = "classpath:m2mockup/m2template/index.html";

    @RequestMapping(value = "/")
    public Mixer2XhtmlView index() throws TagTypeUnmatchException, IOException {
        log.debug("going index()");

        Html html = mixer2Engine.loadHtmlTemplate(resourceLoader.getResource(
                mainTemplate).getInputStream());

        M2staticHelper.replaceM2staticPath(html);
        IndexHelper.replaceMessage(html);

        return new Mixer2XhtmlView(mixer2Engine, html);
    }

}
