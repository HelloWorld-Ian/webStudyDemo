package Dao;

import beans.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogDaoImpl implements BlogDao {

    private JdbcTemplate jdbc;

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Override
    public int add(BlogInfo blogInfo) {
        System.out.println(blogInfo.getBlog_id());
        String sql = "insert into bloginfo values(null,?,?,?,?,?,?,?,?,?);";
        return jdbc.update(sql, blogInfo.getUser_id(), blogInfo.getTitle(), blogInfo.getBlog_id()
                , blogInfo.getBriefIntro(), blogInfo.getSortStr(), blogInfo.getDraft(), blogInfo.getCondition()
                , blogInfo.getTime(),blogInfo.getSort());
    }

    @Override
    public List<BlogInfo> getUserBlogInfo(int user_id) {
        String sql = "select * from bloginfo where user_id=?;";
        List<BlogInfo> list;
        try {
            list = jdbc.query(sql, new BeanPropertyRowMapper<>(BlogInfo.class), user_id);
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    @Override
    public BlogInfo getUserSingleBlog(int user_id, int blog_id) {
        String sql = "select * from bloginfo where user_id=? and blog_id=?";
        BlogInfo b;
        try {
            b = jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(BlogInfo.class),
                    user_id, blog_id);
        } catch (Exception e) {
            return null;
        }
        return b;
    }

    @Override
    public List<BlogInfo> getUserBlogBySort(int sort) {
        String sql = "select * from bloginfo where sort=?;";
        List<BlogInfo> list;
        try {
            list = jdbc.query(sql, new BeanPropertyRowMapper<>(BlogInfo.class), sort);
        } catch (Exception e) {
            return null;
        }
        return list;
    }
}
