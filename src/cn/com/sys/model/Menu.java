package cn.com.sys.model;

public class Menu {
    private transient int id;
    private String name;
    private int pid;
    private String url;
    private int type;
    private int oder;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOder() {
        return oder;
    }

    public void setOder(int oder) {
        this.oder = oder;
    }
}
