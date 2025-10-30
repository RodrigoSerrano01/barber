package br.com.v1.barber.domain;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "client")
public class Client {

    @Id
    private String id;
    private String name;
    private String date;
    private String CPF;
    private String phone;
    private String email;





}
