package br.com.v1.barber.domain;


import br.com.v1.barber.enumerator.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
//@Document(collection = "user")
public abstract class User {
    @NotBlank
    private String name;
    @NotBlank
    private String date;
    @NotBlank
    private String CPF;
    @NotBlank
    private String phone;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max =50)
    private String password;

    private UserRole userRole;
}
