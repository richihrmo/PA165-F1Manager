package cz.muni.fi.mvc;

import cz.muni.fi.dto.DriverDTO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Robert Tamas
 */
public class Tools {
    public static String redirectNonTestDriver(HttpServletRequest request, UriComponentsBuilder builder, RedirectAttributes redirectAttributes) {
        DriverDTO user = (DriverDTO) request.getSession().getAttribute("driver");
        if(user == null || user.isMainDriver()){
            redirectAttributes.addFlashAttribute("alert_warning", "You are not authorized!");
            return "redirect:" + builder.path("/").build().toUriString();
        }
        return null;
    }
}
