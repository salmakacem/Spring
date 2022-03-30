package com.DPC.spring.entities;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "imagetable")
public class ImageModel  implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "picByte", length = 1000)
    @Lob
    private byte[] picByte;


    @Column(name = "archiver")
    private Boolean archiver ;



//    public Boolean getArchiver() {
//        return archiver;
//    }
//
//    public void setArchiver(Boolean archiver) {
//        this.archiver = archiver;
//    }


    @OneToOne
    private User user ;

//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public byte[] getPicByte() {
//        return picByte;
//    }
//
//    public void setPicByte(byte[] picByte) {
//        this.picByte = picByte;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
