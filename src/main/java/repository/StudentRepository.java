package repository;

import configuration.Config;
import dto.AddStudentDto;
import dto.DeleteStudentDto;
import service.exception.UnableToExecuteQueryException;
import service.exception.UnableToGetConnectionException;
import lombok.AllArgsConstructor;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StudentRepository {

    private static final String SAVE_STUDENT =
            "insert into students (name,surname,patronymic,birth_date,study_group) values (?,?,?,?,?)";
    private static final String GET_STUDENT = "select * from STUDENTS where name =? and surname=? and patronymic=? and birth_date=? and study_group=?";
    private static final String DELETE_STUDENT_BY_ID = "delete from students where id=?";
    private static final String SELECT_ALL_STUDENTS = "select * from students";
    private static final String STUDENT_ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PATRONYMIC = "patronymic";
    private static final String BIRTH_DATE = "birth_date";
    private static final String STUDY_GROUP = "study_group";

    private final Config config;

    protected Connection getConnection() {
        try {
            return DataSource.getInstance(config).getConnection();
        } catch (SQLException e) {
            throw new UnableToGetConnectionException(e.getMessage());
        }
    }

    public boolean studentIsPresent(AddStudentDto addStudentDto) {

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT);

            preparedStatement.setString(1, addStudentDto.getName());
            preparedStatement.setString(2, addStudentDto.getSurname());
            preparedStatement.setString(3, addStudentDto.getPatronymic());
            preparedStatement.setString(4, addStudentDto.getBirthDate());
            preparedStatement.setString(5, addStudentDto.getGroup());

            ResultSet rs = preparedStatement.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new UnableToExecuteQueryException(e.getMessage());
        }

    }


    public void save(AddStudentDto addStudentDto) {

        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_STUDENT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, addStudentDto.getName());
            preparedStatement.setString(2, addStudentDto.getSurname());
            preparedStatement.setString(3, addStudentDto.getPatronymic());
            preparedStatement.setString(4, addStudentDto.getBirthDate());
            preparedStatement.setString(5, addStudentDto.getGroup());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new UnableToExecuteQueryException(e.getMessage());
        }
    }


    public void delete(DeleteStudentDto deleteStudentDto) {

        try {

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_BY_ID);
            preparedStatement.setLong(1, deleteStudentDto.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new UnableToExecuteQueryException(e.getMessage());
        }

    }


    public List<Student> getAllStudents() {

        try {
            List<Student> transactionList = new ArrayList<>();

            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                transactionList.add(Student.builder()
                        .id(rs.getInt(STUDENT_ID))
                        .name(rs.getString(NAME))
                        .surname(rs.getString(SURNAME))
                        .patronymic(rs.getString(PATRONYMIC))
                        .birthDate(rs.getString(BIRTH_DATE))
                        .group(rs.getString(STUDY_GROUP))
                        .build());
            }
            return transactionList;

        } catch (SQLException e) {
            throw new UnableToExecuteQueryException(e.getMessage());
        }

    }
}
