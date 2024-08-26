package fr.neocfr.endmc.users;

import fr.neocfr.endmc.dto.UserDTO;
import fr.neocfr.endmc.exceptions.NotFoundError;
import fr.neocfr.endmc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@RestController
@RequestMapping("/players")
public class UserController {

    @Autowired
    private UserService service;

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO create(@RequestBody UserDTO playerProfileDTO) {
        User entity = service.convertToEntity(playerProfileDTO);
        User result = service.createProfile(entity);

        return service.convertToDto(result);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> get(@PathVariable("id") UUID uniqueId) {
        return service.findByUniqueId(uniqueId).map(service::convertToDto).map(ResponseEntity::ok).orElseThrow(
                () -> new NotFoundError("Profile %s not found".formatted(uniqueId.toString())));
    }
}
