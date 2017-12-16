package controllers;

import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.facade.CarFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Richard Hrmo
 */

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarFacade carFacade;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCar(Model model){
        model.addAttribute("car", new CarDTO());
        return "car/new";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteCar(
            @PathVariable long id, Model model, UriComponentsBuilder builder, RedirectAttributes redirectAttributes){
        CarDTO carDTO = carFacade.findCarById(id);
        carFacade.deleteCar(carDTO);
        redirectAttributes.addFlashAttribute("success", "Car " + carDTO.toString() + " was deleted.");
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
