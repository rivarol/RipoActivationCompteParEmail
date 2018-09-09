package nc.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import nc.event.CompteUtilisateurEvent;
import nc.model.Utilisateur;
import nc.service.UtilisateurService;

public class CompteUtilisateurListener implements ApplicationListener<CompteUtilisateurEvent>{
	@Autowired
	private UtilisateurService utilisateurService;
	
	private MailSender mailSender;
	
	public void onApplicationEvent(CompteUtilisateurEvent event) {
		this.confirmeInscription(event);
	}
	
	private void confirmeInscription(CompteUtilisateurEvent event) {
		Utilisateur utilisateur=event.getUtilisateur();
		final String token=UUID.randomUUID().toString();
		utilisateurService.creerJetonDeVerificationPourUtilisateur(utilisateur, token);
		
		SimpleMailMessage email=creationMessageMail(event, utilisateur, token);
		mailSender.send(email);
	}
	
	private SimpleMailMessage creationMessageMail(CompteUtilisateurEvent event, Utilisateur utilisateur, String token) {
		String adresseEmailDestinataire = utilisateur.getEmail();
		String sujet = "Confirmation d'inscription";
		
		String confirmationUrl = event.getAppUrl() + "/confirmationCreationCompte?token="+token;
		String message = "Vous vous êtes inscrit avec succès. Nous vous enverrons un message de confirmation à votre compte de messagerie.";
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(adresseEmailDestinataire);
		email.setSubject(sujet);
		email.setText(message+ " \r\n" + confirmationUrl);
		email.setFrom("email");
		
		return email;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
}
