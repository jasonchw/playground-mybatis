package com.playground.mybatis.rest;

import com.playground.mybatis.dto.PersonDto;
import com.playground.mybatis.lang.Result;
import com.playground.mybatis.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/services/persons")
@Slf4j
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/v1/{personId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<PersonDto>> getPersons(@PathVariable("personId") Long personId) {
        logger.debug("getting person by personId: {}", personId);

        PersonDto personDto = personService.findById(personId);

        Result<PersonDto> result = Result.ofSuccess(personDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/v1", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<PersonDto>> createPerson(@RequestBody PersonDto personDto) {
        logger.debug("create person: {}", personDto);

        PersonDto persistedPersonDto = personService.insert(personDto);

        Result<PersonDto> result = Result.ofSuccess(persistedPersonDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/v1", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Result<PersonDto>> updatePerson(@RequestBody PersonDto personDto) {
        logger.debug("update person: {}", personDto);

        PersonDto persistedPersonDto = personService.update(personDto);

        Result<PersonDto> result = Result.ofSuccess(persistedPersonDto);
        return ResponseEntity.ok(result);
    }
}
