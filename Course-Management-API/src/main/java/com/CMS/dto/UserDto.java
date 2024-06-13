package com.CMS.dto;

import com.CMS.entities.Progress;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {      //directly expose
    private Long id;

    private String username;

    private String password;

    private String role;

    private float progress;
}
