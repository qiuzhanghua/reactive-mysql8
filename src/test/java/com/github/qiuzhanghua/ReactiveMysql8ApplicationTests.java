package com.github.qiuzhanghua;

import com.github.qiuzhanghua.domain.Person;
import com.github.qiuzhanghua.service.PeopleService;
import com.mysql.cj.xdevapi.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveMysql8ApplicationTests {

  @Autowired
  PeopleService service;

  @Test
  public void contextLoads() {
  }

//  @Test
  public void testSave() {
    Person p = new Person();
    p.setName("邱张华");
    Long id = service.savePerson(p);
    assertNotNull(id);
  }
}
