package RehabStar.Project.controller;

import RehabStar.Project.domain.FollowingPair;
import RehabStar.Project.services.FollowingPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by David Terkula on 11/14/2017.
 */
@Component
public class FollowingPairController {

    @Autowired
    private FollowingPairService followingPairService;

    @Autowired
    public FollowingPairController(FollowingPairService followingPairService){
        this.followingPairService = followingPairService;
    }

    @RequestMapping(value = "/findUserFollowersId/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<FollowingPair> findUserById(@PathVariable("id") int id){
        return followingPairService.findFollowerIds(id);
    }

    @RequestMapping(value = "/addFollower/{id}/{toFollowId}", method = RequestMethod.GET)
    public @ResponseBody void addFollower(@PathVariable("userId") int userId, @PathVariable("toFollowId") int toFollowId){
        followingPairService.addFollowingPair(userId, toFollowId);
    }

}
