package br.com;

public class ErrorResponse  {

    private String field;
    private String message;

    public ErrorResponse(String field,String message) {
        this.setField(field);
        this.message = message;
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
}