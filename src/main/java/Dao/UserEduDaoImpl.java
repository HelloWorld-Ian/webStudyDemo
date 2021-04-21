package Dao;

import beans.UserEdu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserEduDaoImpl implements UserEduDao{

    JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int add(UserEdu u) {
        String sql="insert into eduinfo values(?,?,?,?,?);";
        return jdbc.update(sql, u.getUser_idNum(),
                u.getSchool(),u.getMajor(),
                u.getTime(),u.getBackground());
    }

    @Override
    public int update(UserEdu u) {
        String sql="update eduinfo set user_idNum=?,school=?,major=?,time=?," +
                "background=? where user_idNum=?";
        return jdbc.update(sql, u.getUser_idNum(),
                u.getSchool(),u.getMajor(),
                u.getTime(),u.getBackground(),u.getUser_idNum());
    }

    @Override
    public UserEdu queryForInfo(int user_idNum) {
        String sql="select * from eduinfo where user_idNum=?;";
        UserEdu u;
        try{
            u=jdbc.queryForObject(sql,new BeanPropertyRowMapper<>(UserEdu.class),user_idNum);
        }catch (Exception e){
            return null;
        }
        return u;
    }

    @Override
    public int init(int id) {
        String sql="insert into eduinfo values(?,null,null,null,null)";
        return jdbc.update(sql,id);
    }
}
