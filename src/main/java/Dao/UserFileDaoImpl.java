package Dao;

import beans.UserFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFileDaoImpl implements UserFileDao{
    JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int add(UserFileInfo u) {
        String sql="insert into fileinfo values(null,?,?,?,?,?,?,?,?,?);";
        return jdbc.update(sql,u.getFile_name(),
                u.getFile_text(),u.getFile_sort(),
                u.getUser_id(),u.getFile_time(),u.getFile_realName(),
                u.getSort(),u.getSize(),u.getType());
    }

    @Override
    public int update(UserFileInfo u) {
        String sql="update fileinfo set file_name=?,file_text=?," +
                "file_sort=?,user_id=?," +
                "file_time=?,file_realName=? where user_id=?";
        return jdbc.update(sql,u.getFile_name(),
                u.getFile_text(),u.getFile_sort(),
                u.getUser_id(),u.getFile_realName(),u.getUser_id());
    }

    @Override
    public List<UserFileInfo> queryForAllInfo(int user_id) {
        String sql="select * from fileinfo where user_id=?;";
        try {
            return jdbc.query(sql,new BeanPropertyRowMapper<>(UserFileInfo.class), user_id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserFileInfo> getFileInfoBySort(int sort) {
        String sql="select * from fileinfo where sort=?;";
        try {
            return jdbc.query(sql,new BeanPropertyRowMapper<>(UserFileInfo.class),sort);
        }catch (Exception e){
            return null;
        }
    }
}
