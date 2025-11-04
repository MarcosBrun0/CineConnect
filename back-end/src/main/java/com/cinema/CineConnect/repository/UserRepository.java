package com.cinema.CineConnect.repository;

import com.cinema.CineConnect.model.DTO.UserRecordRoleName;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import com.cinema.CineConnect.model.DTO.UserRecordRoleId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public List<UserRecordRoleId> findAllClients() {
        return jdbcClient.sql("SELECT name, email FROM users WHERE role_id = 5")
                .query(UserRecordRoleId.class)
                .list();
    }


    public Optional<UserRecordRoleName> findByEmailRoleName(String email) {
        return jdbcClient.sql("""
            SELECT users.id,users.name,email,password,birth_date,roles.name as roleName
            FROM users 
            INNER JOIN roles 
            ON users.role_id = roles.id 
            WHERE email = :email
""")
                .param("email", email)
                .query(UserRecordRoleName.class)
                .optional();
    }
        public void saveUser(UserRecordRoleId user) {
            jdbcClient.sql("""
        INSERT INTO users (name, role_id, email, password, birth_date)
            VALUES (:name,:role_id,:email,:password,:birth_date)
    """)
                .param("name", user.name())
                .param("email", user.email())
                .param("password",user.password())
                .param("role_id",user.role())
                .param("birth_date", LocalDate.parse(user.birth_date()))
                .update();
    }

    public List<UserRecordRoleName> findAllUsersRoleName() {
        return jdbcClient.sql("""
         
            SELECT users.id,users.name,email,password,birth_date,roles.name as roleName
            FROM users INNER JOIN roles 
            ON users.role_id = roles.id 
            """)
                .query(UserRecordRoleName.class)
                .list();
    }





}
