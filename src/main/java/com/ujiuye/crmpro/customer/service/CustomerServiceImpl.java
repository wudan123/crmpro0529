package com.ujiuye.crmpro.customer.service;

import com.ujiuye.crmpro.customer.mapper.CustomerMapper;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.customer.pojo.CustomerExample;
import com.ujiuye.crmpro.customer.pojo.CustomerExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> list(int type, String keyword) {
        CustomerExample example=new CustomerExample();
        Criteria criteria = example.createCriteria();
        if (type==1){
            criteria.andCompanypersonLike("%"+keyword+"%");


        }
        if (type==2){

            criteria.andComnameLike("%"+keyword+"%");
        }

        return customerMapper.selectByExample(example);
    }

    @Override
    public Customer show(int id) {

        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(Customer customer) {
        customer.setAddtime(new Date());

        return customerMapper.insert(customer);
    }

    @Override
    public int update(Customer customer) {

        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public int remove(List<Integer> ids) {
        CustomerExample example=new CustomerExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return customerMapper.deleteByExample(example);
    }

    @Override
    public List<Customer> getByName(String name) {
        CustomerExample example=new CustomerExample();
        Criteria criteria = example.createCriteria();
        criteria.andComnameLike("%"+name+"%");

        return customerMapper.selectByExample(example);
    }
}
