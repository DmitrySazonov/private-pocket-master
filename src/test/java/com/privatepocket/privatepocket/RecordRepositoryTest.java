package com.privatepocket.privatepocket;

import com.privatepocket.privatepocket.storage.Record;
import com.privatepocket.privatepocket.storage.RecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecordRepositoryTest {

    @Autowired
    RecordRepository recordRepository;

    @Test
    public void saveRecord() throws Exception {
        recordRepository.save(new Record());
    }
}
