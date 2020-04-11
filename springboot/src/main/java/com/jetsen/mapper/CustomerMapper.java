package com.jetsen.mapper;

import com.jetsen.entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author NICKEL
 */
@Mapper
public interface CustomerMapper {
    @Insert("insert into customer(firstName,lastName,memo) values(#{firstName},#{lastName},#{memo})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Customer customer);

    @Select("select id, firstName, lastName, memo from customer where id=#{id}")
    Customer selectByPrimaryKey(long id);

    @Select("select id, firstName, lastName, memo from customer")
    List<Customer> selectAllCustomer();
}
