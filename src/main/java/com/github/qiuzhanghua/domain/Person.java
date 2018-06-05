package com.github.qiuzhanghua.domain;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

  private static final long serialVersionUID = -1661198087445313175L;

  private Long id;
  private String name;

  public Person(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Person() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return id == person.id &&
        Objects.equals(name, person.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "Person{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
