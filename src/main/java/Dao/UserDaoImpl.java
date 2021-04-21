package Dao;

import beans.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class UserDaoImpl implements UserDao{
    JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int add(UserInfo u) {
        String sql="insert into email values(null,?,?,?);";
        return jdbc.update(sql,u.getUser_email(),u.getPassword(),0);
    }

    @Override
    public  int getUserBlogTotal(int id) {
        String sql="select total_blog from email where id =? ;";
        Integer res=jdbc.queryForObject(sql,Integer.class,id);
        return res==null?-1:res;
    }

    @Override
    public void updateBlogTotal(int id, int total) {
        String sql="update email set total_blog =? where id= ?;";
        jdbc.update(sql, total + 1, id);
    }

    @Override
    public UserInfo getUserInfo(String email) {
        String sql="select * from email where user_email=?;";
        UserInfo obj;
        try {
           obj = jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(UserInfo.class), email);
        }catch (Exception e){
            return null;
        }
        return obj;
    }

    public UserInfo getUserInfo(int id) {
        String sql="select * from email where id=?;";
        UserInfo obj;
        try {
            obj = jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(UserInfo.class), id);
        }catch (Exception e){
            return null;
        }
        return obj;
    }

    @Override
    public int update(UserInfo u) {
        String sql="update email set id=?,user_email=?," +
                "password=?,total_blog=? where id=?;";
        return jdbc.update(sql,u.getValueArray(),u.getUser_email(),u.getPassword(),u.getTotal_blog());
    }
}
