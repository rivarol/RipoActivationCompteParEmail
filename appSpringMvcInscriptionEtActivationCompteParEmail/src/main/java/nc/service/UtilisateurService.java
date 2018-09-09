package nc.service;

import nc.model.JetonDeVerification;
import nc.model.Utilisateur;


public interface UtilisateurService {
	Utilisateur enregisterNouveauUtilisateur(Utilisateur utilisateur);
	void enregisterUtilisateur(Utilisateur utilisateur);
	Utilisateur getUtilisateur(String token);
	Utilisateur findUtilisateurByEmail(String email);
	void deleteUtilisateur(Utilisateur utilisateur);
	JetonDeVerification getJetonDeVerification(String token);
	void creerJetonDeVerificationPourUtilisateur(Utilisateur utilisateur, String token);
}