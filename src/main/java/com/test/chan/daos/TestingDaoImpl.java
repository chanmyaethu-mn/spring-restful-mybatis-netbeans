/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chan.daos;

import com.test.chan.beans.Testing;
import com.test.chan.mappers.TestingMapper;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CHANMYAETHU
 */

@Repository
@Transactional
public class TestingDaoImpl implements TestingDao{

    @Autowired
    private TestingMapper testingMapper;
    
    @Override
    public void insert() throws Exception {
        testingMapper.insert(new Testing("" + new Random().nextInt(), "" + new Random().nextInt()));
        testingMapper.insert(new Testing()); // this will throw an exception
    }
    
}
