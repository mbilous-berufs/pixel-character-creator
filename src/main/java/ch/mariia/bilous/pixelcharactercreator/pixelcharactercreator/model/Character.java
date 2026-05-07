package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 100)
    private String name;

    private String gender;
    private Integer height;
    private String bodyType;
    private String skinColor;
    private String hairColor;
    private String eyeColor;
    private String lipColor;
    private String outlineColor;

    @ManyToOne private CustomizationItem body;
    @ManyToOne private CustomizationItem face;
    @ManyToOne private CustomizationItem hair;
    @ManyToOne private CustomizationItem clothes;
    @ManyToOne private CustomizationItem background;
    @ManyToOne private CustomizationItem accessory;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Integer getHeight() { return height; }
    public void setHeight(Integer height) { this.height = height; }
    public String getBodyType() { return bodyType; }
    public void setBodyType(String bodyType) { this.bodyType = bodyType; }
    public String getSkinColor() { return skinColor; }
    public void setSkinColor(String skinColor) { this.skinColor = skinColor; }
    public String getHairColor() { return hairColor; }
    public void setHairColor(String hairColor) { this.hairColor = hairColor; }
    public String getEyeColor() { return eyeColor; }
    public void setEyeColor(String eyeColor) { this.eyeColor = eyeColor; }
    public String getLipColor() { return lipColor; }
    public void setLipColor(String lipColor) { this.lipColor = lipColor; }
    public String getOutlineColor() { return outlineColor; }
    public void setOutlineColor(String outlineColor) { this.outlineColor = outlineColor; }
    public AppUser getOwner() { return owner; }
    public void setOwner(AppUser owner) { this.owner = owner; }
    public CustomizationItem getBody() { return body; }
    public void setBody(CustomizationItem body) { this.body = body; }
    public CustomizationItem getFace() { return face; }
    public void setFace(CustomizationItem face) { this.face = face; }
    public CustomizationItem getHair() { return hair; }
    public void setHair(CustomizationItem hair) { this.hair = hair; }
    public CustomizationItem getClothes() { return clothes; }
    public void setClothes(CustomizationItem clothes) { this.clothes = clothes; }
    public CustomizationItem getBackground() { return background; }
    public void setBackground(CustomizationItem background) { this.background = background; }
    public CustomizationItem getAccessory() { return accessory; }
    public void setAccessory(CustomizationItem accessory) { this.accessory = accessory; }
}
