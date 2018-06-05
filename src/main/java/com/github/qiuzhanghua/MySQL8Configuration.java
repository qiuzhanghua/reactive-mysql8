package com.github.qiuzhanghua;

import com.mysql.cj.xdevapi.Schema;
import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import com.mysql.cj.xdevapi.Table;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySQL8Configuration {

  @Bean
  public Session mySession() {
    return new SessionFactory().getSession("mysqlx://localhost:33060/app?user=app&password=app");
  }

  @Bean
  public Schema mySchema() {
    return mySession().getSchema("app");
  }

  @Bean
  public Table people() {
    return mySchema().getTable("people");
  }
}
