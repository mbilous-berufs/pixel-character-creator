package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
    List<CharacterEntity> findByOwnerKeycloakId(String keycloakId);
    Optional<CharacterEntity> findByIdAndOwnerKeycloakId(Long id, String keycloakId);
    long countByOwnerKeycloakId(String keycloakId);
}
