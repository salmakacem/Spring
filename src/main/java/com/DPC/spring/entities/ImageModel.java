package com.DPC.spring.entities;

import java.io.Serializable;




public class ImageModel {
    private String pic;

    private String pictype;


    private byte[] picByte;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPictype() {
        return pictype;
    }

    public void setPictype(String pictype) {
        this.pictype = pictype;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public ImageModel(String pic, String pictype, byte[] picByte) {
        this.pic = pic;
        this.pictype = pictype;
        this.picByte = picByte;
    }
}