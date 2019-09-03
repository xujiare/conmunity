package com.sb.stu.spstu.mapper;

import com.sb.stu.spstu.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into gitUser(account_id,name,token,gmt_create,modified) values(#{accountId},#{name},#{token},#{gmtCreate},#{modified})")
    void addUser(User user);
    @Select("select * from gitUser where token=#{token}")
    User findBytoken(@Param("token") String token);
}
