package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.AppUser;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CharacterEntity;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class CharacterRepositoryTest {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldPerformCrudOperations() {
        String keycloakId = "user-" + System.currentTimeMillis();

        AppUser user = new AppUser();
        user.setKeycloakId(keycloakId);
        user.setUsername("testuser");
        user.setRole(Role.USER);
        user = userRepository.save(user);

        CharacterEntity character = new CharacterEntity();
        character.setName("Sprite 1");
        character.setGender("female");
        character.setHeight(64);
        character.setBodyType("small");
        character.setSkinColor("#FFFFFF");
        character.setHairColor("#000000");
        character.setEyeColor("#0000FF");
        character.setLipColor("#FFAAAA");
        character.setOutlineColor("#111111");
        character.setOwner(user);

        CharacterEntity saved = characterRepository.save(character);

        assertThat(saved.getId()).isNotNull();

        CharacterEntity found = characterRepository.findById(saved.getId()).orElseThrow();

        assertThat(found.getName()).isEqualTo("Sprite 1");
        assertThat(found.getOwner().getKeycloakId()).isEqualTo(keycloakId);

        found.setName("Sprite Updated");
        characterRepository.save(found);

        CharacterEntity updated = characterRepository.findById(saved.getId()).orElseThrow();

        assertThat(updated.getName()).isEqualTo("Sprite Updated");

        assertThat(characterRepository.findByOwnerKeycloakId(keycloakId)).hasSize(1);
        assertThat(characterRepository.findByIdAndOwnerKeycloakId(saved.getId(), keycloakId)).isPresent();
        assertThat(characterRepository.countByOwnerKeycloakId(keycloakId)).isEqualTo(1);

        characterRepository.delete(updated);

        assertThat(characterRepository.findById(saved.getId())).isEmpty();
    }
}