package RehabStar.Project.controller;

import RehabStar.Project.auxilary.StoryFeed;
import RehabStar.Project.domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by David Terkula on 11/16/2017.
 */
@Component
public class FeedController {
    @Autowired
    StoryFeed storyFeed;

    @Autowired
    public FeedController(StoryFeed storyFeed){
        this.storyFeed = storyFeed;
    }

    @RequestMapping(value = "/findFeedForUser/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<Story> findFeedForUser(@PathVariable("userId") int userId){
        return storyFeed.populateUsersFeedFromFollowers(userId);
    }

}
