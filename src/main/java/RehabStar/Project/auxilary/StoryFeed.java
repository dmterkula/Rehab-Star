package RehabStar.Project.auxilary;

import RehabStar.Project.domain.FollowingPair;
import RehabStar.Project.domain.Story;
import RehabStar.Project.services.FollowingPairService;
import RehabStar.Project.services.StoryService;
import RehabStar.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collections;

/**
 * Created by David Terkula on 11/14/2017.
 */
@Component
public class StoryFeed {

    private FollowingPairService followingPairService;
    private UserService userService;
    private StoryService storyService;



    @Autowired
    public StoryFeed(FollowingPairService followingPairService, UserService userService, StoryService storyService){
        this.followingPairService = followingPairService;
        this.userService = userService;
        this.storyService = storyService;
    }

    // make it of a certain length, and from a certain length
    public List<Story> populateUsersFeedFromFollowers(int userId){
        List<Story> feed = new ArrayList<>();
        List<FollowingPair> followingPairs = followingPairService.findFollowerIds(userId);
        Set<Story> allFollowersStories = new HashSet<>();
        for(FollowingPair fp: followingPairs){
            allFollowersStories.addAll(storyService.findOneUserStoriesWithinDays(fp.getFollowingId(), 1));
        }

        if(allFollowersStories.size() < 10){ // mix in random stories

            boolean stillAddRandom = true;
            while(stillAddRandom) { // add a random story

                Story randomStory = findRandomStory(userId);
                allFollowersStories.add(randomStory);
                if(allFollowersStories.size() >= 10){
                    stillAddRandom = false;
                }

            }
            feed.addAll(allFollowersStories);

        }
        else{ // add set to feed and return feed
            feed.addAll(allFollowersStories);
        }

        return feed;
    }

    public Story findRandomStory(int userId){
        Story returnMe = new Story();
        Set<Story> allStories = new HashSet<>();
        boolean exit = false;
        for(int daysBack = 0; allStories.size() == 0 && !exit; daysBack++){ // dont wanna go forever, need to add a check here
            allStories.addAll(storyService.findStoriesWithinDays(daysBack));
            if(!allStories.isEmpty() || allStories.size() == 0) {
                Iterator iterator = allStories.iterator();
                while (iterator.hasNext()){
                    Story s = (Story)iterator.next();
                    if(s.getUserId() == userId){
                        iterator.remove();
                    }
                }
            }
            if(daysBack >= 5){
                exit = true;
            }
        }

        List<Story> pickFrom = new ArrayList<>();
        pickFrom.addAll(allStories);
        Collections.shuffle(pickFrom);
        returnMe = pickFrom.get(0);
        return returnMe;
    }

    public List<Story> populateUserPageWithPastStories(int userId){
        List<Story> usersStories = storyService.findStoriesByUserId(userId);
        usersStories = storyService.sortStoriesForMostRecent(usersStories);
        return usersStories;
    }

}
