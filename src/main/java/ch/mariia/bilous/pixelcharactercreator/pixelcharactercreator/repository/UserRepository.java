package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByKeycloakId(String keycloakId);
}
