package cn.com.user.model;

public class User {

    private int uId;
    private String uName;
    private String uPass;
    private int state;
    private int rId;

    public int getUId(){
        return uId;
    }
    public void setUId(int uId){
        this.uId = uId;
    }

    public String getUName(){
        return uName;
    }

    public void setUName(String uName){
        this.uName = uName;
    }

    public String getUPass(){
        return uPass;
    }

    public void setUPass(String uPass){
        this.uPass = uPass;
    }

    public int getState(){
        return state;
    }

    public void setState(int state){
         this.state = state;
    }

    public int getRId(){
        return rId;
    }

    public void setRId(int rId){
        this.rId = rId;
    }
}
