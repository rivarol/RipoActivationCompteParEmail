package nc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import nc.model.Utilisateur;
import nc.service.UtilisateurService;

@Controller
public class ConnexionCompteUtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginForm(Model model) {
		Utilisateur utilisateur=new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		return "login";
	}
	
	@RequestMapping(value="/connexionCompteUtilisateur", method=RequestMethod.POST)
	public ModelAndView compteUtilisateur(@ModelAttribute("utilisateur") @Valid Utilisateur utilisateur, BindingResult result, HttpServletRequest request) {
		if(utilisateur==null) {
			return new ModelAndView("login", "utilisateur",utilisateur);
		}
		
		Utilisateur user = utilisateurService.findUtilisateurByEmail(utilisateur.getEmail());
		if(user==null) {
			return new ModelAndView("login", "utilisateur",utilisateur);
		}
		
		boolean activated=utilisateurService.findUtilisateurByEmail(utilisateur.getEmail()).isEnabled();
		if(activated && user.getMotDePasse().equals(utilisateur.getMotDePasse())) {
			return new ModelAndView("compteUtilisateur","user",user);
		}else {
			return new ModelAndView("login","utilisateur",utilisateur);
		}
	}

}
