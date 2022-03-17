package com.DPC.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor

@RequiredArgsConstructor
@Table(name = "archive")
public class Archive implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @NonNull
    @Column(name = "Description")
    private String description;

    @NonNull
    @Column(name = "date_archivage")
    private Date date_archivage;
    @NonNull
    @Column(name = "event_name")
    private String event_name;
    @NonNull
    @Column(name = "statut")
    private Boolean statut;
    @NonNull
    @Column(name = "date")
    private Date date;

    @NonNull
    @Column(name = "DescriptionEvent")
    private String descriptionEvent;

    @NonNull
    @Column(name = "image")
    private String image;

    @NonNull
    @Column(name = "budget")
    private Double budget;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Evenement evenement;

    @Setter(value = AccessLevel.NONE)
    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Setter(value = AccessLevel.NONE)
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();




}
