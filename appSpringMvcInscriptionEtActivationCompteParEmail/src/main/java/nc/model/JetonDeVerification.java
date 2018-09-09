package nc.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;


@Entity
public class JetonDeVerification implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final long DELAI_EXPIRATION = 1;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String token;
	
	@OneToOne(targetEntity=Utilisateur.class, fetch=FetchType.EAGER)
	@JoinColumn(nullable=false, name="id_utilisateur", foreignKey=@ForeignKey(name="fk_jeton_de_verification"))
	private Utilisateur utilisateur;
	private LocalDate delai;
	
	public JetonDeVerification() {
		super();
	}

	public JetonDeVerification(final String token) {
		super();
		this.token = token;
		this.delai=verifierDelaiExpiration(DELAI_EXPIRATION);
	}

	public JetonDeVerification(final String token, final Utilisateur utilisateur) {
		super();
		this.token = token;
		this.utilisateur = utilisateur;
		this.delai=verifierDelaiExpiration(DELAI_EXPIRATION);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public LocalDate getDelaiExpiration() {
		return delai;
	}

	public void setDateExpiration(LocalDate delai) {
		this.delai = delai;
	}
	
	private LocalDate verifierDelaiExpiration(long daysToAdd) {
		return LocalDate.now().plusDays(daysToAdd);
	}
	
	public void updateToken(final String token) {
		this.token = token;
		this.delai=verifierDelaiExpiration(DELAI_EXPIRATION);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result + ((delai == null) ? 0 : delai.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
        return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JetonDeVerification other = (JetonDeVerification) obj;
        if (delai == null) {
            if (other.delai != null) {
                return false;
            }
        } else if (!delai.equals(other.delai)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
            return false;
        }
        if (utilisateur == null) {
            if (other.utilisateur != null) {
                return false;
            }
        } else if (!utilisateur.equals(other.utilisateur)) {
            return false;
        }
        return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(token).append("]").append("[Expire").append(delai).append("]");
        return builder.toString();
	}
	
	
}
