package com.gert.dao;

import com.gert.model.PersistentLogin;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by gert on 03.03.17.
 */
@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenImplementation extends AbstractDao<String, PersistentLogin> implements PersistentTokenRepository {

    static final Logger logger = LoggerFactory.getLogger(HibernateTokenImplementation.class);

    public void createNewToken(PersistentRememberMeToken token) {

        logger.info("Creating Token for user : {}", token.getUsername());
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        persist(persistentLogin);

    }

    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {

        logger.info("Updating Token for seriesId : {}", seriesId);
        PersistentLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        update(persistentLogin);
    }

    public PersistentRememberMeToken getTokenForSeries(String seriesId) {

        logger.info("Fetch Token if any for seriesId : {}", seriesId);
        try {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("series", seriesId));
            PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            logger.info("Token not found...");
            return null;
        }
    }

    public void removeUserTokens(String username) {

        logger.info("Removing Token if any for user : {}", username);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

        if (persistentLogin != null) {
            logger.info("rememberMe was selected");
            delete(persistentLogin);
        }

    }
}
