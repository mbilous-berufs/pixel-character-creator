package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CustomizationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomizationItemRequest(
        @NotBlank String name,
        @NotNull CustomizationType type,
        @NotBlank String imagePath,
        boolean active
) {}
