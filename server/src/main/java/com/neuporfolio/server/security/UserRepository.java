package com.neuporfolio.server.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    /*
    自己写太麻烦了！还是用模板吧。
     */
//    public User find(Long id)//根据id查找
//    {
//        return jdbcTemplate.queryForObject("select * from users where id=?", new BeanPropertyRowMapper<>(User.class), id);
//    }
//    public User save(User user)//如果已经存在id则update,如果不存在则insert
//    {
//        User userInDatabase=this.find(user.getId());
//        StringBuilder sql=new StringBuilder();
//        //利用反射，获取User所有成员属性名
//        Field[] fields=user.getClass().getDeclaredFields();//包括私有
//        if(userInDatabase==null)
//        {
//            //sql语句
//            sql.append("insert into users(");
//            //利用反射，获取User所有成员属性名
//            for(Field field:fields)
//            {
//                sql.append(field.getName()).append(",");
//            }
//            //去除最后一个","
//            if(sql.charAt(sql.length()-1)==',')
//                sql.delete(sql.length()-1,sql.length());
//            sql.append(") values(");
//            //加入通配符
//            for(Field field:fields)
//            {
//                sql.append(':').append(field.getName()).append(" ,");
//            }
//            //去除最后一个","
//            if(sql.charAt(sql.length()-1)==',')
//                sql.delete(sql.length()-1,sql.length());
//            sql.append(");");
//            jdbcTemplate.update(String.valueOf(sql),new BeanPropertySqlParameterSource(user));
//        }else {
//            //sql语句
//
//            sql.append("update users set ");
//            for(Field field:fields)
//            {
//                sql.append(field.getName()).append("=:").append(field.getName()).append(" ,");
//            }
//            //去除最后一个","
//            if(sql.charAt(sql.length()-1)==',')
//                sql.delete(sql.length()-1,sql.length());
//            sql.append(" where id=:id;");
//
//            jdbcTemplate.update(String.valueOf(sql),new BeanPropertySqlParameterSource(user));
//        }
//        return user;
//    }
}
