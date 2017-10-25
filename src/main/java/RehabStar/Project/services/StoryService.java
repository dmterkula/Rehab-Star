package RehabStar.Project.services;

import RehabStar.Project.domain.Story;

import java.util.List;

/**
 * Created by dmter on 10/24/2017.
 */
public interface StoryService {

    int findUserIdByStoryId(int storyId);

    Story findStoryById(int storyid);

    List<Story> findStoriesByUserId(int userId);

    byte[] findTextByStoryId(int id);

    String findFileNameByStoryId(int id);

    List<Story> findAllStories();

    List<Story> findStoriesByUserName(String userName);

    void updateStoryText(int storyId, byte[] text);

    void addStory(Story s);

    void deleteStory(Story s);
}
