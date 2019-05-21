package com.demo.Dao;

import com.demo.Utils.HibernateUtils;
import com.demo.Utils.QueryResult;
import com.demo.domain.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

import java.util.List;

/**
 * @BelongsProject: hibAndFreeMarkerDemo
 * @BelongsPackage: com.demo.Dao
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-20 18:09
 * @Description: 实现对数据库完整的操作(CRUD)
 */
public class UserDao {

    /**
     * @描述 save方法
     * @参数 [customer]
     * @返回值 void
     * @创建人 yinxiuchaun
     * @创建时间 2019/5/20
     * @修改人和其它信息
     */
    public void save(Customer customer) {
        Session session = HibernateUtils.openSession();
        try {
            //开启事务
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            //提交事务
            transaction.commit();
        } catch (RuntimeException e) {
            //回滚事务
            session.getTransaction().rollback();
            throw e;

        } finally {
            session.close();
        }
    }

    /**
     * @描述 更新
     * @参数 [customer]
     * @返回值 void
     * @创建人 yinxiuchaun
     * @创建时间 2019/5/20
     * @修改人和其它信息
     */
    public void update(Customer customer) {
        Session session = HibernateUtils.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.update(customer);
            t.commit();
        } catch (RuntimeException e) {
            //回滚
            t.rollback();
            throw e;
        }

    }

    /**
     * @描述 删除
     * @参数 [id]
     * @返回值 void
     * @创建人 yinxiuchaun
     * @创建时间 2019/5/20
     * @修改人和其它信息
     */
    public void delete(int id) {
        Session session = HibernateUtils.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            //Customer customer = getById(id);
            Object customer = session.get(Customer.class,id);
            session.delete(customer);
            t.commit();
        } catch (RuntimeException e) {
            //回滚
            t.rollback();
            throw e;
        }

    }

    /*
     * 根据id查询一个User数据
     */
    public Customer getById(int id) {
        Session session = HibernateUtils.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Customer user = (Customer) session.get(Customer.class, id);// 操作
            tx.commit();
            return user;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Customer> findAll() {
        Session session = HibernateUtils.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Customer");
            List<Customer> list = query.list();
            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * 分页的查询数据列表
     *
     * @param firstResult 从结果列表中的哪个索引开始取数据
     * @param maxResults  最多取多少条数据
     * @return 一页的数据列表
     */

    //@SuppressWarnings("unchecked")
    // 告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息。
    @SuppressWarnings("unchecked")
    public QueryResult findAll(int firstResult, int maxResults) {
        Session session = HibernateUtils.openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            //方式一
            //查询一页的数据
            //通过Hql查询
//            Query query = session.createQuery("from Customer");
//            query.setFirstResult(firstResult);
//            query.setMaxResults(maxResults);
//            List<Customer> list = query.list();
            //方式二
            //方法链
            List<Customer> list = session.createQuery("from Customer ")
                    .setFirstResult(firstResult)
                    .setMaxResults(maxResults).list();
            //总数
            Long cont = (Long) session.createQuery("SELECT COUNT(*) from Customer ")
                    .uniqueResult();
            t.commit();
            return new QueryResult(cont.intValue(), list);
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        }
    }

}
