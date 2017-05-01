package com.gert.converter;

import com.gert.model.employer.Employer;
import com.gert.service.employer.EmployerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by gert on 03.03.17.
 */
@Component
public class EmployerConverter implements Converter<Employer, Employer> {

    static final Logger logger = LoggerFactory.getLogger(EmployerConverter.class);

    @Autowired
    EmployerService employerService;
    /**
     * Gets employer by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Employer convert(Employer employer) {

        return employer;
    }
}
