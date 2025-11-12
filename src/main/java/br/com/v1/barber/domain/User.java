package br.com.v1.barber.domain;


import br.com.v1.barber.enumerator.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetailsService;

@Data
@NoArgsConstructor
//@Document(collection = "user")
public abstract class User  {
    @NotBlank
    private String name;
    @NotBlank
    private String date;
    @NotBlank
    private String CPF;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    private String password;

    private UserRole userRole;
}
