package com.tobilko.web.repository;

import com.tobilko.web.Runner;
import com.tobilko.web.entity.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static com.tobilko.web.stuff.Constant.HIBERNATE_CONFIG_FILE;

public class ORMFeedbackRepository implements Repository<Feedback> {

    private final Session session;

    {
        SessionFactory factory = new Configuration()
                .configure(HIBERNATE_CONFIG_FILE.get())
                .addPackage(Runner.class.getPackage().getName())
                .addAnnotatedClass(Feedback.class)
                .buildSessionFactory();
        session = factory.openSession();
    }

    @Override
    public void save(Feedback feedback) {
        Transaction transaction = session.beginTransaction();
        session.save(feedback);
        transaction.commit();
    }

    public List<Feedback> getAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Feedback> query = builder.createQuery(Feedback.class);

        return session.createQuery(query.select(query.from(Feedback.class))).getResultList();
    }

}
