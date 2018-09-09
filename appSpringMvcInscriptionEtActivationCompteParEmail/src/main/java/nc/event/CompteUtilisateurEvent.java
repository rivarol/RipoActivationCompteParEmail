package nc.event;

import org.springframework.context.ApplicationEvent;

import nc.model.Utilisateur;

public class CompteUtilisateurEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	private String appUrl;

	public CompteUtilisateurEvent(Utilisateur utilisateur, String appUrl) {
		super(utilisateur);
		this.utilisateur = utilisateur;
		this.appUrl = appUrl;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public String getAppUrl() {
		return appUrl;
	}

}
