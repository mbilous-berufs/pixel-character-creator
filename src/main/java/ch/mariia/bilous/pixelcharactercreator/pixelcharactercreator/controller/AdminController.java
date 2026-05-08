package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.controller;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto.ColorOptionRequest;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto.CustomizationItemRequest;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.ColorOption;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CustomizationItem;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service.AdminService;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service.CustomizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin", description = "Admin management for global customization data")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {
    private final CustomizationService customizationService;
    private final AdminService adminService;

    public AdminController(CustomizationService customizationService, AdminService adminService) {
        this.customizationService = customizationService;
        this.adminService = adminService;
    }

    @GetMapping
    @Operation(summary = "Get admin info")
    public String getAdminInfo() { return adminService.getAdminInfo(); }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create customization item")
    public CustomizationItem createItem(@Valid @RequestBody CustomizationItemRequest request) {
        return customizationService.createItem(request);
    }

    @PutMapping("/items/{id}")
    @Operation(summary = "Update customization item")
    public CustomizationItem updateItem(@PathVariable Long id, @Valid @RequestBody CustomizationItemRequest request) {
        return customizationService.updateItem(id, request);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete customization item")
    public void deleteItem(@PathVariable Long id) { customizationService.deleteItem(id); }

    @PostMapping("/colors")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create color")
    public ColorOption createColor(@Valid @RequestBody ColorOptionRequest request) {
        return customizationService.createColor(request);
    }

    @PutMapping("/colors/{id}")
    @Operation(summary = "Update color")
    public ColorOption updateColor(@PathVariable Long id, @Valid @RequestBody ColorOptionRequest request) {
        return customizationService.updateColor(id, request);
    }

    @DeleteMapping("/colors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete color")
    public void deleteColor(@PathVariable Long id) { customizationService.deleteColor(id); }
}
