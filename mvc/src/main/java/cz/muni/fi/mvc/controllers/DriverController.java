package cz.muni.fi.mvc.controllers;

import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.DriverCreateDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.enums.DrivingSkill;
import cz.muni.fi.facade.CarFacade;
import cz.muni.fi.facade.DriverFacade;
import cz.muni.fi.mvc.Tools;
import cz.muni.fi.mvc.filter.DrivingSkillFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lucie Kureckova, 445264
 */
@Controller
@RequestMapping("/drivers")
public class DriverController {
    
    private final static Logger log = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    private DriverFacade driverFacade;
    
    @Autowired
    private CarFacade carFacade;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
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
        return "drivers/list";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewDriver(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        log.debug("[DRIVER] create");
        DriverDTO driver = new DriverDTO();
        model.addAttribute("driver", driver);
        model.addAttribute("Skills", Arrays.asList(DrivingSkill.values()));
        return "drivers/edit";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editDriver(@PathVariable Long id, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        log.debug("[DRIVER] edit ({})", id);
        model.addAttribute("driver", driverFacade.getDriverByID(id));
        model.addAttribute("Skills", Arrays.asList(DrivingSkill.values()));
        return "drivers/edit";
    }
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailDriver(@PathVariable Long id, Model model) {
        log.debug("[DRIVER] details about ({})", id);
        DriverDTO d = driverFacade.getDriverByID(id);
        model.addAttribute("driver", d);
        model.addAttribute("car", carFacade.findCarByDriver(d));
        return "drivers/show";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute("driver") DriverDTO driverDTO,
                             Model model, HttpServletRequest request,
                             RedirectAttributes redirectAttributes,
                             UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        if(driverDTO.getName().isEmpty() || driverDTO.getSurname().isEmpty() || driverDTO.getNationality().isEmpty()){
            model.addAttribute("alert_danger", "Please fill all values!");
            model.addAttribute("driver", driverDTO);
            model.addAttribute("Skills", Arrays.asList(DrivingSkill.values()));
            return "drivers/edit";
        }
        if(driverDTO.getId() == null){
            DriverCreateDTO driver = new DriverCreateDTO(driverDTO.getName(), driverDTO.getSurname(), driverDTO.getNationality(), driverDTO.getSpecialSkill());
            driverFacade.createDriver(driver);
            redirectAttributes.addFlashAttribute("alert_success", "Driver was created.");
        } else{
            driverFacade.updateDriver(driverDTO);
            redirectAttributes.addFlashAttribute("alert_success", "Driver details were saved.");
        }
        return "redirect:" + "/drivers";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         Model model, HttpServletRequest request,
                         RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        log.debug("[DRIVER] delete ({})", id);
        DriverDTO driverDTO = driverFacade.getDriverByID(id);
        try {
            driverFacade.deleteDriver(driverDTO);
        } catch (Exception e) {
            String alert = "Driver " + driverDTO.getName() + " " + driverDTO.getSurname() + " cannot be deleted";
            CarDTO c = carFacade.findCarByDriver(driverDTO);
            if(c != null){
                alert += ", because he is main driver in a car";
            }
            redirectAttributes.addFlashAttribute("alert_danger", alert);
            return "redirect:" + "/drivers";
        }
        redirectAttributes.addFlashAttribute("alert_success", "Driver " + driverDTO.getName() + " " + driverDTO.getSurname() + " deleted.");
        return "redirect:" + "/drivers";
    }
}
