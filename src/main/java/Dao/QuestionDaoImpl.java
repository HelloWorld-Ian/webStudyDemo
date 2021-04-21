package Dao;


import beans.QuestionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao{

    JdbcTemplate jdbc;

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Override
    public int add(QuestionBean q) {
        String sql="insert into question values(null,?,?,?,?);";
        return jdbc.update(sql,q.getTitle(),q.getUser_idNum(),
                    q.getUser_id(),q.getSort());
    }

    @Override
    public int update(QuestionBean q) {
        String sql="update question set id=?,title=?,user_idNum=?," +
                "user_id=?,sort=? where id=?";
        return jdbc.update(sql,q.getId(),q.getTitle(),q.getUser_idNum(),
                           q.getUser_id(),q.getSort(),q.getId());
    }

    @Override
    public List<QuestionBean> getQuestionBySort(int sort) {
        String sql="select * from question where sort=?";
        List<QuestionBean>list;
        try {
            list = jdbc.query(sql, new BeanPropertyRowMapper<>(QuestionBean.class),sort);
        }catch (Exception e){
            return null;
        }
        return list;
    }

    @Override
    public List<QuestionBean> getQuestionByUser_id(int user_id) {
        String sql="select * from question where user_id=?";
        List<QuestionBean>list;
        try {
            list = jdbc.query(sql, new BeanPropertyRowMapper<>(QuestionBean.class),user_id);
        }catch (Exception e){
            return null;
        }
        return list;
    }
}
