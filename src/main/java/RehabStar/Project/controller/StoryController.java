package RehabStar.Project.controller;

/**
 * Created by David Terkula on 10/26/2017.
 */


import RehabStar.Project.domain.Story;
import RehabStar.Project.domain.User;
import RehabStar.Project.services.StoryService;
import javafx.scene.paint.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Comparator;
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
        byte [] t = getBytes(text);
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
    @RequestMapping(value = "/findStoriesByUserId/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findStoriesByUserId(@PathVariable("userId") int userId) {
        return storyService.findStoriesByUserId(userId);
    }

    /*
         Returns Story text of passed in id
      */
    @RequestMapping(value = "/returnStoryPlainTextById/{id}", method = RequestMethod.GET)
    public @ResponseBody String returnStoryPlainTextById(@PathVariable("id") int id)
            throws java.io.UnsupportedEncodingException{
        Story s = storyService.findStoryById(id);
        byte [] text = s.getText();
        String plainText = storyService.convertToPlainText(text);
        return plainText;
    }

    /*
    Returns List<Story> with a given given substring in the title field
  */
    @RequestMapping(value = "/findStoriesByTitleSubstring/{sub}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findStoriesByTitleSubstring(@PathVariable("sub") String sub){
        List<Story> matches = storyService.findStoriesByTitleSubstring(sub);
        return matches;
    }

    /*
    Returns List<Story> with a dateCreated in the last n number of days
  */
    @RequestMapping(value = "/findStoriesWithinDays/{daysSince}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findStoriesWithinDays(@PathVariable("daysSince") int daysSince){
        List<Story> matches = storyService.findStoriesWithinDays(daysSince);
        return matches;
    }

    /*
      Returns List<Story> with a dateCreated in the last n number of hours
    */
    @RequestMapping(value = "/findStoriesWithinHours/{hoursSince}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findStoriesWithinHours(@PathVariable("hoursSince") int hoursSince){
        List<Story> matches = storyService.findStoriesWithinDays(hoursSince);
        return matches;
    }

    /*
        Returns sorted list of stories based on timestamp
     */
    @RequestMapping(value = "sortUserStoriesForMostRecent/{id}")
    public @ResponseBody List<Story> sortStoriesForMostRecent(@PathVariable ("id") int storyId){
        List<Story> userStories = storyService.findStoriesByUserId(storyId);
        return storyService.sortStoriesForMostRecent(userStories);
    }


    /*
         Returns list of stories tagged with a given keyword
     */
    @RequestMapping(value = "findStoriesByAKeyword/{keyword}")
    public @ResponseBody List<Story> findStoriesByAKeyword(@PathVariable ("keyword") String keyword){
        return storyService.findStoriesByAKeyword(keyword);
    }

    /*
        Returns the story with the given id with the updated keywords.
     */
    @RequestMapping(value = "/updateKeywordsById/{id}/{k1}/{k2}/{k3}" , method = RequestMethod.GET)
    public @ResponseBody Story updateKeywordsById(@ModelAttribute("user") @PathVariable("id") int id, @PathVariable("k1") String k1, @PathVariable("k2") String k2, @PathVariable("k3") String k3){
        storyService.updateKeywordsById(id, k1, k2, k3);
        return storyService.findStoryById(id);

    }

    /*
        Returns the plain text of a story
     */
    public String getStoryPlainText(Story s) throws java.io.UnsupportedEncodingException {
        return storyService.convertToPlainText(s.getText());
    }

    /*
        Gets the byte[] of a string so it can be inserted into DB
     */
    public byte[] getBytes(String text){
        return storyService.convertTextToBytes(text);
    }

    @RequestMapping(value = "/addStory/{title}/{text}", method = RequestMethod.GET)
    public @ResponseBody void addStory(@ModelAttribute(value="user") User user, @PathVariable("title") String title, @PathVariable("text") String text, Model model){
        // the text


    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute(value="user") User user, @ModelAttribute(value="story") Story story, BindingResult bindingResult, Model model) {
       // story.setId()
        // set file name

        if(user == null) {
            System.out.println("Null!");
        }
        String fileName = story.getTitle().replaceAll("//s+", "");
        fileName = fileName +".txt";
        story.setFileName(fileName);
        // sets the bytes
        byte[] bytes = storyService.convertTextToBytes(story.getPlainText());
        story.setText(bytes);
        // set time
        story.setDateCreated(new Timestamp(System.currentTimeMillis()));
        System.out.println(story.getId() + " ");
        // set id
        System.out.println("To prove its not null here is the user name " + user.getUserName());
        story.setId(user.getId());

        storyService.addStory(story);
        return "home";

    }

}
