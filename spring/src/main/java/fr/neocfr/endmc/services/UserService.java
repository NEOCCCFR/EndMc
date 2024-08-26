package fr.neocfr.endmc.services;

import fr.neocfr.endmc.dto.UserDTO;
import fr.neocfr.endmc.users.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    boolean existsByUniqueId(UUID uniqueId);

    User createProfile(User profile);

    Optional<User> findByUniqueId(UUID uniqueId);

    UserDTO convertToDto(User profile);

    User convertToEntity(UserDTO profileDTO);

}
