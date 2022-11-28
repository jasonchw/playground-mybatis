package com.playground.mybatis.service;

import com.playground.mybatis.dto.PersonDto;
import com.playground.mybatis.mapper.PersonMapper;
import com.playground.mybatis.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PersonService {
    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public PersonDto findById(Long id) {
        Person person = personMapper.findById(id);
        return PersonDto.from(person);
    }

    public PersonDto findByEmail(String email) {
        Person person = personMapper.findByEmail(email);
        return PersonDto.from(person);
    }

    public PersonDto insert(PersonDto personDto) {
        Person person = PersonDto.toPerson(personDto);
        int count = personMapper.insert(person);

        Person persistedPerson = personMapper.findById(person.getId());
        return PersonDto.from(persistedPerson);
    }

    public PersonDto update(PersonDto personDto) {
        Person person = PersonDto.toPerson(personDto);
        int count = personMapper.update(person);

        Person persistedPerson = personMapper.findById(person.getId());
        return PersonDto.from(persistedPerson);
    }
}
