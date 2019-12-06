package com.expleo.webcm.dao;

import com.expleo.webcm.entity.expleodb.History;
import com.expleo.webcm.entity.expleodb.Skill;
import com.expleo.webcm.entity.expleodb.UserExpleo;
import com.expleo.webcm.entity.expleodb.UserSkill;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class UserSkillDAOImpl implements UserSkillDAO {

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserSkill> getUserSkill() {

        Session currentSession = sessionFactory.openSession();

        currentSession.beginTransaction();

        Query<UserSkill> theQuery = currentSession.createQuery("from UserSkill", UserSkill.class);

        List<UserSkill> userSkills = theQuery.getResultList();


        currentSession.getTransaction().commit();

        currentSession.close();

        return userSkills;
    }



    @Override
    public void saveUserSkill(int idUserExpleo, int idSkill) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        UserSkill userSkill = new UserSkill(session.get(Skill.class, idSkill),session.get(UserExpleo.class, idUserExpleo));
        session.save(userSkill);
        session.flush();

        Query usQuery = session.createQuery("from UserSkill where user.id= :id and skill.id=:skillId");
        usQuery.setParameter("id", idUserExpleo);
        usQuery.setParameter("skillId", idSkill);

        UserSkill us = (UserSkill) usQuery.getSingleResult();
        session.merge(new History(us.getId(), 1, dateFormat.format(Calendar.getInstance().getTime())));
        session.flush();

        session.getTransaction().commit();
        session.close();

    }


    public void saveUserSkill(int idUser, int idSkill, int eval) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query userSkillQuery = session.createQuery("select us from UserSkill us where user.id=: idUser and skill.id= :idSkill");
        userSkillQuery.setParameter("idUser", idUser);
        userSkillQuery.setParameter("idSkill", idSkill);


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            UserSkill userSkill = (UserSkill) userSkillQuery.getSingleResult();
            session.flush();
            userSkill.setEvaluation(eval);
            boolean update = false;

            try {
                Query historyQuery = session.createQuery("from History where idUserSkill=: idUser");
                historyQuery.setParameter("idUser", userSkill.getId());

                List<History> history = new LinkedList<History>(historyQuery.list());
                session.flush();

                for(History temp:history){
                    if(temp.getDate().equals(userSkill.getDataEvaluare())){
                        temp.setEvaluare(eval);
                        session.update(temp);
                        session.flush();
                        update = true;
                        break;
                    }
                }
                if(!update){
                    session.save(new History(userSkill.getId(), eval, dateFormat.format(Calendar.getInstance().getTime())));
                    session.flush();
                }

            }catch (NoResultException e){
                System.out.println("UserSkillDAOImpl.saveUserSkill = no result historyQuery");
            }

            userSkill.setDataEvaluare(dateFormat.format(Calendar.getInstance().getTime()));
            session.update(userSkill);
            session.flush();

        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public List<UserSkill> getUserSkillByUser(UserExpleo userExpleo) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        Query query = session.createQuery("from UserSkill where ID_user = :id");
        Query query = session.createQuery("SELECT us FROM UserSkill us JOIN FETCH us.skill JOIN FETCH us.user where us.user.id = :id");
        query.setParameter("id", userExpleo.getId());

        List<UserSkill> result = (List<UserSkill>) query.list();

//        for (UserSkill userSkill : result){
//            Hibernate.initialize(userSkill.getSkill());
//        }

        session.getTransaction().commit();

        session.close();

        return result;
    }

    @Override
    public List<UserSkill> getUserSkillBySkill(Skill skill) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from UserSkill where ID_skill = :id");

        query.setParameter("id", skill.getIdSkill());

        List<UserSkill> result = (List<UserSkill>) query.list();

        for (UserSkill userSkill : result){
            Hibernate.initialize(userSkill.getSkill());
            Hibernate.initialize(userSkill.getUser());
        }

        session.getTransaction().commit();

        session.close();

        return result;
    }

    @Override
    public void getUserByEvaluation(List<UserSkill> userSkills, int eval){

        List<UserSkill> userSkillsLast = new LinkedList<>();

        for(UserSkill temp: userSkills) {
            if (temp.getEvaluation() < eval) {
                userSkillsLast.add(temp);
            }
        }
        userSkills.removeAll(userSkillsLast);
        userSkillsLast.clear();

//        return userSkillsLast;

    }


    @Override
    public void removeUserSkill(int idUserExpleo, int idSkill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserSkill userSkill = new UserSkill(session.get(Skill.class, idSkill),session.get(UserExpleo.class, idUserExpleo));

        session.clear();

        session.delete(userSkill);

        session.getTransaction().commit();

        session.close();

    }

}
