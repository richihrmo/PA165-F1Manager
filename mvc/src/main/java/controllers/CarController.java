package controllers;

import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.facade.CarFacade;
import cz.muni.fi.facade.ComponentFacade;
import cz.muni.fi.facade.DriverFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author Richard Hrmo
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Inject
    private CarFacade carFacade;

    @Inject
    private ComponentFacade componentFacade;

    @Inject
    private DriverFacade driverFacade;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCar(Model model){
        model.addAttribute("car", new CarDTO());
        model.addAttribute("components", componentFacade.listAllComponents());
        model.addAttribute("drivers", driverFacade.getAllDrivers());
        return "car/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newCarCreate(@Valid @ModelAttribute("car") CarDTO form,
                               RedirectAttributes redirectAttributes,
                               Model model,
                               UriComponentsBuilder uriBuilder){
        if (form.getDriver() == null){
            model.addAttribute("alert_danger", "Driver is null");
            return "car/new";
        }
        if (form.getTransmission() == null
                || form.getAerodynamics() == null
                || form.getBrakes() == null
                || form.getEngine() == null
                || form.getSuspension() == null){
            model.addAttribute("alert_danger", "One of components is null");
            return "car/new";
        }
        carFacade.createCar(form);
        redirectAttributes.addFlashAttribute("alert_success", "Car " + form.toString() + " was created.");
        return "redirect:" + uriBuilder.path("/car").build().toUriString();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String updateCar(@PathVariable long id, Model model){
        CarDTO carDTO = carFacade.findCarById(id);
        model.addAttribute("car", carDTO);
        return "car/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateCarCreate(@Valid @ModelAttribute("car") CarDTO form,
                               RedirectAttributes redirectAttributes,
                               Model model,
                               UriComponentsBuilder uriBuilder){
        if (form.getDriver() == null){
            model.addAttribute("alert_danger", "Driver is null");
            return "car/edit";
        }
        if (form.getTransmission() == null
                || form.getAerodynamics() == null
                || form.getBrakes() == null
                || form.getEngine() == null
                || form.getSuspension() == null){
            model.addAttribute("alert_danger", "One of components is null");
            return "car/edit";
        }
        carFacade.createCar(form);
        redirectAttributes.addFlashAttribute("alert_success", "Car " + form.toString() + " was updated.");
        return "redirect:" + uriBuilder.path("/car").build().toUriString();

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteCar(
            @PathVariable long id, Model model, UriComponentsBuilder builder, RedirectAttributes redirectAttributes){
        CarDTO carDTO = carFacade.findCarById(id);
        carFacade.deleteCar(carDTO);
        redirectAttributes.addFlashAttribute("alert_success", "Car " + carDTO.toString() + " was deleted.");
        return "redirect:" + builder.path("car/list").build().toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("car", carFacade.findCarById(id));
        return "car/show";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("cars", carFacade.listAllCars());
        return "car/list";
    }
}
