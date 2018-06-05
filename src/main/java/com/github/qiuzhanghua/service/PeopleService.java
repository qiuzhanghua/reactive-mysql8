package com.github.qiuzhanghua.service;

import com.github.qiuzhanghua.domain.Person;
import com.mysql.cj.xdevapi.InsertResult;
import com.mysql.cj.xdevapi.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PeopleService {

  @Autowired
  @Qualifier("people")
  Table people;

  // Don't use this method in prod env
  // and It seems fetchAll not fully reactive
  public Flux<Person> findAll() {
    return Mono.fromFuture(people.select().executeAsync())
        .flatMapIterable(rows -> rows.fetchAll()).map(row -> {
          return new Person(row.getLong("id"), row.getString("name"));
        });
  }

  public Flux<Person> findByName(String name) {
    return Mono.fromFuture(people.select("id", "name")
        .where("name like :name")
        .orderBy("name")
        .bind("name", name).executeAsync())
        .flatMapIterable(rows -> rows.fetchAll()).map(row -> {
          return new Person(row.getLong("id"), row.getString("name"));
        });
  }

  public Mono<Person> findById(Long id) {
    return Mono.fromFuture(people.select("id", "name")
        .where("id = :id")
        .orderBy("id")
        .bind("id", id).executeAsync())
        .flatMapIterable(rows -> rows.fetchAll()).map(row -> {
          return new Person(row.getLong("id"), row.getString("name"));
        }).next();
  }

  public Long savePerson(Person person) {
    InsertResult execute = people.insert("id", "name").values(person.getId(), person.getName()).execute();
    return execute.getAutoIncrementValue();
  }

  public Long deletePerson(Long id) {
    return people.delete().where("id = :id").bind("id", id).execute().getAffectedItemsCount();
  }

  public Long updatePerson(Person person) {
    return people.update().where("id = :id").bind("id", person.getId()).set("name", person.getName()).execute().getAffectedItemsCount();
  }
}
