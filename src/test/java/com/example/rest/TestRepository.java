package com.example.rest;

import com.example.rest.model.JsonList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TestRepository {

    @Autowired
    private TestEntityManager entityManager;

    private JsonList jsonList;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testRepositoryQueryByHash() {
        // given
        jsonList = new JsonList("hash","value");
        entityManager.persist(jsonList);
        entityManager.flush();

        // when
        JsonList found = employeeRepository.findByHash(jsonList.getHash());

        // then
        assertThat(found.getHash()).isEqualTo(jsonList.getHash());
    }

    @Test
    public void testGettersSetters(){
        jsonList = new JsonList();

        List<java.io.Serializable> inputData = new ArrayList<>();
        inputData.add(1);
        inputData.add("hash");
        inputData.add("value");

        jsonList.setId((Integer) inputData.get(0));
        jsonList.setHash((String) inputData.get(1));
        jsonList.setValue((String) inputData.get(2));

        List<java.io.Serializable> outputData = new ArrayList<>();
        outputData.add(jsonList.getId());
        outputData.add(jsonList.getHash());
        outputData.add(jsonList.getValue());

        assertEquals(inputData,outputData);
    }
}