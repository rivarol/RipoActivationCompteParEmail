package nc.service;

import org.springframework.beans.factory.annotation.Autowired;

import nc.dao.JetonDeVerificationRepository;
import nc.dao.UtilisateurRepository;
import nc.erreur.UtilisateurExusteDejaException;
import nc.model.JetonDeVerification;
import nc.model.Utilisateur;

public class UtilisateurServiceImpl implements UtilisateurService{
	@Autowired
	private UtilisateurRepository uR;
	
	@Autowired
	private JetonDeVerificationRepository jR;
	
	private boolean emailExiste(final String email) {
		return uR.findByEmail(email) !=null;
	}

	public Utilisateur enregisterNouveauUtilisateur(Utilisateur utilisateur) {
		if(emailExiste(utilisateur.getEmail())) {
			throw new UtilisateurExusteDejaException("Il existe déjà un utilisateur avec cette adresse email "+utilisateur.getEmail());
		}
		return uR.save(utilisateur);
	}

	public void enregisterUtilisateur(Utilisateur utilisateur) {
		uR.save(utilisateur);
		
	}

	public Utilisateur getUtilisateur(String token) {
		JetonDeVerification jDv=jR.findByToken(token);
		if(jDv!=null) {
			return jDv.getUtilisateur();
		}
		return null;
	}

	public JetonDeVerification getJetonDeVerification(String token) {
		return jR.findByToken(token);
	}

	public void creerJetonDeVerificationPourUtilisateur(Utilisateur utilisateur, String token) {
		JetonDeVerification jDv=new JetonDeVerification(token, utilisateur);
		jR.save(jDv);
	}

	public Utilisateur findUtilisateurByEmail(String email) {
		return uR.findByEmail(email);
	}

	public void deleteUtilisateur(Utilisateur utilisateur) {
		uR.delete(utilisateur);
		
	}

}
