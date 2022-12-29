package com.playground.mybatis.dto;

import com.playground.mybatis.model.Person;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public static @Nullable PersonDto from(@Nullable Person person) {
        return person == null ? null : PersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .build();
    }

    public static @Nullable Person toPerson(@Nullable PersonDto dto) {
        return dto == null ? null : Person.builder()
                .id(dto.id)
                .firstName(dto.firstName)
                .lastName(dto.lastName)
                .email(dto.email)
                .build();
    }
}
