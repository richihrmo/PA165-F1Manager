package cz.muni.fi.mvc.controllers;

import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.enums.ComponentType;
import cz.muni.fi.facade.ComponentFacade;
import cz.muni.fi.filter.ComponentFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author Robert Tamas
 */
@Controller
@RequestMapping("/components")
public class ComponentController {

    private final static Logger log = LoggerFactory.getLogger(ComponentController.class);

    @Autowired
    ComponentFacade componentFacade;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createComponent(Model model,  HttpServletRequest request, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        //String res = Tools.redirectNonTestDriver(request, uriBuilder, redirectAttributes);
        //if(res != null) return res;
        log.debug("[COMPONENT] Create");
        model.addAttribute("componentTypeSelect", Arrays.asList(ComponentType.values()));
        model.addAttribute("componentCreate", new ComponentDTO());
        return "/components/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("componentCreate")ComponentDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {

        //String res = Tools.redirectNonTestDriver(request, uriBuilder, redirectAttributes);
        //if (res != null) return res;

        if (componentFacade.findComponentByName(formBean.getName()) != null) {
            redirectAttributes.addFlashAttribute("alert_warning", "Component with name '" + formBean.getName() + "' already exists");
            return "redirect:" + uriBuilder.path("/components/create").build().encode().toUriString();
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.debug("FieldError: {}", fe);
            }

            model.addAttribute("componentCreate", formBean);
            return "/components/create";
        }

        ComponentDTO componentDTO = componentFacade.createComponent(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Creation of " + componentDTO.getName() + " succeeded");

        return "redirect:" + uriBuilder.path("/components").build().toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editComponent(@PathVariable long id, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        //String res = Tools.redirectNonTestDriver(request, uriBuilder, redirectAttributes);
        //if(res != null) return res;
        log.debug("[COMPONENT] Edit {}", id);
        ComponentDTO componentDTO = componentFacade.findComponentByID(id);
        model.addAttribute("componentTypeSelect", Arrays.asList(ComponentType.values()));
        model.addAttribute("componentEdit", componentDTO);
        return "/components/edit";
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable long id, @Valid @ModelAttribute("componentEdit")ComponentDTO formBean, BindingResult bindingResult,
                         Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        //String res = Tools.redirectNonTestDriver(request, uriBuilder, redirectAttributes);
        //if(res != null) return res;

        formBean.setId(id);
        ComponentDTO foundComponent = componentFacade.findComponentByName(formBean.getName());

        if (foundComponent != null && !foundComponent.getId().equals(formBean.getId())) {
            redirectAttributes.addFlashAttribute("alert_warning", "Component with name '" + formBean.getName() + "' already exists");
            return "redirect:" + uriBuilder.path("/components/edit/{id}").buildAndExpand(id).encode().toUriString();
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }

            model.addAttribute("componentEdit", formBean);
            return "/components/edit";
        }

        log.debug("[COMPONENT] Update: {}", formBean);
        model.addAttribute("componentTypeSelect", Arrays.asList(ComponentType.values()));
        
        if(formBean.getType() == null){
            ComponentDTO c = componentFacade.findComponentByID(id);
            formBean.setType(c.getType());
            formBean.setAvailability(false);
        }
        
        ComponentDTO result = componentFacade.updateComponent(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Component '" + result.getName() + "' was updated");
        return "redirect:" + uriBuilder.path("/components/read/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, HttpServletRequest request, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {

        //String res = Tools.redirectNonTestDriver(request, uriBuilder, redirectAttributes);
        //if(res != null) return res;

        log.debug("delete component({})", id);
        ComponentDTO componentDTO = componentFacade.findComponentByID(id);
        try {
            componentFacade.deleteComponent(componentDTO);
            redirectAttributes.addFlashAttribute("alert_success", "Component '" + componentDTO.getName() + "' was deleted.");
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("alert_warning", "Component with name '" + componentDTO.getName() + "' cannot be deleted because it's associated with some car.");
        }
        return "redirect:" + uriBuilder.path("/components").build().toUriString();
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        log.debug(" Read ({})", id);

        model.addAttribute("component", componentFacade.findComponentByID(id));
        return "components/read";
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public String list(@Valid @ModelAttribute("filter")ComponentFilter filterBean, Model model, HttpServletRequest request, UriComponentsBuilder uriBuilder) {

        ComponentFilter.ComponentFilterType filterNone = ComponentFilter.ComponentFilterType.NONE;
        List<ComponentDTO> components;

        log.debug("Listing components with filter: Available: " + filterBean.getAvailable() + " and type: " + filterBean.getType());

        if (filterBean.getAvailable()) {
            if (filterBean.getType() == filterNone || filterBean.getType() == null) {
                components = componentFacade.listAllAvailableComponents();
            } else {
                components = componentFacade.listAllAvailableComponentsWithType(ComponentType.parse(filterBean.getType().getUrlAnnotation()));
            }
        } else {
            if (filterBean.getType() == filterNone || filterBean.getType() == null) {
                components = componentFacade.listAllComponents();
            } else {
                components = componentFacade.listAllComponentsWithType(ComponentType.parse(filterBean.getType().getUrlAnnotation()));
            }
        }

        model.addAttribute("components", components);
        model.addAttribute("filter", filterBean);
        model.addAttribute("componentTypeSelect", Arrays.asList(ComponentFilter.ComponentFilterType .values()));
        return "components/list";
    }
}
