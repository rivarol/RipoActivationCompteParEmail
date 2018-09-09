package nc.dao;


import java.time.LocalTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import nc.model.JetonDeVerification;
import nc.model.Utilisateur;

@Transactional
public interface JetonDeVerificationRepository extends JpaRepository<JetonDeVerification, Long>{
}
