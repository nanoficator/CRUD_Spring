package dao;

import model.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class RoleDaoHibernate implements RoleDao {

    private static Configuration configuration;

    @Autowired
    DBHelper dbHelper;

    public RoleDaoHibernate() {
        this.configuration = dbHelper.getConfiguration();
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<Role> getAllData() throws SQLException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Role> roles = session.createQuery("FROM Role").list();
        transaction.commit();
        session.close();
        return roles;
    }

    @Override
    public void deleteAllData() throws SQLException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Role");
        transaction.commit();
        session.close();
    }

    @Override
    public void addData(Role role) throws SQLException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(role);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteData(Role role) throws SQLException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
        session.close();
    }

    @Override
    public Role getDataByID(Long id) throws SQLException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Role WHERE id = :id");
        query.setParameter("id", id);
        Role roleFromDB = (Role) query.uniqueResult();
        transaction.commit();
        session.close();
        return roleFromDB;
    }

    @Override
    public Role getDataByName(String name) throws SQLException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Role WHERE name = :name");
        query.setParameter("name", name);
        Role roleFromDB = (Role) query.uniqueResult();
        transaction.commit();
        session.close();
        return roleFromDB;
    }

    @Override
    public void updateData(Role changedRole) throws SQLException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(changedRole);
        transaction.commit();
        session.close();
    }
}
