package RehabStar.Project.services;

import RehabStar.Project.dal.StoryDao;
import RehabStar.Project.dal.UserDao;
import RehabStar.Project.domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmter on 10/24/2017.
 */

@Component
public class StoryServiceImpl implements StoryService {

    @Autowired
    StoryDao storyDao;

    @Autowired
    UserDao userDao;

    @Override
    public int findUserIdByStoryId(int storyId){
        return storyDao.findUserIdByStoryId(storyId);
    }

    @Override
    public Story findStoryById(int storyid){
        return storyDao.findStoryById(storyid);
    }

    @Override
    public List<Story> findStoriesByUserId(int userId){
        return storyDao.findStoriesByUserId(userId);
    }

    @Override
    public byte[] findTextByStoryId(int id){
        return storyDao.findTextByStoryId(id);
    }

    @Override
    public String findFileNameByStoryId(int id){
        return storyDao.findFileNameByStoryId(id);
    }

    @Override
    public List<Story> findAllStories(){
        return storyDao.findAllStories();
    }

    @Override
    public List<Story> findStoriesByUserName(String userName){
        int userId= userDao.findUserByUserName(userName).getId();
        return storyDao.findStoriesByUserId(userId);
    }

    @Override
    public void updateStoryText(int storyId, byte[] text){
        storyDao.updateStoryText(storyId, text);
    }

    @Override
    public void addStory(Story s){
        storyDao.addStory(s);
    }

    @Override
    public void deleteStory(Story s){
        storyDao.deleteStory(s);
    }
}
