package com.gert.controllers;

import com.gert.model.employer.Employer;
import com.gert.model.user.User;
import com.gert.model.user.UserProfile;
import com.gert.service.employer.EmployerService;
import com.gert.service.user.UserProfileService;
import com.gert.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by gert on 03.03.17.
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    EmployerService employerService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;


    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        String userName = getPrincipal();

        List<Employer> employers =
                employerService.findAllEmployersByBossId(userService.findBySSO(userName));

        model.addAttribute("employers", employers);
        model.addAttribute("loggedInUser", userName);

        return "employer/employerList";
    }

    /**
     * This method will provide the medium to add a new employer.
     */
    @RequestMapping(value = {"/newemployer"}, method = RequestMethod.GET)
    public String newEmployer(ModelMap model) {

        Employer employer = new Employer();

        model.addAttribute("employer", employer);
        model.addAttribute("edit", false);
        model.addAttribute("loggedInUser", getPrincipal());

        return "common/registrationEmployer";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {

        User user = new User();

        model.addAttribute("user", user);

        return "common/registrationUser";
    }

    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {

            return "registrationUser";

        }else{

            if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
                FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
                result.addError(ssoError);
                return "common/registrationUser";
            }

            userService.saveUser(user);

            model.addAttribute("success", "user " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
            model.addAttribute("loggedInUser", getPrincipal());
            return "common/registrationSuccess";
        }
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newemployer"}, method = RequestMethod.POST)
    public String saveEmployer(@Valid Employer employer, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {

            return "common/registrationEmployer";

        }else{

            if (!employerService.isUserSSOUnique(employer.getId(), employer.getSsoId())) {
                FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{employer.getSsoId()}, Locale.getDefault()));
                result.addError(ssoError);
                return "common/registrationEmployer";
            }

            employer.setUser(userService.findBySSO(getPrincipal()));
            employerService.saveUser(employer);

            model.addAttribute("success", "Employer " + employer.getFirstName() + " " + employer.getLastName() + " registered successfully");
            model.addAttribute("loggedInUser", getPrincipal());
            return "common/registrationSuccess";
        }
    }



    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-employer-{ssoId}"}, method = RequestMethod.POST)
    public String updateEmployer(@Valid Employer employer, BindingResult result,
                                 ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return "common/registration";
        }

        employerService.createNewEmployer(employer);

        model.addAttribute("success", "Employer " + employer.getFirstName() + " " + employer.getLastName() + " updated successfully");
        model.addAttribute("loggedInUser", getPrincipal());
        return "common/registrationSuccess";
    }


    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = {"/delete-employer-{ssoId}"}, method = RequestMethod.GET)
    public String deleteEmployer(@PathVariable String ssoId) {

        employerService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }


    /**
     * This method will provide userProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedInUser", getPrincipal());
        return "common/accessDenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {

        if (isCurrentAuthenticationAnonymous()) {
            return "common/login";
        } else {
            return "redirect:/list";
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {

        String userName = StringUtils.EMPTY;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private int getUserId(String userName) {

        User user = userService.findBySSO(getPrincipal());

        return user.getId();

    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
