package com.tool.password;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface PasswordRepo {
    @Select("SELECT * FROM passwords WHERE environment = #{environment} AND active = 1 ORDER BY type")
    List<Password> getPassByEnvironment(String environment);
    @Select("SELECT * FROM passwords")
    List<Password> getALlPassword();
    @Insert("INSERT INTO passwords(environment,type,username,password,updatedAt,updatedBy) VALUES (#{environment},#{type},#{username},#{password},#{updatedAt},#{updatedBy})")
    void insertPassword(Password password);
    @Update("UPDATE passwords SET password=#{password},updatedAt=#{updatedAt},updatedBy=#{updatedBy} WHERE type = #{type} AND username = #{username}")
    void updatePassword(Password password);
    @Delete("UPDATE passwords SET active = 0  WHERE username=#{username} AND type=#{type}")
    void deletePassword(Password password);
}
