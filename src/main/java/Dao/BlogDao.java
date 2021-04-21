package Dao;

import beans.BlogInfo;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BlogDao {
    public int add(BlogInfo blogInfo);
    public List<BlogInfo>getUserBlogInfo(int user_id);
    public BlogInfo getUserSingleBlog(int user_id,int blog_id);
    public List<BlogInfo> getUserBlogBySort(int sort);
}
