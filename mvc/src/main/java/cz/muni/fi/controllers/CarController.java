package cz.muni.fi.controllers;

import cz.muni.fi.dto.CarCreateDTO;
import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.enums.ComponentType;
import cz.muni.fi.facade.CarFacade;
import cz.muni.fi.facade.ComponentFacade;
import cz.muni.fi.facade.DriverFacade;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Richard Hrmo
 */
@Controller
@RequestMapping("/cars")
public class CarController {

    @Inject
    private CarFacade carFacade;

    @Inject
    private ComponentFacade componentFacade;

    @Inject
    private DriverFacade driverFacade;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCar(Model model){
        model.addAttribute("car", new CarCreateDTO());
        model.addAttribute("drivers", driverFacade.getAllDrivers()
                .stream().filter(p -> !p.isMainDriver()).collect(Collectors.toList()));
        model.addAttribute("engines", componentFacade.listAllAvailableComponentsWithType(ComponentType.ENGINE));
        model.addAttribute("brakes", componentFacade.listAllAvailableComponentsWithType(ComponentType.BRAKES));
        model.addAttribute("aerodynamics", componentFacade.listAllAvailableComponentsWithType(ComponentType.AERODYNAMICS));
        model.addAttribute("suspension", componentFacade.listAllAvailableComponentsWithType(ComponentType.SUSPENSION));
        model.addAttribute("transmission", componentFacade.listAllAvailableComponentsWithType(ComponentType.TRANSMISSION));
        return "cars/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newCarCreate(@Valid @ModelAttribute("car") CarCreateDTO form,
                               RedirectAttributes redirectAttributes,
                               Model model,
                               UriComponentsBuilder uriBuilder){
        if (form.getDriverId() == null){
            model.addAttribute("alert_danger", "Driver is null");
            model.addAttribute("components", componentFacade.listAllComponents());
            model.addAttribute("drivers", driverFacade.getAllDrivers());
            return "cars/new";
        }
        if (form.getEngineId() == null
                || form.getAerodynamicsId() == null
                || form.getSuspensionId() == null
                || form.getTransmissionId() == null
                || form.getBrakesId() == null){
            model.addAttribute("alert_danger", "One of components is null");
            model.addAttribute("components", componentFacade.listAllComponents());
            model.addAttribute("drivers", driverFacade.getAllDrivers());
            return "cars/new";
        }

        CarDTO carDTO = new CarDTO(
                driverFacade.getDriverByID(form.getDriverId()),
                componentFacade.findComponentByID(form.getEngineId()),
                componentFacade.findComponentByID(form.getAerodynamicsId()),
                componentFacade.findComponentByID(form.getSuspensionId()),
                componentFacade.findComponentByID(form.getTransmissionId()),
                componentFacade.findComponentByID(form.getBrakesId()));

        if (carDTO.getDriver() == null){
            model.addAttribute("alert_danger", "Driver is null");
            model.addAttribute("components", componentFacade.listAllComponents());
            model.addAttribute("drivers", driverFacade.getAllDrivers());
            return "cars/new";
        }
        if (carDTO.getTransmission() == null
                || carDTO.getAerodynamics() == null
                || carDTO.getBrakes() == null
                || carDTO.getEngine() == null
                || carDTO.getSuspension() == null){
            model.addAttribute("alert_danger", "One of components is null");
            model.addAttribute("components", componentFacade.listAllComponents());
            model.addAttribute("drivers", driverFacade.getAllDrivers());
            return "cars/new";
        }
        carFacade.createCar(carDTO);
        redirectAttributes.addFlashAttribute("alert_success", "Car No." + carDTO.getId() + " was created.");
        return "redirect:" + uriBuilder.path("/cars").build().toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updateCar(@PathVariable long id, Model model){
        CarDTO carDTO = carFacade.findCarById(id);
        CarCreateDTO carCreateDTO =
                new CarCreateDTO(carDTO.getId(),
                carDTO.getDriver().getId(),
                carDTO.getEngine().getId(),
                carDTO.getAerodynamics().getId(),
                carDTO.getSuspension().getId(),
                carDTO.getTransmission().getId(),
                carDTO.getBrakes().getId());
        model.addAttribute("car", carCreateDTO);
        model.addAttribute("engines", componentFacade.listAllAvailableComponentsWithType(ComponentType.ENGINE));
        model.addAttribute("brakes", componentFacade.listAllAvailableComponentsWithType(ComponentType.BRAKES));
        model.addAttribute("aerodynamics", componentFacade.listAllAvailableComponentsWithType(ComponentType.AERODYNAMICS));
        model.addAttribute("suspension", componentFacade.listAllAvailableComponentsWithType(ComponentType.SUSPENSION));
        model.addAttribute("transmission", componentFacade.listAllAvailableComponentsWithType(ComponentType.TRANSMISSION));
        model.addAttribute("drivers", driverFacade.getAllDrivers());
        return "cars/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateCarCreate(@Valid @ModelAttribute("car") CarCreateDTO form,
                               RedirectAttributes redirectAttributes,
                               UriComponentsBuilder uriBuilder, Model model){
        CarDTO carDTO = new CarDTO(
                driverFacade.getDriverByID(form.getDriverId()),
                componentFacade.findComponentByID(form.getEngineId()),
                componentFacade.findComponentByID(form.getAerodynamicsId()),
                componentFacade.findComponentByID(form.getSuspensionId()),
                componentFacade.findComponentByID(form.getTransmissionId()),
                componentFacade.findComponentByID(form.getBrakesId()));
        carDTO.setId(form.getId());
        if (carDTO.getDriver() == null){
            model.addAttribute("alert_danger", "Driver is null");
            model.addAttribute("components", componentFacade.listAllComponents());
            model.addAttribute("drivers", driverFacade.getAllDrivers());
            return "cars/edit";
        }
        if (carDTO.getTransmission() == null
                || carDTO.getAerodynamics() == null
                || carDTO.getBrakes() == null
                || carDTO.getEngine() == null
                || carDTO.getSuspension() == null){
            model.addAttribute("alert_danger", "One of components is null");
            model.addAttribute("components", componentFacade.listAllComponents());
            model.addAttribute("drivers", driverFacade.getAllDrivers());
            return "cars/edit";
        }
        carFacade.updateCar(carDTO);
        redirectAttributes.addFlashAttribute("alert_success", "Car No." + carDTO.getId() + " was updated.");
        return "redirect:" + uriBuilder.path("/cars").build().toUriString();

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteCar(
            @PathVariable long id, Model model, UriComponentsBuilder builder, RedirectAttributes redirectAttributes){
        CarDTO carDTO = carFacade.findCarById(id);
        try {
            carFacade.deleteCar(carDTO);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("alert_danger", "Car No." + carDTO.getId()
                    + " cannot be deleted, because it part of team");
            return "redirect:" + builder.path("/cars").build().toUriString();
        }
        redirectAttributes.addFlashAttribute("alert_success", "Car No." + carDTO.getId() + " was deleted.");
        return "redirect:" + builder.path("/cars").build().toUriString();
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("car", carFacade.findCarById(id));
        return "cars/show";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("cars", carFacade.listAllCars());
        return "cars/list";
    }
}
