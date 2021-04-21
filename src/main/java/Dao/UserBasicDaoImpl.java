package Dao;

import beans.UserBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserBasicDaoImpl implements UserBasicDao {

    JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int add(UserBasic u) {
        String sql="insert into basicinfo values(?,?,?,?,?,?,?);";
        return jdbc.update(sql,u.getUser_idNum(),
                u.getUser_id(),u.getUser_name(),
                u.getUser_sex(),u.getUser_introduction(),
                u.getUser_date(),u.getUser_city());
    }

    @Override
    public int update(UserBasic u) {
        String sql="update basicinfo set user_idNum=?,user_id=?,user_name=?," +
                "user_sex=?,user_introduction=?,user_date=?,user_city=?" +
                "where user_idNum=?;";
        return jdbc.update(sql,u.getUser_idNum(),
                u.getUser_id(),u.getUser_name(),
                u.getUser_sex(),u.getUser_introduction(),
                u.getUser_date(),u.getUser_city(),u.getUser_idNum());
    }

    @Override
    public UserBasic queryForInfo(int user_idNum) {
        String sql="select * from basicinfo where user_idNum=?;";
        UserBasic u;
        try{
            u=jdbc.queryForObject(sql,new BeanPropertyRowMapper<>(UserBasic.class),user_idNum);
        }catch (Exception e){
            return null;
        }
        return u;
    }

    @Override
    public String getUserId(int id) {
        String sql="select user_id from basicinfo where user_idNum=?;";
        String user;
        try{
            user=jdbc.queryForObject(sql,String.class,id);
        }catch (Exception e){
            return null;
        }
        return user;
    }

    @Override
    public int init(int id){
        String sql="insert into basicinfo values (?,null,null,null,null,null,null)";
        return  jdbc.update(sql,id);
    }
}
