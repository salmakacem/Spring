package com.DPC.spring.DTO;

import com.DPC.spring.entities.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDto {
    private long id;

    private ERole name;
}
