package RehabStar.Project.dal;

import RehabStar.Project.domain.Story;

import java.util.List;

/**
 * Created by dmter on 10/24/2017.
 */
public interface StoryDao {

    /*
        Returns the user id who wrote the story with the passed story
     */
    int findUserIdByStoryId(int storyId);

    /*
        Returns the Story with the given id
     */
    Story findStoryById(int storyid);

    /*
       Returns a List of stories written by a user with a a given user name.
    */
    List<Story> findStoriesByUserId(int userId);

    /*
      Returns the byte array associated with the story id
   */
    byte[] findTextByStoryId(int id);

    /*
        Returns the fileName with the given storyId
     */
    String findFileNameByStoryId(int id);

    /*
        Returns a List of Stories with all the stories
     */
    List<Story> findAllStories();

    /*
        Updates the story text with the passed text of the given story id
     */
    void updateStoryText(int storyId, byte [] text);

    /*
        Adds a new story to the DB
     */
    void addStory(Story s);

    /*
        Deletes the story from the DB
     */
    void deleteStory(Story s);

    String findTitleById(int storyId);

    List<Story> findStoriesByTitle(String title);

    List<Story> findStoriesByTitleSubstring(String sub);

    List<String> findAllTitles();

}
