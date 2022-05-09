package com.DPC.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor

@Table(name = "adress")
public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @NonNull
    @Column(name = "zip")
    private String zip;
    @NonNull
    @Column(name = "city_name")
    private String city_name;

    @NonNull
    @Column(name = "home_adress")
    private String home_adress;

    @NonNull
    @Column(name = "work_adress")
    private String work_adress;

    @NonNull
    @Column(name = "gouvernorat")
    private String gouvernorat;






//
//    @OneToOne(mappedBy = "adress")
//    private Localisation localisation;
//
//    @OneToOne(mappedBy = "adress")
//    private Evenement evenement;
//
//    @OneToOne(mappedBy = "adress")
//    private Association association;


    @OneToOne(mappedBy = "adress")
    private UserDetails userDetails;

    @Setter(value = AccessLevel.NONE)
    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "creat_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creat_AT = new Date();

    @Setter(value = AccessLevel.NONE)
    @UpdateTimestamp
    @Column(name = "updat_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatAt = new Date();


}
