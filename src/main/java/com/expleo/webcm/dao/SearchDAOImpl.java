package com.expleo.webcm.dao;

import com.expleo.webcm.entity.expleodb.UserExpleo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchDAOImpl implements SearchDAO {

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserExpleo> searchUser(String text) {
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);

        Transaction tx = fullTextSession.beginTransaction();

        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder()
                .forEntity(UserExpleo.class).get();

        org.apache.lucene.search.Query query = qb
                .keyword()
                .onFields("nume", "prenume", "email", "numarMatricol")
                .matching(text)
                .createQuery();

        org.hibernate.query.Query hibQuery =
                fullTextSession.createFullTextQuery(query, UserExpleo.class);

        List result = hibQuery.list();


        for(Object o : result){
            System.out.println( "-----------------" + o );
        }


        tx.commit();
        session.close();
        return result;
    }
}
