package com.gert.service.employer;

import com.gert.dao.employer.EmployerDao;
import com.gert.model.employer.Employer;
import com.gert.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gert on 03.03.17.
 */
@Service("employerService")
@Transactional
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employer findById(int id) {
        return dao.findById(id);
    }

    public Employer findBySSO(String sso) {

        Employer employer = dao.findBySSO(sso);
        return employer;
    }

    public void saveUser(Employer employer) {

        employer.setPassword(passwordEncoder.encode(employer.getPassword()));
        dao.save(employer);
    }

    public void createNewEmployer(Employer employer) {

        Employer entity = dao.findById(employer.getId());

        if(entity!=null){

            entity.setSsoId(employer.getSsoId());

            if(!employer.getPassword().equals(entity.getPassword())){
                entity.setPassword(passwordEncoder.encode(employer.getPassword()));
            }
            entity.setFirstName(employer.getFirstName());
            entity.setLastName(employer.getLastName());
            entity.setEmail(employer.getEmail());
            entity.setPhone(employer.getPhone());
        }
    }

    public void deleteUserBySSO(String sso) {
        dao.deleteBySSO(sso);
    }

    public List<Employer> findAllEmployersByBossId(User boss) {

        return dao.findAllEmployersByBossId(boss);
    }

    public boolean isUserSSOUnique(Integer id, String sso) {
        Employer employer = findBySSO(sso);
        return ( employer == null || ((id != null) && (employer.getId() == id)));
    }

}
