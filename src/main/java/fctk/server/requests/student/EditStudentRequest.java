package fctk.server.requests.student;

public record EditStudentRequest(long id, String surname, String name, String patronymic, String status, long groupId) {
}
