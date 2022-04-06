package com.DPC.spring.entities;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@Data
@Table(name = "imagetable")
public class ImageModel  implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    public ImageModel() {
    }

    public ImageModel(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
