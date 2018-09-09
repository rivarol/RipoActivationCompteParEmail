package nc.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import nc.model.Utilisateur;

@Transactional
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
}
