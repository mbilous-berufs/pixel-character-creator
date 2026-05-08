package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.controller;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.ColorOption;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CustomizationItem;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CustomizationType;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service.CustomizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customization")
@Tag(name = "Customization", description = "Available character customization objects")
@SecurityRequirement(name = "bearerAuth")
public class CustomizationController {
    private final CustomizationService customizationService;

    public CustomizationController(CustomizationService customizationService) {
        this.customizationService = customizationService;
    }

    @GetMapping("/items")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get all active customization items")
    public List<CustomizationItem> getItems() {
        return customizationService.findActiveItems();
    }

    @GetMapping("/items/{type}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get active customization items by type")
    public List<CustomizationItem> getItemsByType(@PathVariable CustomizationType type) {
        return customizationService.findActiveItemsByType(type);
    }

    @GetMapping("/colors")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get all colors")
    public List<ColorOption> getColors() {
        return customizationService.findColors();
    }
}
