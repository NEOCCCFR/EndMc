package fr.neocfr.endmc.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(Long id, UUID uniqueId, String name) {}
