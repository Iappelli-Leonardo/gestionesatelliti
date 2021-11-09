package it.prova.gestionesatelliti.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionesatelliti.model.Satellite;
import it.prova.gestionesatelliti.service.SatelliteService;

@Controller
@RequestMapping(value = "/satellite")
public class SatelliteController {
	
	@Autowired
	private SatelliteService satelliteService;
	
	@GetMapping
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		List<Satellite> results = satelliteService.listAllElements();
		mv.addObject("satellite_list_attribute", results);
		mv.setViewName("satellite/list");
		return mv;
	}

	@GetMapping("/search")
	public String search() {
		return "satellite/search";
	}

	@PostMapping("/list")
	public String listByExample(Satellite example, ModelMap model) {
		List<Satellite> results = satelliteService.findByExample(example);
		model.addAttribute("satellite_list_attribute", results);
		return "satellite/list";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("insert_satellite_attr", new Satellite());
		return "satellite/insert";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_satellite_attr") Satellite satellite, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors())
			return "satellite/insert";

		satelliteService.inserisciNuovo(satellite);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/satellite";
	}
	
	@GetMapping("/show/{idSatellite}")
	public String show(@PathVariable(required = true) Long idSatellite, Model model) {
		model.addAttribute("show_satellite_attr", satelliteService.caricaSingoloElemento(idSatellite));
		return "satellite/show";
	}
	
	@GetMapping("/remove/{idSatellite}")
	public String remove(@PathVariable(required = true) Long idSatellite, Model model) {
		model.addAttribute("remove_satellite_attr", satelliteService.caricaSingoloElemento(idSatellite));
		return "satellite/remove";
	}
	
	@GetMapping("/delete/{idSatellite}")
	public String rimozione(@PathVariable(required = true) Long idSatellite, Model model, RedirectAttributes redirectAttrs) {
		
	 Satellite satellite = satelliteService.caricaSingoloElemento(idSatellite);
	 
	 satelliteService.rimuovi(satellite);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/satellite";
	}
	
	@GetMapping("/modifica/{idSatellite}")
	public String modifica(@PathVariable(required = true) Long idSatellite, Model model) {
		model.addAttribute("update_satellite_attr", satelliteService.caricaSingoloElemento(idSatellite));
		return "satellite/update";
	}

	@PostMapping("/update")
	public String saveupdate(@Valid @ModelAttribute("update_satellite_attr") Satellite satellite,
			 Model model, RedirectAttributes redirectAttrs, BindingResult result) {

		if (result.hasErrors())
			return "satellite/update";

		satelliteService.aggiorna(satellite);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/satellite";

	}
	
	@GetMapping("/findallyears")
	public String ricercaDueAnni(Model model) {
		List<Satellite> satelliti = satelliteService.findAllAnnniDisattivati();
		model.addAttribute("satellite_list_attribute", satelliti);
		return "satellite/list";
	}
	
	@GetMapping("/findalldisattivati")
	public String ricercaDisattivati(Model model) {
		List<Satellite> satelliti = satelliteService.findDisattivatiNonRientrati();
		model.addAttribute("satellite_list_attribute", satelliti);
		return "satellite/list";
	}

	@GetMapping("/findall10anni")
	public String RicercaDieciAnniEFissi(Model model) {
		List<Satellite> satelliti = satelliteService.findPiuDiDieciAnniFissi();
		model.addAttribute("satellite_list_attribute", satelliti);
		return "satellite/list";
	}
}
