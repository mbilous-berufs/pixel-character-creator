package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String keycloakId;

    @NotBlank // for String: cannot be empty
    @Column(nullable = false)
    @Size(min = 3, max = 20)
    private String username;

    @NotNull // for Java: cannot be equal Null
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)  // for db: cannot be equal to null
    private Role role = Role.USER;

    @JsonIgnore //@JsonIgnore means: when the backend returns a user as JSON,
                // this field automatically won't be shown in the JSON response.
                // To avoid an infinite loop:
    //'User' contains 'Character'
    //'Character' contains 'User'
                //And also to avoid returning unnecessary data.
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterEntity> characterEntities = new ArrayList<>();
//cascade = CascadeType.ALL means:
    //Actions performed on a user also apply to its characters.
    //For example, if you save a user with a new character, the character will also be preserved.
    //If you delete a user, its characters will also be deleted.
//orphanRemoval = true means:
    //If a character is removed from the user.characters list, it will be deleted from the database.

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getKeycloakId() { return keycloakId; }
    public void setKeycloakId(String keycloakId) { this.keycloakId = keycloakId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public List<CharacterEntity> getCharacters() { return characterEntities; }
    public void setCharacters(List<CharacterEntity> characterEntities) { this.characterEntities = characterEntities; }
}
