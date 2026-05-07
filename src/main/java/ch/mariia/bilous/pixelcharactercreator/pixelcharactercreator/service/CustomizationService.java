package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto.ColorOptionRequest;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.dto.CustomizationItemRequest;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.exception.ResourceNotFoundException;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.ColorOption;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CustomizationItem;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.CustomizationType;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository.ColorOptionRepository;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository.CustomizationItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomizationService {
    private final CustomizationItemRepository itemRepository;
    private final ColorOptionRepository colorRepository;

    public CustomizationService(CustomizationItemRepository itemRepository, ColorOptionRepository colorRepository) {
        this.itemRepository = itemRepository;
        this.colorRepository = colorRepository;
    }

    public List<CustomizationItem> findActiveItems() { return itemRepository.findByActiveTrue(); }
    public List<CustomizationItem> findActiveItemsByType(CustomizationType type) { return itemRepository.findByTypeAndActiveTrue(type); }
    public List<ColorOption> findColors() { return colorRepository.findAll(); }

    public CustomizationItem createItem(CustomizationItemRequest request) {
        CustomizationItem item = new CustomizationItem();
        updateItemFields(item, request);
        return itemRepository.save(item);
    }

    public CustomizationItem updateItem(Long id, CustomizationItemRequest request) {
        CustomizationItem item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        updateItemFields(item, request);
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) { itemRepository.deleteById(id); }

    public ColorOption createColor(ColorOptionRequest request) {
        ColorOption color = new ColorOption();
        updateColorFields(color, request);
        return colorRepository.save(color);
    }

    public ColorOption updateColor(Long id, ColorOptionRequest request) {
        ColorOption color = colorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Color not found"));
        updateColorFields(color, request);
        return colorRepository.save(color);
    }

    public void deleteColor(Long id) { colorRepository.deleteById(id); }

    private void updateItemFields(CustomizationItem item, CustomizationItemRequest request) {
        item.setName(request.name());
        item.setType(request.type());
        item.setImagePath(request.imagePath());
        item.setActive(request.active());
    }

    private void updateColorFields(ColorOption color, ColorOptionRequest request) {
        color.setName(request.name());
        color.setHexCode(request.hexCode());
    }
}
