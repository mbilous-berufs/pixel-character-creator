package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CustomizationItem;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CustomizationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomizationItemRepository extends JpaRepository<CustomizationItem, Long> {
    List<CustomizationItem> findByActiveTrue();
    List<CustomizationItem> findByTypeAndActiveTrue(CustomizationType type);
}
