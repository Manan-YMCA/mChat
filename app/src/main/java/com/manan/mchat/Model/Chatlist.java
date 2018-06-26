package com.manan.mchat.Model;

public class Chatlist {

    private String cname;
    private String cmessage;
    public Chatlist(String name, String message) {
        cname = name;
        cmessage = message;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCmessage() {
        return cmessage;
    }

    public void setCmessage(String cmessage) {
        this.cmessage = cmessage;
    }
}
