package com.in.monetapp.beans;

/**
 * Created by Aditi on 7/23/2017.
 */

public class SmsBeans {

    private String id;
    private String address;
    private String msg;
    private String readState; //"0" for have not read sms and "1" for have read sms
    private String time;
    private String folderName;

    public SmsBeans() {
    }

    public SmsBeans(String id, String address, String msg, String readState, String time, String folderName) {
        this.id = id;
        this.address = address;
        this.msg = msg;
        this.readState = readState;
        this.time = time;
        this.folderName = folderName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public String toString() {
        return "SmsBeans{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", msg='" + msg + '\'' +
                ", readState='" + readState + '\'' +
                ", time='" + time + '\'' +
                ", folderName='" + folderName + '\'' +
                '}';
    }
}
