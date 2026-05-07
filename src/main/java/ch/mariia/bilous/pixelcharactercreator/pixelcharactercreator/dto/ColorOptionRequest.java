package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ColorOptionRequest(
        @NotBlank String name,
        @Pattern(regexp = "^#[0-9A-Fa-f]{6}$") String hexCode,
        @NotBlank String category
) {}
