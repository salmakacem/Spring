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
//    public ImageModel() {
//        super();
//
//    }
//    public ImageModel(String name, String type, byte[] picByte) {
//
//        this.name = name;
//        this.type = type;
//        this.picByte = picByte;
//
//    }
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private Long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "type")
//    private String type;
//
//        //image bytes can have large lengths so we specify a value
//
//        //which is more than the default length for picByte column
//
//    @Column(name = "picByte", length = 1000)
//    private byte[] picByte;
//
//    public String getName() {
//
//        return name;
//
//    }
//
//    public void setName(String name) {
//
//        this.name = name;
//
//    }
//
//    public String getType() {
//
//        return type;
//
//    }
//
//    public void setType(String type) {
//
//        this.type = type;
//
//    }
//
//    public byte[] getPicByte() {
//
//        return picByte;
//
//    }
//
//    public void setPicByte(byte[] picByte) {
//
//        this.picByte = picByte;
//
//    }
//
//    public void setUser(User u) {
//
//    }
//
//    @OneToOne
//    private User user ;
//
//
//
//

}
