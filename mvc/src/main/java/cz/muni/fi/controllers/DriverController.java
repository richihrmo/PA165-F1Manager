package cz.muni.fi.controllers;

import cz.muni.fi.dto.DriverCreateDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.facade.DriverFacade;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Lucie Kureckova, 445264
 */
@Controller
@RequestMapping("/driver")
public class DriverController {
    
    final static Logger log = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    private DriverFacade driverFacade;
    
    /**
     * Shows a list of drivers with the ability to add, delete or edit.
     * 
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllDrivers(Model model){
        log.info("Showing all drivers.");
        model.addAttribute("drivers", driverFacade.getAllDrivers());
        return "driver/list";
    }
    
    /**
     * Prepares an empty form for creating.
     * 
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewDriver(Model model) {
        log.info("Create new driver.");
        DriverDTO driver = new DriverDTO();
        model.addAttribute("driver", driver);
        return "driver/edit";
    }
    
    /**
     * Prepares form for editing.
     * 
     * @param id of driver to edit
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editDriver(@PathVariable Long id, Model model) {
        log.info("Edit driver.");
        model.addAttribute("driver", driverFacade.getDriverByID(id));
        return "driver/edit";
    }
    
    /**
     * Create/update driver to database.
     * 
     * @param driverDTO to be created/edited
     * @param model data to display
     * @param redirectAttributes to show result of method
     * @param uriBuilder uri
     * @return JSP page
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute("driver") DriverDTO driverDTO,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) {

        log.info("Saving DriverDTO: {}", driverDTO);
        if(driverDTO.getName().equals("") || driverDTO.getSurname().equals("") || driverDTO.getNationality().equals("")){
            StringBuilder builder = new StringBuilder("Please fill all values!");

            model.addAttribute("alert_danger", builder.toString());
            return "driver/edit";
        }
        if(driverDTO.getId() == null){
            DriverCreateDTO driver = new DriverCreateDTO();
            driver.setName(driverDTO.getName());
            driver.setSurname(driverDTO.getSurname());
            driver.setNationality(driverDTO.getNationality());
            driver.setSpecialSkill(driverDTO.getSpecialSkill());
            driverFacade.createDriver(driver);
            redirectAttributes.addFlashAttribute("alert_success", "Driver was created.");
        } else{
            driverFacade.updateDriver(driverDTO);
            redirectAttributes.addFlashAttribute("alert_success", "Driver details were saved.");
        }
        return "redirect:" + uriBuilder.path("/driver/").build().toUriString();
    }

    /**
     * Delete driver.
     * 
     * @param id to delete
     * @param model data to display
     * @param redirectAttributes to show result of method
     * @param uriBuilder uri
     * @return JSP page
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {
        log.info("Deleting driver with id: {}", id);
        DriverDTO driverDTO = driverFacade.getDriverByID(id);
        try {
            driverFacade.deleteDriver(driverDTO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Driver " + driverDTO.getName() + " " + driverDTO.getSurname() + " cannot be deleted.");
            return "redirect:" + uriBuilder.path("/driver/").build().toUriString();
        }
        redirectAttributes.addFlashAttribute("alert_success", "Driver " + driverDTO.getName() + " " + driverDTO.getSurname() + " deleted.");
        return "redirect:" + uriBuilder.path("/driver/").build().toUriString();
    }
}
