package cz.muni.fi.controllers;

import cz.muni.fi.dto.CarCreateDTO;
import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.enums.ComponentType;
import cz.muni.fi.facade.CarFacade;
import cz.muni.fi.facade.ComponentFacade;
import cz.muni.fi.facade.DriverFacade;
import java.util.ArrayList;
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
        model.addAttribute("drivers", getAvailableDrivers(null));
        modelAddComponents(model, null);
        return "cars/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newCarCreate(@Valid @ModelAttribute("car") CarCreateDTO form,
                               RedirectAttributes redirectAttributes,
                               Model model,
                               UriComponentsBuilder uriBuilder){
        if (form.getDriverId() == null){
            model.addAttribute("alert_danger", "Driver is null");
            modelAddComponents(model, null);
            model.addAttribute("drivers", getAvailableDrivers(null));
            return "cars/new";
        }
        if (form.getEngineId() == null
                || form.getAerodynamicsId() == null
                || form.getSuspensionId() == null
                || form.getTransmissionId() == null
                || form.getBrakesId() == null){
            model.addAttribute("alert_danger", "One of components is null");
            modelAddComponents(model, null);
            model.addAttribute("drivers", getAvailableDrivers(null));
            return "cars/new";
        }

        CarDTO carDTO = new CarDTO(
                driverFacade.getDriverByID(form.getDriverId()),
                componentFacade.findComponentByID(form.getEngineId()),
                componentFacade.findComponentByID(form.getAerodynamicsId()),
                componentFacade.findComponentByID(form.getSuspensionId()),
                componentFacade.findComponentByID(form.getTransmissionId()),
                componentFacade.findComponentByID(form.getBrakesId()));

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
        modelAddComponents(model, carCreateDTO);
        model.addAttribute("drivers", getAvailableDrivers(carDTO.getDriver()));
        return "cars/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateCarCreate(@Valid @ModelAttribute("car") CarCreateDTO form,
                               RedirectAttributes redirectAttributes,
                               UriComponentsBuilder uriBuilder, Model model){
        if (form.getDriverId() == null){
            model.addAttribute("alert_danger", "Driver is null");
            modelAddComponents(model, form);
            model.addAttribute("drivers", getAvailableDrivers(null));
            return "cars/edit";
        }
        if (form.getEngineId() == null
                || form.getAerodynamicsId() == null
                || form.getSuspensionId() == null
                || form.getTransmissionId() == null
                || form.getBrakesId() == null){
            model.addAttribute("alert_danger", "One of components is null");
            modelAddComponents(model, form);
            model.addAttribute("drivers", getAvailableDrivers(driverFacade.getDriverByID(form.getDriverId())));
            return "cars/edit";
        }

        CarDTO carDTO = new CarDTO(
                driverFacade.getDriverByID(form.getDriverId()),
                componentFacade.findComponentByID(form.getEngineId()),
                componentFacade.findComponentByID(form.getAerodynamicsId()),
                componentFacade.findComponentByID(form.getSuspensionId()),
                componentFacade.findComponentByID(form.getTransmissionId()),
                componentFacade.findComponentByID(form.getBrakesId()));
        carDTO.setId(form.getId());

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

    private void modelAddComponents(Model model, CarCreateDTO carCreateDTO){
        List<ComponentDTO> engines = componentFacade.listAllAvailableComponentsWithType(ComponentType.ENGINE);
        List<ComponentDTO> brakes = componentFacade.listAllAvailableComponentsWithType(ComponentType.BRAKES);
        List<ComponentDTO> aerodynamics = componentFacade.listAllAvailableComponentsWithType(ComponentType.AERODYNAMICS);
        List<ComponentDTO> suspension = componentFacade.listAllAvailableComponentsWithType(ComponentType.SUSPENSION);
        List<ComponentDTO> transmission = componentFacade.listAllAvailableComponentsWithType(ComponentType.TRANSMISSION);
        
        if(carCreateDTO != null){
            engines.add(componentFacade.findComponentByID(carCreateDTO.getEngineId()));
            brakes.add(componentFacade.findComponentByID(carCreateDTO.getBrakesId()));
            aerodynamics.add(componentFacade.findComponentByID(carCreateDTO.getAerodynamicsId()));
            suspension.add(componentFacade.findComponentByID(carCreateDTO.getSuspensionId()));
            transmission.add(componentFacade.findComponentByID(carCreateDTO.getTransmissionId()));
        }
        model.addAttribute("engines", engines);
        model.addAttribute("brakes", brakes);
        model.addAttribute("aerodynamics", aerodynamics);
        model.addAttribute("suspension", suspension);
        model.addAttribute("transmission", transmission);
    }
    
    private List<DriverDTO> getAvailableDrivers(DriverDTO driver){
        List<DriverDTO> drivers = new ArrayList<>();
        for(DriverDTO d : driverFacade.getAllDrivers()){
            if(carFacade.findCarByDriver(d) == null){
                drivers.add(d);
            }
        }
        if(driver != null){
            drivers.add(driver);
        }
        return drivers;
    }
}
