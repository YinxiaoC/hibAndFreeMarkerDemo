package com.demo.Dao;

import com.demo.Utils.QueryResult;
import com.demo.domain.Customer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @BelongsProject: hibAndFreeMarkerDemo
 * @BelongsPackage: com.demo.Dao
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-21 09:43
 * @Description:
 */
public class UserDaoTest {

    private UserDao userDao = new UserDao();
    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setName("222");
        customer.setAddress("222");
        userDao.save(customer);
    }

    @Test
    public void update() {
        Customer customer = userDao.getById(1);
        customer.setName("测试update");
        userDao.update(customer);
    }

    @Test
    public void delete() {
        userDao.delete(3);
    }

    @Test
    public void getById() {
        System.out.println(userDao.getById(2));
    }

    @Test
    public void findAll() {
        List<Customer> list = userDao.findAll();
        for (Customer customer:
                list) {
            System.out.println(customer);
        }

    }

    @Test
    public void findAll1() {
        //索引从0开始，最多显示10个
        QueryResult queryResult = userDao.findAll(0,10);
        System.out.println("总记录数:"+queryResult.getCount());
        for (Customer customer :
                (List<Customer>)queryResult.getList()) {
            System.out.println(customer);
        }
    }
}