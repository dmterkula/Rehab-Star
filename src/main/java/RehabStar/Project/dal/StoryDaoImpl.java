package RehabStar.Project.dal;

import RehabStar.Project.domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by dmter on 10/24/2017.
 */
@Component
public class StoryDaoImpl implements StoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int findUserIdByStoryId(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getBytes("text")));
        Story s = stories.get(0);
        return s.getUserId();
    }

    @Override
    public Story findStoryById(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getBytes("text")));
        Story s = stories.get(0);
        s.setId(storyId);
        return s;
    }

    @Override
    public List<Story> findStoriesByUserId(int userId) {
        String selectStories = "SELECT * FROM STORIES WHERE userId = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{userId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getBytes("text")));
        return stories;
    }

    @Override
    public byte[] findTextByStoryId(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getBytes("text")));
        Story s = stories.get(0);
        return s.getText();
    }

    @Override
    public String findFileNameByStoryId(int storyId) {
        String selectStories = "SELECT * FROM STORIES WHERE id = ?";

        List<Story> stories = jdbcTemplate.query(selectStories, new Object[]{storyId},
                (rs, rowNum) ->
                        new Story(
                                rs.getInt("id"),
                                rs.getInt("userId"),
                                rs.getString("fileName"),
                                rs.getBytes("text")));
        Story s = stories.get(0);
        return s.getFileName();
    }

    @Override
    public List<Story> findAllStories() {
        String selectAll = "SELECT * FROM STORIES";
        List<Story> stories = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(Story.class));
        return stories;
    }


    @Override
    public void updateStoryText(int storyId, byte [] text){
        String updateText = "UPDATE STORIES SET " + "text=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateText, new Object[]{text, storyId});
    }

    @Override
    public void addStory(Story s){
        String insert = "INSERT INTO STORIES " +
                "(userId, fileName, text) " +
                "VALUES (?, ?, ?)";

        jdbcTemplate.update(insert, new Object[] {s.getUserId(), s.getFileName(), s.getText()});
    }

    @Override
    public void deleteStory(Story s){
        String delete ="DELETE FROM STORIES WHERE id = ?";
        jdbcTemplate.update(delete, s.getId());
    }
}
