package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto;

public record CharacterRequest(
        String name,
        String gender,
        Integer height,
        String bodyType,
        String skinColor,
        String hairColor,
        String eyeColor,
        String lipColor,
        String outlineColor,
        Long bodyId,
        Long faceId,
        Long hairId,
        Long clothesId,
        Long backgroundId,
        Long accessoryId
) {}
