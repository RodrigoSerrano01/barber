package br.com.v1.barber.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
//@Document(collection = "user")
public abstract class User {

    private String name;
    private String date;
    private String CPF;
    private String phone;
    private String email;
}
