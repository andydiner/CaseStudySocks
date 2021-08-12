package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//database
@Entity
//springboot
@Component
public class Vendor implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;


    @Id
    @NonNull
    @Column(unique = true) @NotBlank
    String emailAddress;
    @NonNull @NotBlank
    String firstName;
    @NonNull @NotBlank
    String lastName;
    @NonNull @NotBlank
    String phoneNumber;
    @NonNull @NotBlank
    String password;
    String imagePath;
}
