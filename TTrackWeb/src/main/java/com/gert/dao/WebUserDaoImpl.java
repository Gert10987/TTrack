package com.gert.dao;

import com.gert.model.WebUser;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gert on 02.03.17.
 */
public class WebUserDaoImpl implements WebUserDao {

    private JdbcTemplate jdbcTemplate;

    public WebUserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(WebUser contact) {

        if (contact.getId() > 0) {
            // update
            String sql = "UPDATE WEB_USER SET fName=?,lName=? ,email=?, "
                    + "WHERE id=?";
            jdbcTemplate.update(sql, contact.getlName(), contact.getEmail(),
                    contact.getfName(), contact.getId());
        } else {
            // insert
            String sql = "INSERT INTO WEB_USER (fName, lName, email)"
                    + " VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, contact.getlName(), contact.getEmail(),
                    contact.getfName());
        }

    }

    public void delete(int contactId) {

        String sql = "DELETE FROM WEB_USER WHERE id=?";
        jdbcTemplate.update(sql, contactId);
    }

    public WebUser get(int contactId) {
        String sql = "SELECT * FROM WEB_USER WHERE id=" + contactId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<WebUser>() {

            public WebUser extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    WebUser contact = new WebUser();
                    contact.setId(rs.getInt("id"));
                    contact.setfName(rs.getString("fName"));
                    contact.setlName(rs.getString("lName"));
                    contact.setEmail(rs.getString("email"));
                    return contact;
                }

                return null;
            }

        });
    }

    public List<WebUser> list() {

        String sql = "SELECT * FROM WEB_USER";
        List<WebUser> listContact = jdbcTemplate.query(sql, new RowMapper<WebUser>() {

            public WebUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                WebUser aContact = new WebUser();

                aContact.setId(rs.getInt("id"));
                aContact.setfName(rs.getString("fName"));
                aContact.setlName(rs.getString("lName"));
                aContact.setEmail(rs.getString("email"));

                return aContact;
            }

        });

        return listContact;
    }
}
