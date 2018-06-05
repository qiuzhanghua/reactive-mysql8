# Try X DevAPI of MySQL 8 with Spring WebFlux

Perhaps it's semi-reactive. :)

#Install MySQL 8.0

first of all

## Create Schema
```sql
CREATE SCHEMA `app` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

## Create Table
```sql
create table people
(
	id int auto_increment
		primary key,
	name char(254) null
);

```

## Create User
```sql
CREATE USER 'app'@'%' IDENTIFIED BY 'app';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, RELOAD, FILE, REFERENCES, INDEX,
    ALTER, SHOW DATABASES, SUPER, LOCK TABLES, CREATE VIEW, SHOW VIEW 
    on app.* TO 'app'@'%';
GRANT RELOAD on *.* TO 'app'@'%';
FLUSH PRIVILEGES;
```

# Spring Boot and WebFlux

## Define Beans of MySQL
```java
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

```

## PeopleService and PeopleController.


## HTTPie and CRUD

### Add 3 People
```bash
http POST localhost:8080/people name=Daniel
```
and you get
```bash
HTTP/1.1 201 Created
content-length: 0
```
```bash
http POST localhost:8080/people name=Summer
```
and you get
```bash
HTTP/1.1 201 Created
content-length: 0
```
```bash
http POST localhost:8080/people name=邱张华
```
and you get
```bash
HTTP/1.1 201 Created
content-length: 0
```


### Show all people

```bash
http localhost:8080/people
```
and you will get
```json
[
    {
        "id": 1,
        "name": "Daniel"
    },
    {
        "id": 2,
        "name": "Summer"
    },
    {
        "id": 3,
        "name": "邱张华"
    }
]
```
### Add and Modify Person
```bash
http POST localhost:8080/people name=邱张华

http PUT localhost:8080/people id=4 name=qiuzhanghua

```

### Delete Person
```bash
http DELETE localhost:8080/people/4
```

