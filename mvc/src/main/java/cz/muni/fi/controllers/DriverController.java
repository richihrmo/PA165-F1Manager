package cz.muni.fi.controllers;

import cz.muni.fi.dto.DriverCreateDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.enums.DrivingSkill;
import cz.muni.fi.facade.DriverFacade;
import cz.muni.fi.filter.DrivingSkillFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllDrivers(@Valid @ModelAttribute("filter")DrivingSkillFilter filterType, Model model){
        
        if(filterType.getSkill() == DrivingSkillFilter.MainDrivingSkillFilter.NONE || filterType.getSkill() == null){
            log.debug("[DRIVER] show all");
            model.addAttribute("drivers", driverFacade.getAllDrivers());
        }else{
            log.debug("[DRIVER] all filtered");
            List<DriverDTO> filtered = new ArrayList<>();
            for(DriverDTO d : driverFacade.getAllDrivers()){
                if(d.getSpecialSkill() == DrivingSkill.parse(filterType.getSkill().getUrlAnnotation())){
                    filtered.add(d);
                }
            }
            model.addAttribute("drivers", filtered);
        }
        model.addAttribute("drivingType", Arrays.asList(DrivingSkillFilter.MainDrivingSkillFilter.values()));
        model.addAttribute("filter", filterType);
        return "driver/list";
    }
    
    @RequestMapping(value = "/filter/{filtered}", method = RequestMethod.GET)
    public String filterDrivers(@Valid @ModelAttribute("selected")String skill, Model model){
        
        model.addAttribute("Skills", Arrays.asList(DrivingSkill.values()));
        model.addAttribute("selected", skill);
        return "driver/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewDriver(Model model) {
        log.debug("[DRIVER] create");
        DriverDTO driver = new DriverDTO();
        model.addAttribute("driver", driver);
        model.addAttribute("Skills", Arrays.asList(DrivingSkill.values()));
        return "driver/edit";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editDriver(@PathVariable Long id, Model model) {
        log.debug("[DRIVER] edit ({})", id);
        model.addAttribute("driver", driverFacade.getDriverByID(id));
        model.addAttribute("Skills", Arrays.asList(DrivingSkill.values()));
        return "driver/edit";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute("driver") DriverDTO driverDTO,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) {
        if(driverDTO.getName().isEmpty() || driverDTO.getSurname().isEmpty() || driverDTO.getNationality().isEmpty()){
            model.addAttribute("alert_danger", "Please fill all values!");
            return "driver/edit";
        }
        if(driverDTO.getId() == null){
            DriverCreateDTO driver = new DriverCreateDTO(driverDTO.getName(), driverDTO.getSurname(), driverDTO.getNationality(), driverDTO.getSpecialSkill());
            driverFacade.createDriver(driver);
            redirectAttributes.addFlashAttribute("alert_success", "Driver was created.");
        } else{
            driverFacade.updateDriver(driverDTO);
            redirectAttributes.addFlashAttribute("alert_success", "Driver details were saved.");
        }
        return "redirect:" + "/driver/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {
        log.debug("[DRIVER] delete ({})", id);
        DriverDTO driverDTO = driverFacade.getDriverByID(id);
        try {
            driverFacade.deleteDriver(driverDTO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Driver " + driverDTO.getName() + " " + driverDTO.getSurname() + " cannot be deleted.");
            return "redirect:" + "/driver/";
        }
        redirectAttributes.addFlashAttribute("alert_success", "Driver " + driverDTO.getName() + " " + driverDTO.getSurname() + " deleted.");
        return "redirect:" + "/driver/";
    }
}
