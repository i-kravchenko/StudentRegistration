package com.example.StudentRegistration.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RegistrationEvent extends ApplicationEvent
{
    private final String message;

    public RegistrationEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
