package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class AuthGroup {

    private static final long serialVersionUID = 8349938216666765198L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long aID;
    @NonNull @NotBlank
    String aUsername;
    @NonNull @NotBlank
    String aAuthGroup;
}
