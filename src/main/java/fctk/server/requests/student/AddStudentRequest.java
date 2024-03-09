package fctk.server.requests.student;


public record AddStudentRequest(String surname, String name, String patronymic, long groupId, String status) {
}
