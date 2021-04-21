package Service;

import Controller.FileOperate;
import Dao.BlogDao;
import Dao.Json;
import beans.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class BlogService {

    private BlogDao blogDao;

    public BlogDao getBlogDao() {
        return blogDao;
    }

    @Autowired
    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public boolean addBlogInfo(BlogInfo blogInfo){
        int t=blogDao.add(blogInfo);
        return t==1;
    }

    public boolean storeBlogHtml(String text,String path){
        try {
            FileOutputStream file=new FileOutputStream(path);
            PrintStream out=new PrintStream(file);
            out.println(text);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserJsonBlogInfo(int user_id){
        List<BlogInfo>list=blogDao.getUserBlogInfo(user_id);
        if(list==null){
            return null;
        }
        String[]field=BlogInfo.getField();
        Object[][]obj=new Object[list.size()][];
        int i=0;
        for(BlogInfo x:list){
            obj[i++]=x.getValue();
        }
        return Json.getJson(field,obj);
    }

    public String getUserJsonBlogInfoWithText(int user_id,int blog_id,String path){
        BlogInfo b=blogDao.getUserSingleBlog(user_id,blog_id);
        if(b==null){
            return null;
        }
        StringBuilder text=new StringBuilder();
        try {
            FileReader file=new FileReader(path);
            BufferedReader bf=new BufferedReader(file);
            String line= bf.readLine();
            while (line!=null){
                text.append(line);
                line= bf.readLine();
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        b.setText(text.toString());
        String[]field=BlogInfo.getFieldWithText();
        Object[]value=b.getValueWithText();
        return Json.getJson(field,value);
    }

    public String getJsonBlogInfoWithTextBySort(int sort) {
        List<BlogInfo> b = blogDao.getUserBlogBySort(sort);
        int l=b.size();
        Object[][]value=new Object[l][];
        int index=0;
        for(BlogInfo x:b){
            int user_id=x.getUser_id();
            String title=x.getTitle();
            StringBuilder text=new StringBuilder();
            String path= FileOperate.uploadPath+File.separatorChar
                    +user_id+File.separatorChar+title;
            try {
                FileReader file=new FileReader(path);
                BufferedReader bf=new BufferedReader(file);
                String line= bf.readLine();
                while (line!=null){
                    text.append(line);
                    line= bf.readLine();
                }
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            x.setText(text.toString());
            value[index++]=x.getValueWithText();
        }
        String[]field=BlogInfo.getFieldWithText();
        return Json.getJson(field,value);
    }

    public String getBlogBySort(int sort){
        List<BlogInfo>list=blogDao.getUserBlogBySort(sort);
        if(list==null){
            return "";
        }
        int size=list.size();
        String[]field=BlogInfo.getField();
        Object[][]value=new Object[size][];
        int index=0;
        for(BlogInfo x:list){
            value[index++]=x.getValue();
        }
        return Json.getJson(field,value);
    }
}
