package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto.CharacterRequest;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.exception.ResourceNotFoundException;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.AppUser;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CharacterEntity;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CustomizationItem;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository.CharacterRepository;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository.CustomizationItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CustomizationItemRepository itemRepository;

    public CharacterService(CharacterRepository characterRepository, CustomizationItemRepository itemRepository) {
        this.characterRepository = characterRepository;
        this.itemRepository = itemRepository;
    }

    public List<CharacterEntity> findOwnCharacters(String keycloakId) {
        return characterRepository.findByOwnerKeycloakId(keycloakId);
    }

    public List<CharacterEntity> findAllCharacters() {
        return characterRepository.findAll();
    }

    public CharacterEntity findOwnCharacter(Long id, String keycloakId) {
        return characterRepository.findByIdAndOwnerKeycloakId(id, keycloakId)
                .orElseThrow(() -> new ResourceNotFoundException("Character not found"));
    }

    public CharacterEntity create(CharacterRequest request, AppUser owner) {
        CharacterEntity character = new CharacterEntity();
        character.setOwner(owner);
        applyRequest(character, request);
        if (character.getName() == null || character.getName().isBlank()) {
            character.setName("Sprite " + (characterRepository.countByOwnerKeycloakId(owner.getKeycloakId()) + 1));
        }
        return characterRepository.save(character);
    }

    public CharacterEntity update(Long id, CharacterRequest request, String keycloakId) {
        CharacterEntity character = findOwnCharacter(id, keycloakId);
        applyRequest(character, request);
        return characterRepository.save(character);
    }

    public void delete(Long id, String keycloakId) {
        CharacterEntity character = findOwnCharacter(id, keycloakId);
        characterRepository.delete(character);
    }

    private void applyRequest(CharacterEntity character, CharacterRequest request) {
        character.setName(request.name());
        character.setGender(request.gender());
        character.setHeight(request.height());
        character.setBodyType(request.bodyType());
        character.setSkinColor(request.skinColor());
        character.setHairColor(request.hairColor());
        character.setEyeColor(request.eyeColor());
        character.setLipColor(request.lipColor());
        character.setOutlineColor(request.outlineColor());
        character.setBody(findItem(request.bodyId()));
        character.setFace(findItem(request.faceId()));
        character.setHair(findItem(request.hairId()));
        character.setClothes(findItem(request.clothesId()));
        character.setBackground(findItem(request.backgroundId()));
        character.setAccessory(findItem(request.accessoryId()));
    }

    private CustomizationItem findItem(Long id) {
        if (id == null) return null;
        return itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customization item not found"));
    }
}
