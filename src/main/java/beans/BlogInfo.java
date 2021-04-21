package beans;

public class BlogInfo {
    private int id;
    private int user_id;
    private String title;
    private String blog_id;
    private String briefIntro;
    private int sort;
    private String sortStr;
    private String draft;
    private String condition;
    private String text;
    private String time;


    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getBlog_id() {
        return blog_id;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public String getCondition() {
        return condition;
    }

    public String getText() {
        return text;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public void setSortStr(String sort) {
        this.sortStr = sort;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public String getSortStr() {
        return sortStr;
    }

    public String getDraft() {
        return draft;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBlog_id(String blog_id) {
        this.blog_id = blog_id;
    }

    public static String[] getField(){
        return new String[]{
                "user_id", "title", "blog_id",
                "briefIntro", "sortStr", "draft",
                "condition","time","sort"
        };
    }
    public static String[] getFieldWithText(){
        return new String[]{
                "user_id", "title", "blog_id",
                "briefIntro", "sortStr", "draft",
                "condition","time","text",
                "sort"
        };
    }
    public Object[]getValue(){
        return new Object[]{
                getUser_id(),getTitle(),
                getBlog_id(),getBriefIntro(),
                getSortStr(),getDraft(),
                getCondition(),getTime(),
                getSort()
        };
    }
    public Object[]getValueWithText(){
        return new Object[]{
                getUser_id(),getTitle(),
                getBlog_id(),getBriefIntro(),
                getSortStr(),getDraft(),
                getCondition(),getTime(),
                getText(),getSort()
        };
    }
}
