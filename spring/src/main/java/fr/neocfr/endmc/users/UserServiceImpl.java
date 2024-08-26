package fr.neocfr.endmc.users;

import fr.neocfr.endmc.dto.UserDTO;
import fr.neocfr.endmc.exceptions.CreationError;
import fr.neocfr.endmc.exceptions.ExecutionError;
import fr.neocfr.endmc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean existsByUniqueId(UUID uniqueId) {
        try {
            return repository.existsByUniqueId(uniqueId);
        } catch (Throwable e) {
            throw new ExecutionError("Failed to check existence of player profile", e);
        }
    }

    @Override
    public User createProfile(User profile) {
        if (existsByUniqueId(profile.getUniqueId()))
            throw new CreationError("Profile %s already exists".formatted(profile.getUniqueId().toString()));
        try {
            return repository.save(profile);
        } catch (Throwable e) {
            throw new ExecutionError("Failed to save player profile", e);
        }
    }

    @Override
    public Optional<User> findByUniqueId(UUID uniqueId) {
        try {
            return repository.findByUniqueId(uniqueId);
        } catch (Throwable e) {
            throw new ExecutionError("Failed to fetch player profile", e);
        }
    }

    @Override
    public UserDTO convertToDto(User profile) {
        return new UserDTO(profile.getId(), profile.getUniqueId(), profile.getName());
    }

    @Override
    public User convertToEntity(UserDTO profileDTO) {
        return new User(profileDTO.id(), profileDTO.uniqueId(), profileDTO.name());
    }
}
