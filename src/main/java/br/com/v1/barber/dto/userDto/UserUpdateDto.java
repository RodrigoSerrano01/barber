package br.com.v1.barber.dto.userDto;

import br.com.v1.barber.enumerator.UserRole;
import br.com.v1.barber.validation.DateDDMMYYYY;
import br.com.v1.barber.validation.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")
    // @DateDDMMYYYY
    private String date;
    @NotBlank
    @CPF(message = "Invalid CPF")
    private String CPF;
    @NotBlank
    @NotBlank(message = "Phone cannot be blank")
    @Phone
    private String phone;
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    private String password;

    private UserRole userRole;
}
