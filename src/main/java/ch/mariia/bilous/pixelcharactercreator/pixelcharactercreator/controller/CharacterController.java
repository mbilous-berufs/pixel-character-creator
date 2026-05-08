package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.controller;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto.CharacterRequest;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.AppUser;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CharacterEntity;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service.CharacterService;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@Tag(name = "Characters", description = "CRUD operations for pixel characters")
@SecurityRequirement(name = "bearerAuth")
public class CharacterController {
    private final CharacterService characterService;
    private final UserService userService;

    public CharacterController(CharacterService characterService, UserService userService) {
        this.characterService = characterService;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get own characters")
    public List<CharacterEntity> getOwnCharacters(@AuthenticationPrincipal Jwt jwt) {
        return characterService.findOwnCharacters(jwt.getSubject());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get one own character")
    public CharacterEntity getOwnCharacter(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        return characterService.findOwnCharacter(id, jwt.getSubject());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Create character")
    public CharacterEntity createCharacter(@Valid @RequestBody CharacterRequest request, @AuthenticationPrincipal Jwt jwt) {
        AppUser owner = userService.getOrCreateCurrentUser(jwt);
        return characterService.create(request, owner);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Update own character")
    public CharacterEntity updateCharacter(@PathVariable Long id, @Valid @RequestBody CharacterRequest request, @AuthenticationPrincipal Jwt jwt) {
        return characterService.update(id, request, jwt.getSubject());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Delete own character")
    public void deleteCharacter(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        characterService.delete(id, jwt.getSubject());
    }
}
