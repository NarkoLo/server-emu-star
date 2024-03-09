package fctk.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fctk.server.commands.DefaultCommand;
import fctk.server.commands.ICommand;
import fctk.server.commands.group.*;
import fctk.server.commands.student.*;
import fctk.server.commands.teacher.AddTeacherCommand;
import fctk.server.commands.teacher.GetTeacherCommand;
import fctk.server.controllers.GroupController;
import fctk.server.controllers.StudentController;
import fctk.server.controllers.SubjectController;
import fctk.server.controllers.TeacherController;
import fctk.server.repositories.*;
import fctk.server.services.GroupService;
import fctk.server.services.StudentService;
import fctk.server.services.SubjectService;
import fctk.server.services.TeacherService;
import fctk.server.validators.IdRequestValidator;
import fctk.server.validators.group.AddStudentGroupValidator;
import fctk.server.validators.group.EditStudentGroupValidator;
import fctk.server.validators.primitive.DateValidator;
import fctk.server.validators.primitive.LongValidator;
import fctk.server.validators.primitive.StringValidator;
import fctk.server.validators.student.AddStudentValidator;
import fctk.server.validators.student.EditStudentValidator;
import fctk.server.validators.subject.AddSubjectValidator;
import fctk.server.validators.subject.EditSubjectValidator;
import fctk.server.validators.teacher.AddTeacherValidator;

import java.util.HashMap;
import java.util.Map;

public class Server implements IServer {

    private final Map<String, ICommand> commands;

    public Server() {
        DateValidator dateValidator = new DateValidator();
        LongValidator longValidator = new LongValidator();
        StringValidator stringValidator = new StringValidator();
        IdRequestValidator idRequestValidator = new IdRequestValidator(longValidator);
        AddStudentGroupValidator addStudentGroupValidator = new AddStudentGroupValidator(stringValidator);
        EditStudentGroupValidator editStudentGroupValidator = new EditStudentGroupValidator(longValidator, stringValidator);
        AddStudentValidator addStudentValidator = new AddStudentValidator(stringValidator, longValidator);
        EditStudentValidator editStudentValidator = new EditStudentValidator(stringValidator, longValidator);
        AddSubjectValidator addSubjectValidator = new AddSubjectValidator(stringValidator);
        EditSubjectValidator editSubjectValidator = new EditSubjectValidator(stringValidator, longValidator);
        AddTeacherValidator addTeacherValidator = new AddTeacherValidator(stringValidator);

        DataBase dataBase = new DataBase(
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>()
        );
        GroupRepository groupRepository = new GroupRepository(dataBase);
        StudentRepository studentRepository = new StudentRepository(dataBase);
        SubjectRepository subjectRepository = new SubjectRepository(dataBase);
        GroupService groupService = new GroupService(groupRepository);
        StudentService studentService = new StudentService(studentRepository);
        SubjectService subjectService = new SubjectService(subjectRepository);
        GroupController groupController = new GroupController(
                groupService,
                addStudentGroupValidator,
                editStudentGroupValidator,
                idRequestValidator
        );
        StudentController studentController = new StudentController(
                studentService,
                groupService,
                idRequestValidator,
                addStudentValidator,
                editStudentValidator
        );
        SubjectController subjectController = new SubjectController(
                subjectService,
                addSubjectValidator,
                editSubjectValidator,
                idRequestValidator
        );

        TeacherRepository teacherRepository = new TeacherRepository(dataBase);
        TeacherService teacherService = new TeacherService(teacherRepository);
        TeacherController teacherController = new TeacherController(
                teacherService,
                addTeacherValidator,
                idRequestValidator
        );

        ObjectMapper mapper = new ObjectMapper();

        commands = new HashMap<>();

        commands.put("addGroup", new AddGroupCommand(groupController, mapper));
        commands.put("deleteGroup", new DeleteStudentGroupCommand(groupController, mapper));
        commands.put("editGroup", new EditStudentGroupCommand(groupController, mapper));
        commands.put("getGroupById", new GetStudentGroupByIdCommand(groupController, mapper));
        commands.put("getGroups", new GetStudentGroupsCommand(groupController, mapper));

        commands.put("addStudent", new AddStudentCommand(studentController, mapper));
        commands.put("deleteStudent", new DeleteStudentCommand(studentController, mapper));
        commands.put("editStudent", new EditStudentCommand(studentController, mapper));
        commands.put("getStudent", new GetStudentCommand(studentController, mapper));
        commands.put("getStudentsByGroupId", new GetStudentsByGroupIdCommand(studentController, mapper));

        commands.put("addTeacher", new AddTeacherCommand(teacherController, mapper));
        commands.put("getTeacherById", new GetTeacherCommand(teacherController, mapper));
    }

    @Override
    public String executeRequest(String endPoint, String json) throws JsonProcessingException {
        return commands.getOrDefault(endPoint, new DefaultCommand()).executeToJSON(json);
    }
}