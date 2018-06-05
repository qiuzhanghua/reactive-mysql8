package com.github.qiuzhanghua.web;

import com.github.qiuzhanghua.domain.Person;
import com.github.qiuzhanghua.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/people")
public class PeopleController {
  @Autowired
  PeopleService peopleService;

  @GetMapping("")
  public Flux<Person> all() {
    return peopleService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Person> getById(@PathVariable Long id) {
    return peopleService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@RequestBody Person person) {
//    System.out.println(person);
    peopleService.savePerson(person);
  }

  @PutMapping
  public void put(@RequestBody Person person)  {
    peopleService.updatePerson(person);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable(value = "id") Long id)  {
    peopleService.deletePerson(id);
  }

}
