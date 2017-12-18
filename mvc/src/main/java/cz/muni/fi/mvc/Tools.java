package cz.muni.fi.mvc;

import cz.muni.fi.dto.UserDTO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Robert Tamas
 */
public class Tools {

    public static String redirectNonAdmin(HttpServletRequest request, UriComponentsBuilder builder, RedirectAttributes redirectAttributes) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if(user == null || !user.getAdmin()){
            redirectAttributes.addFlashAttribute("alert_warning", "You are not authorized!");
            return "redirect:" + builder.path("/").build().toUriString();
        }
        return null;
    }
}
