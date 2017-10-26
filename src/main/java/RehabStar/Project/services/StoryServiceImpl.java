package RehabStar.Project.services;

import RehabStar.Project.dal.StoryDao;
import RehabStar.Project.dal.UserDao;
import RehabStar.Project.domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by David Terkula on 10/24/2017.
 */

@Component
public class StoryServiceImpl implements StoryService {

    @Autowired
    StoryDao storyDao;

    @Autowired
    UserDao userDao;

    /*
       Returns the user id who wrote the story with the passed story
    */
    @Override
    public int findUserIdByStoryId(int storyId){
        return storyDao.findUserIdByStoryId(storyId);
    }

    /*
        Returns the Story with the given id
     */
    @Override
    public Story findStoryById(int storyid){
        return storyDao.findStoryById(storyid);
    }

    /*
        Returns a List of stories written by a user with a a given user name.
     */
    @Override
    public List<Story> findStoriesByUserId(int userId){
        return storyDao.findStoriesByUserId(userId);
    }

    /*
       Returns the byte array associated with the story id
    */
    @Override
    public byte[] findTextByStoryId(int id){
        return storyDao.findTextByStoryId(id);
    }

    /*
        Returns the fileName with the given storyId
     */
    @Override
    public String findFileNameByStoryId(int id){
        return storyDao.findFileNameByStoryId(id);
    }

    /*
        Returns a List of Stories with all the stories
     */
    @Override
    public List<Story> findAllStories(){
        return storyDao.findAllStories();
    }

    /*
        Returns a List of stories by a certain userName
     */
    @Override
    public List<Story> findStoriesByUserName(String userName){
        int userId= userDao.findUserByUserName(userName).getId();
        return storyDao.findStoriesByUserId(userId);
    }

    /*
        Updates the story text with the passed text of the given story id
     */
    @Override
    public void updateStoryText(int storyId, byte[] text){
        storyDao.updateStoryText(storyId, text);
    }

    /*
      Adds a new story to the DB
   */
    @Override
    public void addStory(Story s){
        storyDao.addStory(s);
    }

    /*
        Deletes the story from the DB
     */
    @Override
    public void deleteStory(Story s){
        storyDao.deleteStory(s);
    }
}
