package com.toregozhin.springcourse.dao;

import com.toregozhin.springcourse.model.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {

        Session session = entityManager.unwrap(Session.class);

//        //Получить всех людей. Запрос
//        List<Person> people = entityManager.createQuery("select p from Person p", Person.class)
//                .getResultList();
//
//        //N запрасов к БД
//        for(Person p : people) {
//            System.out.println("Person " + p.getName() + " HAS: " + p.getItems());
//        }

        //Solution
        List<Person> people = session.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.items")
                .getResultList();
        for (Person person : people) {
            System.out.println("Person: " + person.getName() + " HAS " + person.getItems());
        }
    }

}
