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

    /*
        Constructor for FeedController, Autowires storyFeed dependency
     */
    @Autowired
    public FeedController(StoryFeed storyFeed){
        this.storyFeed = storyFeed;
    }

    /*
        finds a feed of 10 stories from follower for user if 10 available; otherwise pulls in random
     */
    @RequestMapping(value = "/findFeedForUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findFeedForUser(@ModelAttribute("user") @PathVariable("userId") int userId){
        return storyFeed.populateUsersFeedFromFollowers(userId);
    }

    /*
        Returns a feed of a user's past stories in order that one would see if they clicked on that users profile
     */
    @RequestMapping(value = "/findFeedOfUsersStories/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findFeedOfUsersStories(@ModelAttribute("user") @PathVariable("userId") int userId) {
        return storyFeed.populateUserPageWithPastStories(userId);
    }

    /*
        Returns a list of all past stories that are not the users
     */
    @RequestMapping(value = "/findAllStoriesNotUsers/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findAllStoriesNotUsers(@ModelAttribute("user") @PathVariable("userId") int userId) {
        return storyFeed.populateUserFeedAllStories(userId);
    }

    /*
        Returns a list of all past stories that are not the users that were posted within a number of days
     */
    @RequestMapping(value = "/findAllStoriesNotUsersWithinDays/{userId}/{daysSince}", method = RequestMethod.GET)
    public @ResponseBody List<Story> findAllStoriesNotUsers(@ModelAttribute("user") @PathVariable("userId") int userId,
                                                            @PathVariable("daysSince") int daysSince) {
        return storyFeed.populateUserFeedAllStoriesWithinDays(userId, daysSince);
    }

}
