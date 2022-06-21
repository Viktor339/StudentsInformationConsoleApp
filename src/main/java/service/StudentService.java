package service;

import dto.AddStudentDto;
import dto.DeleteStudentDto;
import service.exception.StudentAlreadyExistsException;
import lombok.AllArgsConstructor;
import model.Student;
import repository.StudentRepository;
import service.validator.Validator;
import service.validator.ValidatorService;

import java.util.List;

@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;
    private ValidatorService validatorService;
    private List<Validator<AddStudentDto>> addStudentValidators;
    private List<Validator<DeleteStudentDto>> deleteStudentValidators;

    public void saveStudent(AddStudentDto addStudentDto) {

        validatorService.validate(addStudentValidators, addStudentDto);

        if (studentRepository.studentIsPresent(addStudentDto)) {
            throw new StudentAlreadyExistsException("Student with these parameters already exist");
        }
        studentRepository.save(addStudentDto);
    }

    public void delete(DeleteStudentDto deleteStudentDto) {

        validatorService.validate(deleteStudentValidators, deleteStudentDto);

        studentRepository.delete(deleteStudentDto);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

}
