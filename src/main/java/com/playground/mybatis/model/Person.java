package com.playground.mybatis.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Person extends AuditAwareModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @Builder
    public Person(Long id, String firstName, String lastName, String email,
                  String createdBy, LocalDateTime createdOn, String updatedBy, String updatedUsing, LocalDateTime updatedOn) {
        super(createdBy, createdOn, updatedBy, updatedUsing, updatedOn);

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
