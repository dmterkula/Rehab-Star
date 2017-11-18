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

    private final int maxFeedSize = 10;

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
        HashSet<Story> currentFeed = new HashSet<>();
        for(FollowingPair fp: followingPairs){
            currentFeed.addAll(storyService.findOneUserStoriesWithinDays(fp.getFollowingId(), 1));
        }

        feed.addAll(currentFeed);
        if(feed.size() < maxFeedSize){
            feed = addMostRecentNonFollowerStories(userId, feed);
        }

        return feed;
    }



    public List<Story> addMostRecentNonFollowerStories(int userId, List<Story> currentFeed){
        List<Story> storyPool = generateStoryPool();

        Iterator<Story> iterator = storyPool.iterator();
        while(iterator.hasNext()){
            Story s = iterator.next();
            if(s.getUserId() == userId || currentFeed.contains(s)){
                iterator.remove();
            }
        }

        storyPool = storyService.sortStoriesForMostRecent(storyPool);
        if(storyPool.size() > maxFeedSize-currentFeed.size()){
            List<Story> trimmedFeed = storyPool.subList(0, maxFeedSize-currentFeed.size());
            storyPool = trimmedFeed;
        }

        currentFeed.addAll(storyPool);


        return currentFeed;
    }

    public List<Story> generateStoryPool(){
        List<Story> storyPool = storyService.findStoriesWithinDays(7);

        return  storyPool;

    }


    public List<Story> populateUserPageWithPastStories(int userId){
        List<Story> usersStories = storyService.findStoriesByUserId(userId);
        usersStories = storyService.sortStoriesForMostRecent(usersStories);
        return usersStories;
    }

}
