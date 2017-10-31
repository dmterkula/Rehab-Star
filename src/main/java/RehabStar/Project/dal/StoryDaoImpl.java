package RehabStar.Project.dal;

import RehabStar.Project.domain.Story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by David Terkula on 10/24/2017.
 */
@Component
public class StoryDaoImpl implements StoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final long ONE_DAY_MILLISCONDS = 25 * 60 * 60 * 1000;

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
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated")));


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
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated")));
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
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated")));
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
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated")));
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
                                rs.getBytes("text"),
                                rs.getTimestamp("dateCreated")));
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
                "(userId, fileName, text, title, dateCreated) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(insert, new Object[] {s.getUserId(), s.getFileName(), s.getText(), s.getTitle(), s.getDateCreated()});
    }

    /*
       Deletes the story from the DB
    */
    @Override
    public void deleteStory(Story s){
        String delete ="DELETE FROM STORIES WHERE id = ?";
        jdbcTemplate.update(delete, s.getId());
    }

    /*
        Returns the title of a story with a given id
     */
    @Override
    public String findTitleById(int id){
        String s = "SELECT title FROM STORIES WHERE id = ?";
        Object[] inputs = new Object[] {id};
        String title = jdbcTemplate.queryForObject(s, inputs, String.class);
        return title;
    }

    /*
        Returns a list of strings with all the story titles
     */
    @Override
    public List<String> findAllTitles(){
        String s = "SELECT title FROM STORIES";
        return jdbcTemplate.queryForList(s, String.class);
    }

    /*
        Returns list of stories with identical titles
     */
    @Override
    public List<Story> findStoriesByTitle(String title){
        String s = "SELECT * FROM STORIES WHERE title = ?";
        Object[] inputs = new Object[] {title};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));
    }

    /*
       Returns a the dateCreated timestamp of a story with the given id
    */
   @Override
   public Timestamp findDateCreatedById(int storyId){
       String s = "SELECT dateCreated FROM STORIES WHERE id = ?";
       Object[] inputs = new Object[] {storyId};
       Timestamp dateCreated = jdbcTemplate.queryForObject(s, inputs, Timestamp.class);
       return dateCreated;
   }

    /*
      Returns a list of stories created within a certain number of days passed in
   */
   @Override
   public List<Story> findStoriesWithinDays(int daysSince){
       String s = "SELECT * FROM STORIES WHERE dateCreated >= DATEADD(day, -?, GETDATE())";
       Object[] inputs = new Object[] {daysSince};
       return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));

    }

    /*
     Returns a list of stories created within a certain number of hours passed in
  */
    @Override
    public List<Story> findStoriesWithinHours(int hoursSince){
       String s = "SELECT * FROM STORIES WHERE dateCreated BETWEEN " + "dateadd(hour, -?, CURRENT_TIMESTAMP)"+
        "AND CURRENT_TIMESTAMP";
               //">= DATEADD(hh, -?, GETDATE())";
        Object[] inputs = new Object[] {hoursSince};
        return jdbcTemplate.query(s, inputs, new BeanPropertyRowMapper<>(Story.class));
    }

}
