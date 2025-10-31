package br.com.v1.barber.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing different error types and their messages.
 */
@Getter
@AllArgsConstructor
public enum Error {

    NO_CLIENT_FOUND("NO_CLIENT_FOUND", "No CLIENT were found."),
    NO_CLIENT_FOUND_BY_ID("NO_CLIENT_FOUND_BY_ID", "No CLIENT was found by ID."),
    NO_CLIENT_FOUND_BY_FIRST_AND_LAST_NAME("NO_CLIENT_FOUND_BY_FIRST_AND_LAST_NAME",
            "No CLIENT was found by first and last name."),
    CLIENT_ALREADY_EXISTS("CLIENT_ALREADY_EXISTS", "CLIENT already exists."),


    NO_SERVICE_FOUND("NO_SERVICE_FOUND", "No SERVICE were found."),
    NO_SERVICE_FOUND_BY_ID("NO_SERVICE_FOUND_BY_ID", "No SERVICE was found by ID."),
    SERVICE_ALREADY_EXISTS("SERVICE_ALREADY_EXISTS", "SERVICE already exists."),

    NO_EMPLOYEE_FOUND("NO_EMPLOYEE_FOUND", "No EMPLOYEE were found."),
    NO_EMPLOYEE_FOUND_BY_ID("NO_EMPLOYEE_FOUND_BY_ID", "No EMPLOYEE was found by ID."),
    EMPLOYEE_ALREADY_EXISTS("EMPLOYEE_ALREADY_EXISTS", "EMPLOYEE already exists."),;

    private final String errorMessage;
    private final String errorDescription;
}
