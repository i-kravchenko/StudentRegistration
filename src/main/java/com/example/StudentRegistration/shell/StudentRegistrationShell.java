package com.example.StudentRegistration.shell;

import com.example.StudentRegistration.dto.Student;
import com.example.StudentRegistration.event.RegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class StudentRegistrationShell
{
    private final Map<Integer, Student> repository = new HashMap<>();
    private final ApplicationEventPublisher publisher;

    @Autowired
    public StudentRegistrationShell(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @ShellMethod(key = "ls", value = "show current students list")
    public void list() {
        repository.values().forEach(System.out::println);
    }

    @ShellMethod("add new student")
    public void add(
            @ShellOption(value = "name") String firstName,
            @ShellOption(value = "lname") String lastName,
            @ShellOption(value = "age") Integer age) {
        Student student = new Student(getNextId(), firstName, lastName, age);
        repository.put(student.getId(), student);
        publisher.publishEvent(new RegistrationEvent(
                this,
                MessageFormat.format("new student registered: {0}", student)
        ));
    }

    public void add(Student student) {
        student.setId(getNextId());
        repository.put(student.getId(), student);
    }

    private int getNextId() {
        return repository.size() + 1;
    }

    @ShellMethod(key = "rm", value = "delete student by id")
    public void delete(Integer id) {
        repository.remove(id);
        publisher.publishEvent(new RegistrationEvent(
                this,
                MessageFormat.format("student {0} was deleted", id)
        ));
    }

    @ShellMethod(key = "cln", value = "delete all students")
    public void clean()
    {
        repository.clear();
        publisher.publishEvent(new RegistrationEvent(
                this,
                "students list was cleared"
        ));
    }
}
