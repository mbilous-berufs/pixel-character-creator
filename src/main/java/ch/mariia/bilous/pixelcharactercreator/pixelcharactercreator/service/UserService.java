package ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.service;

import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.AppUser;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.model.Role;
import ch.mariia.bilous.pixelcharactercreator.pixelcharactercreator.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser getOrCreateCurrentUser(Jwt jwt) {
        String keycloakId = jwt.getSubject();
        return userRepository.findByKeycloakId(keycloakId).orElseGet(() -> {
            AppUser user = new AppUser();
            user.setKeycloakId(keycloakId);
            user.setUsername(jwt.getClaimAsString("preferred_username") != null ? jwt.getClaimAsString("preferred_username") : keycloakId);
            user.setRole(Role.USER);
            return userRepository.save(user);
        });
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }
}
