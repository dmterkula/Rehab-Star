package RehabStar.Project.dal;

import RehabStar.Project.domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by David Terkula on 10/24/2017.
 */
@Component
public class StoryDaoImpl implements StoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
       Returns the Story with the given id
    */
    @Override
    public int findUserIdByStoryId(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getString("title"),
                                rs.getBytes("text")));
        Story s = stories.get(0);
        return s.getUserId();
    }

    /*
        Returns the Story with the given id
     */
    @Override
    public Story findStoryById(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getString("title"),
                                rs.getBytes("text")));
        Story s = stories.get(0);
        s.setId(storyId);
        return s;
    }

    /*
        Returns a List of stories written by a user with a a given user name.
     */
    @Override
    public List<Story> findStoriesByUserId(int userId) {
        String selectStories = "SELECT * FROM STORIES WHERE userId = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{userId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getString("title"),
                                rs.getBytes("text")));
        return stories;
    }

    /*
       Returns the byte array associated with the story id
    */
    @Override
    public byte[] findTextByStoryId(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getString("title"),
                                rs.getBytes("text")));
        Story s = stories.get(0);
        return s.getText();
    }

    /*
        Returns the fileName with the given storyId
     */
    @Override
    public String findFileNameByStoryId(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getNString("title"),
                                rs.getBytes("text")));
        Story s = stories.get(0);
        return s.getFileName();
    }

    /*
        Returns a List of Stories with all the stories
     */
    @Override
    public List<Story> findAllStories() {
        String selectAll = "SELECT * FROM STORIES";
        List<Story> stories = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(Story.class));
        return stories;
    }


    /*
       Updates the story text with the passed text of the given story id
    */
    @Override
    public void updateStoryText(int storyId, byte [] text){
        String updateText = "UPDATE STORIES SET " + "text=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateText, new Object[]{text, storyId});
    }

    /*
       Adds a new story to the DB
    */
    @Override
    public void addStory(Story s){
        String insert = "INSERT INTO STORIES " +
                "(userId, fileName, text, title) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(insert, new Object[] {s.getUserId(), s.getFileName(), s.getText(), s.getTitle()});
    }

    /*
       Deletes the story from the DB
    */
    @Override
    public void deleteStory(Story s){
        String delete ="DELETE FROM STORIES WHERE id = ?";
        jdbcTemplate.update(delete, s.getId());
    }

    @Override
    public String findTitleById(int id){
        String s = "SELECT title FROM STORIES WHERE id = ?";
        Object[] inputs = new Object[] {id};
        String title = jdbcTemplate.queryForObject(s, inputs, String.class);
        return title;
    }

    @Override
    public List<String> findAllTitles(){
        String s = "SELECT title FROM STORIES";
        return jdbcTemplate.queryForList(s, String.class);
    }

    @Override
    public List<Story> findStoriesByTitleSubstring(String substring){
        List<String> titles = findAllTitles();
        List<String> matches = new ArrayList<>();
        for(String s: titles){
            if(s.toLowerCase().contains(substring.toLowerCase())){
                matches.add(s);
            }
        }
        List<Story> returnMatches = new ArrayList<>();
        for(String s: matches){
            returnMatches.addAll(findStoriesByTitle(s));
        }

        return returnMatches;

    }

    @Override
    public List<Story> findStoriesByTitle(String title){
        String s = "SELECT * FROM STORIES WHERE title = ?";
        Object[] inputs = new Object[] {title};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));
    }

}
