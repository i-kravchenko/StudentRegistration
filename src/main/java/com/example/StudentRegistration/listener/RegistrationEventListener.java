package com.example.StudentRegistration.listener;

import com.example.StudentRegistration.dto.StudentsList;
import com.example.StudentRegistration.event.RegistrationEvent;
import com.example.StudentRegistration.shell.StudentRegistrationShell;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventListener
{
    private final StudentRegistrationShell application;
    private final StudentsList studentsList;

    @Autowired
    public RegistrationEventListener(StudentRegistrationShell application, StudentsList studentsList) {
        this.application = application;
        this.studentsList = studentsList;
    }

    @PostConstruct
    public void PostConstruct()
    {
        studentsList.getStudents().forEach(application::add);
    }

    @EventListener
    public void listenRegistrationEvents(RegistrationEvent event) {
        System.out.println(event.getMessage());
    }
}
