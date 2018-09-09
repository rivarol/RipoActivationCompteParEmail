package nc.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import nc.validation.Email;

@Entity
public class Utilisateur implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Le nom ne doit pas être vide")
	@Size(min=2, message="Le nom doit avoir au moins 2 caractères")
	private String nom;
	
	@NotNull(message="Le prénom ne doit pas être vide")
	@Size(min=2, message="Le nom doit avoir au moins 2 caractères")
	private String prenom;
	
	@Email
	private String email;
	
	@NotNull(message="Le mot de passe ne doit pas être vide")
	@Size(min=8, message="Le mot de passe doit avoir au moins 8 caractères")
	private String motDePasse;
	
	private boolean enabled;
	
	public Utilisateur() {
		super();
		this.enabled=false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = (prime * result) + ((email == null) ? 0 : email.hashCode());
        return result;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        Utilisateur user=(Utilisateur)obj;
        if (!email.equals(user.email)) {
            return false;
        }
        return true;
	} 
	
	@Override
	public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Utisateur [id=").append(id).
        		append(", prenom=").append(prenom).
        		append(", nom=").append(nom).
        		append(", email=").append(email).
        		append(", motDePasse=").append(motDePasse).
        		append(", enabled=").append(enabled).
        		append("]");
        		
        return builder.toString();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
