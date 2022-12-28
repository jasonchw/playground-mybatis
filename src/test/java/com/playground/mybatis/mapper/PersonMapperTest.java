package com.playground.mybatis.mapper;

import com.playground.mybatis.BaseMapperTest;
import com.playground.mybatis.model.Person;
import com.playground.mybatis.util.FixtureUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonMapperTest extends BaseMapperTest {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private DataSource dataSource;

    @Test
    void findById() {
        // given
        final String firstName = "First";
        final String lastName = "Last";
        final String email = String.format("first.last.%s@gamil.com", RandomStringUtils.randomAlphabetic(10).toLowerCase());

        final Long personId = FixtureUtils.newPerson(dataSource, firstName, lastName, email);

        // when
        Person persistedPerson = personMapper.findById(personId);

        // then
        assertNotNull(persistedPerson);
    }

    @Test
    void findByEmail() {
        // given
        final String firstName = "First";
        final String lastName = "Last";
        final String email = String.format("first.last.%s@gamil.com", RandomStringUtils.randomAlphabetic(10).toLowerCase());

        final Long personId = FixtureUtils.newPerson(dataSource, firstName, lastName, email);

        // when
        Person persistedPerson = personMapper.findByEmail(email);

        // then
        assertNotNull(persistedPerson);
    }

    @Test
    void insert() {
        // given
        final String firstName = "First";
        final String lastName = "Last";
        final String email = String.format("first.last.%s@gamil.com", RandomStringUtils.randomAlphabetic(10).toLowerCase());

        // when
        final Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
        personMapper.insert(person);

        Person persistedPerson = personMapper.findById(person.getId());

        // then
        assertNotNull(persistedPerson);
    }

    @Test
    void update() {
        // given
        final String firstName = "First";
        final String lastName = "Last";
        final String email = String.format("first.last.%s@gamil.com", RandomStringUtils.randomAlphabetic(10).toLowerCase());

        final Long personId = FixtureUtils.newPerson(dataSource, firstName, lastName, email);

        // when
        final String updatedFirstName = "Updated First";
        final String updatedLastName = "Updated Last";
        final String updatedEmail = "updated." + email;

        Person persistedPerson = personMapper.findById(personId);

        persistedPerson.setFirstName(updatedFirstName);
        persistedPerson.setLastName(updatedLastName);
        persistedPerson.setEmail(updatedEmail);
        personMapper.update(persistedPerson);

        persistedPerson = personMapper.findById(personId);

        // then
        assertNotNull(persistedPerson);
        assertEquals(updatedFirstName, persistedPerson.getFirstName());
        assertEquals(updatedLastName, persistedPerson.getLastName());
        assertEquals(updatedEmail, persistedPerson.getEmail());
    }
}
