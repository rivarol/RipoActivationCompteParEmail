package nc.controller;

import java.nio.charset.MalformedInputException;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import nc.event.CompteUtilisateurEvent;
import nc.model.JetonDeVerification;
import nc.model.Utilisateur;
import nc.service.UtilisateurService;

@Controller
public class CompteUtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@RequestMapping(value="/formNouveauCompte", method=RequestMethod.GET)
	public ModelAndView showFormCreerCompteUtilisateur() {
		return new ModelAndView("nouveauCompte","utilisateur", new Utilisateur());
	}
	
	@RequestMapping(value="/enregistrerNouveauCompte", method=RequestMethod.POST)
	public ModelAndView enregistrerCompteNouveauUtilisateur(@ModelAttribute("utilisateur") @Valid Utilisateur utilisateur, BindingResult result, HttpServletRequest request) {
		if(result.hasErrors()) {
			return new ModelAndView("nouveauCompte","utilisateur", utilisateur);
		}
		Utilisateur user=utilisateurService.enregisterNouveauUtilisateur(utilisateur);
			
		if(user==null) {
			return new ModelAndView("nouveauCompte","utilisateur", utilisateur);
		}
			
		try {
			String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			applicationEventPublisher.publishEvent(new CompteUtilisateurEvent(user, appUrl));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("email","utilisateur", utilisateur);
		}
			
		return new ModelAndView("success","utilisateur", utilisateur);
	
	}
	
	@RequestMapping(value="/confirmationCreationCompte", method=RequestMethod.GET)
	 public String confirmationCreationCompte(Model model, @RequestParam(value="token") String token) {
		JetonDeVerification jDv=utilisateurService.getJetonDeVerification(token);
		if(jDv==null) {
			 model.addAttribute("message", new String("Jeton invalide"));
			 return "jetonInvalide";
		}
		
		Utilisateur utilisateur=jDv.getUtilisateur();   
		LocalDate ld=LocalDate.now();
		if(jDv.getDelaiExpiration().compareTo(ld)<=0) {
			 model.addAttribute("message", "Votre jeton d'inscription a expiré. Veuillez vous enregistrer à nouveau.");
	         model.addAttribute("expire", true);
	         model.addAttribute("token", token);
	         return "jetonInvalide";
		}
		
		utilisateur.setEnabled(true);
		utilisateurService.enregisterUtilisateur(utilisateur);
		
		model.addAttribute("utilisateur", new Utilisateur());
		return "login";
	}

}
