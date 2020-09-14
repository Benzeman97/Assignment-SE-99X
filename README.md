# Assignment-SE-99X
Assignment For SE Role

production-price-calculation app is running under the port 9090

run spring cloud config server  which is runing on port number 8888 before starting production-calculation-app


Database is implemnted in Oracle11g with following configurations. Configuraion file is stored in here https://github.com/Benzeman97/Assignment-Config-Repo 

spring:
  datasource:
    url: "jdbc:oracle:thin:@localhost:1521:orcl"
    username: ASSIGNDB
    password: *****
    driver-class-name: oracle.jdbc.OracleDriver
  jpa:
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.Oracle10gDialect
