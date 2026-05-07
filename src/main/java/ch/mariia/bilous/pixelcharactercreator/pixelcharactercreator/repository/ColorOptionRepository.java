package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.ColorOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorOptionRepository extends JpaRepository<ColorOption, Long> {
    List<ColorOption> findByCategoryIgnoreCase(String category);
}