
0. 모든 작업은 dba(root)로 한다.
```sh
# mysql -u root -p
```sh

1. 데이터베이스 생성
```sh
MariaDB [(none)]> create database webdb;
MariaDB [(none)]> show databases;
```sh

2. 사용자 생성
```sh
MariaDB [(none)]> create user 'webdb'@'localhost' identified by 'webdb';
```sh

3. 권한주기
```sh
MariaDB [(none)]> grant all privileges on webdb.* to 'webdb'@'localhost';
MariaDB [(none)]> flush privileges;
```sh
