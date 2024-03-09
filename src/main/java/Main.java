import ru_omsu_fctk_simpleserver.server.Reader;
import ru_omsu_fctk_simpleserver.server.Server;

/**
 * Group:
 * addStudentGroups::{\"name\":\"имя группы\"}
 * editStudentGroups::{\"id\":\"id группы\",\"имя группы\":\"\"}
 * deleteStudentGroup::{"id":"id группы"}
 * getStudentGroupById::{"id":"id группы"}
 * getStudentGroups::
 * [----------------------------------------------------------------------------------------------------------------------------]
 * Student:
 * addStudent::{\"groupId\":\"id группы\",\"surname\":\"фамилия\",\"name\":\"имя\",\"patronymic\":\"отчество\",\"status\":\"статус\"}
 * editStudent::{\"id\":\"id студента\",\"groupId\":\"id группы\",\"surname\":\"фамилия\",\"name\":\"имя\",\"patronymic\":\"отчество\",\"status\":\"статус\"}
 * deleteStudent::{\"id\":\"id студента\"}
 * getStudentById::{\"id\":\"id студента\"}
 * getStudentByGroup::{\"id\":\"id группы\"}
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.initGroup();
        server.initStudent();

        try {
            Reader[] readers = {
                    new Reader("addStudentGroups::{\"name\":\"MMB-102\"}"),
                    new Reader("getStudentGroupById::{\"id\":\"1\"}"),
                    new Reader("editStudentGroups::{\"id\":\"1\",\"name\":\"MMB-103\"}"),
                    new Reader("getStudentGroupById::{\"id\":\"1\"}")
                    /*new Reader("addStudentGroups::{\"name\":\"MMB-101\"}"),
                    new Reader("addStudentGroups::{\"name\":\"MMB-102\"}"),
                    new Reader("addStudentGroups::{\"name\":\"MMB-103\"}"),
                    new Reader("addStudentGroups::{\"name\":\"MMB-104\"}"),
                    new Reader("getStudentGroups:: "),

                     */

                    /*
                    new Reader("addStudent::{\"groupId\":\"1\",\"surname\":\"Носов\",\"name\":\"Никита\",\"patronymic\":\"Отчество\",\"status\":\"учится\"}"),

                    new Reader("getStudentById::{\"id\":\"1\"}"),

                    new Reader("addStudent::{\"groupId\":\"3\",\"surname\":\"Алёшкин\",\"name\":\"Александр\",\"patronymic\":\"Отчество\",\"status\":\"учится\"}"),
                    new Reader("getStudentById::{\"id\":\"2\"}"),

                    new Reader("addStudent::{\"groupId\":\"4\",\"surname\":\"Кошевая\",\"name\":\"Анастасия\",\"patronymic\":\"Отчество\",\"status\":\"учится\"}"),
                    new Reader("getStudentById::{\"id\":\"3\"}"),

                    new Reader("getStudentByGroup::{\"id\":\"1\"}"),
                    new Reader("getStudentByGroup::{\"id\":\"3\"}"),
                    new Reader("getStudentByGroup::{\"id\":\"4\"}"),

                    new Reader("editStudent::{\"id\":\"1\",\"groupId\":\"1\",\"surname\":\"Носов\",\"name\":\"Никита\",\"patronymic\":\"Отчество\",\"status\":\"отчислен\"}"),
                    new Reader("getStudentById::{\"id\":\"1\"}"),

                    new Reader("editStudent::{\"id\":\"2\",\"groupId\":\"4\",\"surname\":\"Алёшкин\",\"name\":\"Александр\",\"patronymic\":\"Отчество\",\"status\":\"учится\"}"),
                    new Reader("getStudentByGroup::{\"id\":\"4\"}"),

                    new Reader("deleteStudent::{\"id\":\"1\"}"),
                    new Reader("getStudentById::{\"id\":\"1\"}")

                    */

                    /*new Reader("getStudentGroupById::{\"id\":\"1\"}"),
                     new Reader("getStudentGroupById::{\"id\":\"2\"}"),
                     new Reader("getStudentGroupById::{\"id\":\"3\"}"),
                     new Reader("getStudentGroupById::{\"id\":\"4\"}"),

                     new Reader("editStudentGroups::{\"id\":\"3\",\"name\":\"\"}"),
                     new Reader("getStudentGroupById::{\"id\":\"3\"}"),

                     new Reader("deleteStudentGroup::{\"id\":\"1\"}"),
                     new Reader("getStudentGroupById::{\"id\":\"1\"}"),

                     new Reader("deleteStudentGroup::{\"id\":\"2\"}"),
                     new Reader("getStudentGroups:: "),

                     new Reader("editStudentGroups::{\"id\":\"3\",\"name\":\"MMB-103-O1\"}"),
                     new Reader("editStudentGroups::{\"id\":\"4\",\"name\":\"MMB-104-O2\"}"),
                     new Reader("getStudentGroups:: ")*/
            };

            for (Reader reader : readers) {
                System.out.println(server.executeRequest(reader));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
