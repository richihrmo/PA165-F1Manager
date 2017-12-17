package cz.muni.fi.mvc.controllers;

import cz.muni.fi.facade.UserFacade;
import cz.muni.fi.mvc.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Robert Tamas
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    private final static Logger log = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        log.debug("[USERS] List all");
        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;
        model.addAttribute("users", userFacade.findAllUsers());
        return "users/list";
    }
}
