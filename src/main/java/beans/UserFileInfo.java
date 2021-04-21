package beans;

public class UserFileInfo {
    private int id;
    private int user_id;
    private String file_name;
    private String file_text;
    private String file_sort;
    private String file_time;
    private String file_realName;
    private String size;
    private String type;

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int sort;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_time() {
        return file_time;
    }

    public String getFile_realName() {
        return file_realName;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_text() {
        return file_text;
    }

    public String getFile_sort() {
        return file_sort;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFile_time(String file_time) {
        this.file_time = file_time;
    }


    public void setFile_realName(String file_realName) {
        this.file_realName = file_realName;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setFile_text(String file_text) {
        this.file_text = file_text;
    }

    public void setFile_sort(String file_sort) {
        this.file_sort = file_sort;
    }

    public Object[] getValueArray(){
        return new Object[]{getId(),getUser_id(),getFile_name(),getFile_text(),
                getFile_sort(),getFile_time(),getFile_realName(),getSort(),
                getSize(),getType()};
    }

    public static String[] getField(){
        return new String[]{"id","user_id","file_name",
                "file_text","file_sort",
                "file_time","file_realName",
                "sort","size","type"};
    }
}
