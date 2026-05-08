package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.controller;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto.CharacterRequest;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.AppUser;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CharacterEntity;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.Role;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service.CharacterService;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CharacterControllerTest {

    @Test
    void shouldPerformCrudControllerOperations() {
        CharacterService characterService = mock(CharacterService.class);
        UserService userService = mock(UserService.class);

        CharacterController controller = new CharacterController(characterService, userService);

        Jwt jwt = createJwt();

        AppUser user = new AppUser();
        user.setId(1L);
        user.setKeycloakId("user-1");
        user.setUsername("testuser");
        user.setRole(Role.USER);

        CharacterEntity character = new CharacterEntity();
        character.setId(1L);
        character.setName("Sprite 1");
        character.setGender("female");
        character.setHeight(64);
        character.setOwner(user);

        CharacterRequest request = new CharacterRequest(
                "Sprite 1",
                "female",
                64,
                "small",
                "#FFFFFF",
                "#000000",
                "#0000FF",
                "#FFAAAA",
                "#111111",
                null,
                null,
                null,
                null,
                null,
                null
        );

        when(characterService.findOwnCharacters("user-1")).thenReturn(List.of(character));
        when(characterService.findOwnCharacter(1L, "user-1")).thenReturn(character);
        when(userService.getOrCreateCurrentUser(jwt)).thenReturn(user);
        when(characterService.create(request, user)).thenReturn(character);
        when(characterService.update(1L, request, "user-1")).thenReturn(character);

        List<CharacterEntity> characters = controller.getOwnCharacters(jwt);
        CharacterEntity found = controller.getOwnCharacter(1L, jwt);
        CharacterEntity created = controller.createCharacter(request, jwt);
        CharacterEntity updated = controller.updateCharacter(1L, request, jwt);
        controller.deleteCharacter(1L, jwt);

        assertThat(characters).hasSize(1);
        assertThat(found.getName()).isEqualTo("Sprite 1");
        assertThat(created.getName()).isEqualTo("Sprite 1");
        assertThat(updated.getName()).isEqualTo("Sprite 1");

        verify(characterService).findOwnCharacters("user-1");
        verify(characterService).findOwnCharacter(1L, "user-1");
        verify(userService).getOrCreateCurrentUser(jwt);
        verify(characterService).create(request, user);
        verify(characterService).update(1L, request, "user-1");
        verify(characterService).delete(1L, "user-1");
    }

    private Jwt createJwt() {
        return new Jwt(
                "token",
                Instant.now(),
                Instant.now().plusSeconds(3600),
                Map.of("alg", "none"),
                Map.of(
                        "sub", "user-1",
                        "preferred_username", "testuser"
                )
        );
    }
}