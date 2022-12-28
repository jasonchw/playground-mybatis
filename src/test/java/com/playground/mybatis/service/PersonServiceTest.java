package com.playground.mybatis.service;

import com.playground.mybatis.dto.PersonDto;
import com.playground.mybatis.mapper.PersonMapper;
import com.playground.mybatis.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PersonServiceTest {
    private PersonService personService;

    @Mock
    private PersonMapper mockPersonMapper;

    @BeforeEach
    public void beforeEach() {
        openMocks(this);
        personService = new PersonService(mockPersonMapper);
    }

    @Test
    void findById() {
        // given
        final Person person = Person.builder().build();

        when(mockPersonMapper.findById(anyLong())).thenReturn(person);

        // when
        final Long id = 111111L;
        PersonDto personDto = personService.findById(id);

        // then
        assertNotNull(personDto);

        verify(mockPersonMapper).findById(anyLong());
        verifyNoMoreInteractions(mockPersonMapper);
    }

    @Test
    void findByEmail() {
        // given
        final Person person = Person.builder().build();

        when(mockPersonMapper.findByEmail(anyString())).thenReturn(person);

        // when
        final String email = "first.last@gmail.com";
        PersonDto personDto = personService.findByEmail(email);

        // then
        assertNotNull(personDto);

        verify(mockPersonMapper).findByEmail(anyString());
        verifyNoMoreInteractions(mockPersonMapper);
    }

    @Test
    void insert() {
        // given
        final Long id = 111111L;
        final int count = 1;
        final Person person = Person.builder().build();

        when(mockPersonMapper.insert(any(Person.class))).thenAnswer((Answer<Integer>) invocation -> {
            Person persistedPerson = invocation.getArgument(0);
            persistedPerson.setId(id);
            return count;
        });
        when(mockPersonMapper.findById(anyLong())).thenReturn(person);

        // when
        final PersonDto personDto = PersonDto.builder().build();
        PersonDto result = personService.insert(personDto);

        // then
        assertNotNull(result);

        verify(mockPersonMapper).insert(any(Person.class));
        verify(mockPersonMapper).findById(anyLong());
        verifyNoMoreInteractions(mockPersonMapper);
    }

    @Test
    void update() {
        // given
        final int count = 1;
        final Person person = Person.builder().build();

        when(mockPersonMapper.update(any(Person.class))).thenReturn(count);
        when(mockPersonMapper.findById(anyLong())).thenReturn(person);

        // when
        final Long id = 111111L;
        final PersonDto personDto = PersonDto.builder()
                .id(id)
                .build();
        PersonDto result = personService.update(personDto);

        // then
        assertNotNull(result);

        verify(mockPersonMapper).update(any(Person.class));
        verify(mockPersonMapper).findById(anyLong());
        verifyNoMoreInteractions(mockPersonMapper);
    }
}
