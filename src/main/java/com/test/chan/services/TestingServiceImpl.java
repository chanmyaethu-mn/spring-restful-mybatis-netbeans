package com.test.chan.services;

import com.test.chan.daos.TestingDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author CHANMYAETHU
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestingServiceImpl implements TestingService {
    
    private static final Logger logger = LogManager.getLogger(TestingServiceImpl.class.getSimpleName());

    @Autowired
    private TestingDao testingDao;

//    public void insert() throws Exception {
//        testingMapper.insert(new Testing("" + new Random().nextInt(), "" + new Random().nextInt()));
//        testingMapper.insert(new Testing()); // this will throw an exception
//    }
    @Override
    public void insert() throws Exception {
        logger.debug("Testing Service Insert");
        testingDao.insert();
    }
}
