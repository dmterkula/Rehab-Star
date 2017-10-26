package RehabStar.Project.controller;

/**
 * Created by David Terkula on 10/26/2017.
 */


import RehabStar.Project.domain.Story;
import RehabStar.Project.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StoryController {
    private StoryService storyService;


    @Autowired
    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    //return all stories
    @RequestMapping(value = "/findAllStories")
    public @ResponseBody List<Story> findAllStories() {
        return storyService.findAllStories();

    }

    /*
Returns the story wth the given id with the updated text
*/
    @RequestMapping(value = "/updateStoryText/{id}/{text}", method = RequestMethod.GET)
    public @ResponseBody Story updateStoryNameById(@PathVariable("id") int id, @PathVariable("text") String text){
        byte [] t = text.getBytes();
        storyService.updateStoryText(id, t);
        return storyService.findStoryById(id);
    }

    /*
     Returns Story of passed in id
  */
    @RequestMapping(value = "/findStoryById/{id}", method = RequestMethod.GET)
    public @ResponseBody Story findStoryById(@PathVariable("id") int id){
        return storyService.findStoryById(id);
    }

    /*
         Returns Story of passed in id
      */
    @RequestMapping(value = "/returnStoryPlainTextById/{id}", method = RequestMethod.GET)
    public @ResponseBody String returnStoryPlainTextById(@PathVariable("id") int id){
        Story s = storyService.findStoryById(id);
        String string =  new String (s.getText());
        return string;
    }


}
