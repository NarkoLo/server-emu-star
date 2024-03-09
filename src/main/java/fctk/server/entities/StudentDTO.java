package fctk.server.entities;

public record StudentDTO(
        Long id,
        String surname,
        String name,
        String patronymic,
        String status,
        GroupDTO group
) {
}
