package ro.tuc.ds2020.entities;

public class MessageBean  {

    private String username;

    public MessageBean(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MessageBean(String username){
        this.username = username;
    }


}