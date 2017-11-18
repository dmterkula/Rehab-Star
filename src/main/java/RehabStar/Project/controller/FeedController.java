package RehabStar.Project.controller;

import RehabStar.Project.auxilary.StoryFeed;
import RehabStar.Project.domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by David Terkula on 11/16/2017.
 */
@Controller
public class FeedController {
    @Autowired
    StoryFeed storyFeed;

    @Autowired
    public FeedController(StoryFeed storyFeed){
        this.storyFeed = storyFeed;
    }

    @RequestMapping(value = "/findFeedForUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findFeedForUser(@ModelAttribute("user") @PathVariable("userId") int userId){
        return storyFeed.populateUsersFeedFromFollowers(userId);
    }

    @RequestMapping(value = "/findFeedOfUsersStories/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findFeedOfUsersStories(@ModelAttribute("user") @PathVariable("userId") int userId) {
        return storyFeed.populateUserPageWithPastStories(userId);
    }
}
