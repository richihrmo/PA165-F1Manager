package cz.muni.fi.mvc.controllers;

import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.TeamDTO;
import cz.muni.fi.dto.TeamEditDTO;
import cz.muni.fi.facade.CarFacade;
import cz.muni.fi.facade.TeamFacade;
import cz.muni.fi.mvc.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Matus Macko
 */
@Controller
@RequestMapping("/teams")
public class TeamController {

    private final static Logger log = LoggerFactory.getLogger(TeamController.class);

    @Inject
    private TeamFacade teamFacade;

    @Inject
    private CarFacade carFacade;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTeam(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        log.debug("[TEAM] create");
        model.addAttribute("teamCreate", new TeamEditDTO());
        modelCarsListAdd(model, null);
        return "/teams/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("teamCreate") TeamEditDTO formBean,
                         Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        if(formBean.getName().isEmpty()){
            model.addAttribute("alert_danger", "Name cannot be empty!");
            modelCarsListAdd(model, null);
            return "teams/create";
        }

        if (formBean.getCarOneId() == null || formBean.getCarTwoId() == null){
            model.addAttribute("alert_danger", "carOne or carTwo cannot be empty!");
            modelCarsListAdd(model, null);
            return "teams/create";
        }

        if (formBean.getCarOneId().equals(formBean.getCarTwoId())) {
            model.addAttribute("alert_danger", "carOne and carTwo cannot be the same!");
            modelCarsListAdd(model, null);
            return "teams/create";
        }

        TeamDTO teamDTO = teamFacade.createTeam(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Creation of " + teamDTO.getName() + " succeeded");
        return "redirect:" + uriBuilder.path("/teams").build().toUriString();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String all(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonUser(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        log.debug("[TEAM] all");
        model.addAttribute("teams", teamFacade.getAllTeams());
        return "teams/all";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonUser(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        log.debug("[TEAM] show ({})", id);
        model.addAttribute("team", teamFacade.getTeamById(id));
        return "teams/show";
    }

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public String drivers(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonUser(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        log.debug("[TEAM] show all drivers");
        model.addAttribute("drivers", teamFacade.getAllTeamCarDrivers());
        return "teams/drivers";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTeam(@PathVariable Long id, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        log.debug("[TEAM] edit ({})", id);
        TeamDTO team = teamFacade.getTeamById(id);
        TeamEditDTO editTeam = new TeamEditDTO(team.getId(), team.getName(), team.getCarOne().getId(), team.getCarTwo().getId());
        modelCarsListAdd(model, team);
        model.addAttribute("teamEdit", editTeam);
        return "teams/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("teamEdit") TeamEditDTO formBean,
                       Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        if (formBean.getName().isEmpty()){
            model.addAttribute("alert_danger", "Name cannot be empty!");
            modelCarsListAdd(model, teamFacade.getTeamById(formBean.getId()));
            return "teams/edit";
        }

        if (formBean.getCarOneId() == null || formBean.getCarTwoId() == null){
            model.addAttribute("alert_danger", "carOne or carTwo cannot be empty!");
            modelCarsListAdd(model, teamFacade.getTeamById(formBean.getId()));
            return "teams/create";
        }

        if (formBean.getCarOneId().equals(formBean.getCarTwoId())) {
            model.addAttribute("alert_danger", "carOne and carTwo cannot be the same!");
            modelCarsListAdd(model, teamFacade.getTeamById(formBean.getId()));
            return "teams/edit";
        }

        TeamDTO teamDTO = teamFacade.updateTeam(formBean);
        redirectAttributes.addFlashAttribute("alert_success", teamDTO.getName() + " successfully updated");
        return "redirect:" + uriBuilder.path("/teams").build().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id, HttpServletRequest request, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        String res = Tools.redirectNonAdmin(request, uriBuilder, redirectAttributes);
        if(res != null) return res;

        TeamDTO teamDTO = teamFacade.getTeamById(id);
        teamFacade.deleteTeam(teamDTO);
        log.debug("[TEAM] delete ({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Team '" + teamDTO.getName() + "' was deleted.");
        return "redirect:" + uriBuilder.path("/teams").build().toUriString();
    }

    private void modelCarsListAdd(Model model, TeamDTO team){
        List<CarDTO> cars = carFacade.listAllCars();
        cars.removeAll(teamFacade.getAllTeamCars());
        if(team != null){
            cars.add(team.getCarOne());
            cars.add(team.getCarTwo());
        }
        model.addAttribute("cars", cars);
    }

}