package beans;

public class QuestionBean {
    private int id;
    private String title;
    private int user_idNum;
    private String user_id;
    private int sort;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getUser_idNum() {
        return user_idNum;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getSort() {
        return sort;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser_idNum(int user_idNum) {
        this.user_idNum = user_idNum;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public static String[]getField(){
        return new String[]{
                "id","title",
                "user_idNum","user_id",
                "sort"};
    }

    public  Object[]getValue(){
        return new Object[]{
                getId(),getTitle(),getUser_idNum(),
                getUser_id(),getSort()
        };
    }



}
