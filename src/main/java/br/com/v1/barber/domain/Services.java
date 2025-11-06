package br.com.v1.barber.domain;


import br.com.v1.barber.enumerator.ServiceTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "services")
public class Services {
    @Id
    private String id;
    private String name;
    private Float value;
    private ServiceTime serviceTime;

}
