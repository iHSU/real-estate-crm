package com.crm.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crm.dao.INoticeDAO;
import com.crm.hibernate.BaseHibernateDAO;
import com.crm.model.Notice;

/**
 * A data access object (DAO) providing persistence and search support for
 * Notice entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.crm.model.Notice
 * @author MyEclipse Persistence Tools
 */

public class NoticeDAO extends BaseHibernateDAO implements INoticeDAO {
    private static final Logger log = LoggerFactory.getLogger(NoticeDAO.class);
    // property constants
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String CLASSIFY = "classify";
    public static final String AUTHOR = "author";

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#save(com.crm.model.Notice)
     */
    public void save(Notice transientInstance) {
	log.debug("saving Notice instance");
	Session session;
	try {
	    session = getSession();
	    session.beginTransaction();
	    session.save(transientInstance);
	    session.getTransaction().commit();
	    session.close();
	    log.debug("save successful");
	} catch (RuntimeException re) {
	    log.error("save failed", re);
	    throw re;
	}
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#delete(com.crm.model.Notice)
     */
    public void delete(Notice persistentInstance) {
	log.debug("deleting Notice instance");

	Session session;
	try {
	    session = getSession();
	    session.beginTransaction();
	    session.delete(persistentInstance);
	    session.getTransaction().commit();
	    session.close();
	    log.debug("delete successful");
	} catch (RuntimeException re) {
	    log.error("delete failed", re);
	    throw re;
	}
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#findById(java.lang.Integer)
     */
    public Notice findById(java.lang.Integer id) {
	log.debug("getting Notice instance with id: " + id);
	try {
	    Notice instance = (Notice) getSession().get("com.crm.model.Notice",
		    id);
	    return instance;
	} catch (RuntimeException re) {
	    log.error("get failed", re);
	    throw re;
	}
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#findByExample(com.crm.model.Notice)
     */
    @SuppressWarnings("unchecked")
    public List<Notice> findByExample(Notice instance) {
	log.debug("finding Notice instance by example");
	try {
	    List<Notice> results = getSession().createCriteria("com.crm.model.Notice")
		    .add(Example.create(instance)).list();
	    log.debug("find by example successful, result size: "
		    + results.size());
	    return results;
	} catch (RuntimeException re) {
	    log.error("find by example failed", re);
	    throw re;
	}
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#findByProperty(java.lang.String, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public List<Notice> findByProperty(String propertyName, Object value) {
	log.debug("finding Notice instance with property: " + propertyName
		+ ", value: " + value);
	try {
	    String queryString = "from Notice as model where model."
		    + propertyName + "= ?";
	    Query queryObject = getSession().createQuery(queryString);
	    queryObject.setParameter(0, value);
	    return queryObject.list();
	} catch (RuntimeException re) {
	    log.error("find by property name failed", re);
	    throw re;
	}
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#findByTitle(java.lang.Object)
     */
    public List<Notice> findByTitle(Object title) {
	return findByProperty(TITLE, title);
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#findByContent(java.lang.Object)
     */
    public List<Notice> findByContent(Object content) {
	return findByProperty(CONTENT, content);
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#findByClassify(java.lang.Object)
     */
    public List<Notice> findByClassify(Object classify) {
	return findByProperty(CLASSIFY, classify);
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#findByAuthor(java.lang.Object)
     */
    public List<Notice> findByAuthor(Object author) {
	return findByProperty(AUTHOR, author);
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<Notice> findAll() {
	log.debug("finding all Notice instances");
	try {
	    String queryString = "from Notice";
	    Query queryObject = getSession().createQuery(queryString);
	    return queryObject.list();
	} catch (RuntimeException re) {
	    log.error("find all failed", re);
	    throw re;
	}
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#merge(com.crm.model.Notice)
     */
    public Notice merge(Notice detachedInstance) {
	log.debug("merging Notice instance");
	
	Session session;
	try {
	    session = getSession();
	    session.beginTransaction();
	    Notice result = (Notice) session.merge(detachedInstance);
	    session.getTransaction().commit();
	    session.close();
	    
	    log.debug("merge successful");
	    return result;
	} catch (RuntimeException re) {
	    log.error("merge failed", re);
	    throw re;
	}
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#attachDirty(com.crm.model.Notice)
     */
    public void attachDirty(Notice instance) {
	log.debug("attaching dirty Notice instance");
	
	Session session;
	try {
	    session = getSession();
	    session.beginTransaction();
	    session.saveOrUpdate(instance);
	    session.getTransaction().commit();
	    session.close();
	    
	    log.debug("attach successful");
	} catch (RuntimeException re) {
	    log.error("attach failed", re);
	    throw re;
	}
    }

    /* (non-Javadoc)
     * @see com.crm.dao.impl.INoticeDAO#attachClean(com.crm.model.Notice)
     */
    public void attachClean(Notice instance) {
	log.debug("attaching clean Notice instance");
	
	Session session;
	try {
	    session = getSession();
	    session.beginTransaction();
	    session.lock(instance, LockMode.NONE);
	    session.getTransaction().commit();
	    session.close();
	    
	    log.debug("attach successful");
	} catch (RuntimeException re) {
	    log.error("attach failed", re);
	    throw re;
	}
    }
}