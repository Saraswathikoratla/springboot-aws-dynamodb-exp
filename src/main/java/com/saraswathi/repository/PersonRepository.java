package com.saraswathi.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.saraswathi.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonRepository {
    @Autowired
    private DynamoDBMapper mapper;

    public Person addPerson(Person person){
        mapper.save(person);
        return person;
    }
    public Person getPerson(String personId){
       return mapper.load(Person.class,personId);
    }

    public String deletPerson(Person person){
        mapper.delete(person);
        return "person removed from db";
    }
    public String editPerson(Person person){
        mapper.save(person,dynamoDBSaveExpression(person));
        return "record updated";
        
    }
    private DynamoDBSaveExpression dynamoDBSaveExpression(Person person){
        DynamoDBSaveExpression dynamoDBSaveExpression=new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue>map=new HashMap<>();
        map.put("personId",new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
        dynamoDBSaveExpression.setExpected(map);
        return dynamoDBSaveExpression;
    }
}
