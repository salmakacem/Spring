
package com.DPC.spring.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DeviceNotif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String webToken;
    private String iosToken;
    private String androidToken;
    private String userId;
    @Transient
    private User user;
}
