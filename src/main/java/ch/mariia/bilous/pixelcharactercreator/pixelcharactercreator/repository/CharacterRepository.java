package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByOwnerKeycloakId(String keycloakId);
    Optional<Character> findByIdAndOwnerKeycloakId(Long id, String keycloakId);
    long countByOwnerKeycloakId(String keycloakId);
}
