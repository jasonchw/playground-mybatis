package com.playground.mybatis.dto;

import com.playground.mybatis.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDtoTest {
    @Test
    void fromWithNonNullPerson() {
        // given
        final Long id = 111111L;
        final String firstName = "First";
        final String lastName = "Last";
        final String email = "first.last@gmail.com";

        // when
        final Person person = Person.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();

        final PersonDto personDto = PersonDto.from(person);

        // then
        assertNotNull(personDto);
        assertEquals(id, personDto.getId());
        assertEquals(firstName, personDto.getFirstName());
        assertEquals(lastName, personDto.getLastName());
        assertEquals(email, personDto.getEmail());
    }

    @Test
    void fromWithNullPerson() {
        // given
        // n/a

        // when
        final Person person = null;
        final PersonDto personDto = PersonDto.from(person);

        // then
        assertNull(personDto);
    }

    @Test
    void toPersonWithNonNullPersonDto() {
        // given
        final Long id = 111111L;
        final String firstName = "First";
        final String lastName = "Last";
        final String email = "first.last@gmail.com";

        // when
        final PersonDto personDto = PersonDto.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();

        final Person person = PersonDto.toPerson(personDto);

        // then
        assertNotNull(person);
        assertEquals(id, person.getId());
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(email, person.getEmail());
    }

    @Test
    void toPersonWithNullPersonDto() {
        // given
        // n/a

        // when
        final PersonDto personDto = null;
        final Person person = PersonDto.toPerson(personDto);

        // then
        assertNull(person);
    }
}
