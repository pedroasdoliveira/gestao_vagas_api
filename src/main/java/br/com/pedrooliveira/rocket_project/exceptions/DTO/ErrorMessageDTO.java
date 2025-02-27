package br.com.pedrooliveira.rocket_project.exceptions.DTO;

import java.util.Objects;

public class ErrorMessageDTO {

    private String message;
    private String field;

    public ErrorMessageDTO(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessageDTO that = (ErrorMessageDTO) o;
        return Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getField(), that.getField());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage(), getField());
    }

    @Override
    public String toString() {
        return "ErrorMessageDTO{" +
                "message='" + message + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}