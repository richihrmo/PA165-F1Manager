package cz.muni.fi.mvc.controllers;

import cz.muni.fi.dto.UserCreateDTO;
import cz.muni.fi.dto.UserDTO;
import cz.muni.fi.dto.UserLoginDTO;
import cz.muni.fi.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Robert Tamas
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final static Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    private int badLoginCounter = 0;

    @Autowired
    private UserFacade userFacade;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loginUser(Model model, HttpServletRequest request) {

        if(request.getSession().getAttribute("user") != null){
            return "home";
        }

        log.debug("[AUTH] Login");

        model.addAttribute("userLogin", new UserLoginDTO());
        return "/auth/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(Model model, HttpServletRequest request) {
        log.debug("[AUTH] Login");
        request.getSession().removeAttribute("user");
        return "home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(Model model, HttpServletRequest request) {
        log.debug("[AUTH] Register");
        model.addAttribute("userRegister", new UserCreateDTO());
        return "auth/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("userRegister") UserCreateDTO formBean, BindingResult bindingResult,
                           Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        log.debug("register(userRegister={})", formBean);


        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.debug("FieldError: {}", fe);
            }

            model.addAttribute("userRegister", formBean);
            return "/auth/register";
        }

        if(userFacade.findUserByEmail(formBean.getEmail()) != null){
            redirectAttributes.addFlashAttribute("alert_warning", "Email " + formBean.getEmail() + " already exists!");
            return "redirect:" + uriBuilder.path("/auth/register").build().toUriString();
        }

        UserDTO user = userFacade.addUser(formBean);
        request.getSession().setAttribute("user", user);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Register " + formBean.getEmail() + " succeeded");

        return "redirect:" + uriBuilder.path("/").build().toUriString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("userLogin") UserLoginDTO formBean, BindingResult bindingResult,
                        Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        log.debug("login(userLogin={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.debug("FieldError: {}", fe);
            }
            model.addAttribute("userLogin", new UserLoginDTO());
            return "/auth/login";
        }

        UserDTO matchingUser = userFacade.findUserByEmail(formBean.getEmail());
        if(matchingUser==null) {
            log.warn("no user with email {}", formBean.getEmail());
            redirectAttributes.addFlashAttribute("alert_warning", "No user with email: " + formBean.getEmail());
            return "redirect:" + uriBuilder.path("/auth").build().toUriString();
        }

        if (!userFacade.authenticate(formBean.getEmail(), formBean.getPassword())) {
            log.warn("wrong credentials: user={} password={}", formBean.getEmail(), formBean.getPassword());
            //easter egg after 10 bad password entry
            if (badLoginCounter == 10 ) {
                redirectAttributes.addFlashAttribute("alert_warning", "Yoda advice: You must correct password use!");
            } else {
                badLoginCounter++;
                redirectAttributes.addFlashAttribute("alert_warning", "Login " + formBean.getEmail() + " failed ");
            }

            return "redirect:" + uriBuilder.path("/auth").build().toUriString();
        }

        badLoginCounter = 0;
        request.getSession().setAttribute("user", matchingUser);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Login " + formBean.getEmail() + " succeeded ");
        return "redirect:" + uriBuilder.path("").build().toUriString();
    }
}
