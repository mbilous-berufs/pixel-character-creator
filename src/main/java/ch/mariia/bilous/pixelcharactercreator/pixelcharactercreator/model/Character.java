package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "characters")
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

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private AppUser owner;
}
