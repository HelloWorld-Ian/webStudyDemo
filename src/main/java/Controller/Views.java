package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/view")
public class Views {
    @ResponseBody
    @RequestMapping(path = "/backstage",method = RequestMethod.GET)
    public ModelAndView backstage(){
        return new ModelAndView("backstage");
    }
    @ResponseBody
    @RequestMapping(path = "/register")
    public ModelAndView register(){
        return new ModelAndView("load_register");
    }
    @ResponseBody
    @RequestMapping(path = "/backstage_password")
    public ModelAndView backstage_password(){
        return new ModelAndView("backstage-password");
    }
    @ResponseBody
    @RequestMapping(path = "/backstage_resource")
    public ModelAndView backstage_resource(){
        return new ModelAndView("backstage-resource");
    }
    @ResponseBody
    @RequestMapping(path = "/backstage_storage")
    public ModelAndView backstage_storage(){
        return new ModelAndView("backstage-storage");
    }
    @ResponseBody
    @RequestMapping(path = "/backstage-writing")
    public ModelAndView backstage_writing(){
        return new ModelAndView("backstage-writing");
    }
    @ResponseBody
    @RequestMapping(path = "/article_presentation")
    public ModelAndView article_presentation(){
        return new ModelAndView("article-presentation");
    }

    @ResponseBody
    @RequestMapping(path ="/question_presentation")
    public ModelAndView question_presentation(){
        return new ModelAndView("question-presentation");
    }

    @ResponseBody
    @RequestMapping(path = "/resource_presentation")
    public ModelAndView resource_presentation(){
        return new ModelAndView("resource-presentation");
    }

    @ResponseBody
    @RequestMapping(path = "/backstage-question")
    public ModelAndView backstage_question(){
        return new ModelAndView("backstage-question");
    }

    @ResponseBody
    @RequestMapping(path = "/search_presentation")
    public ModelAndView search_presentation(){
            return new ModelAndView("search-presentation");
    }

}
