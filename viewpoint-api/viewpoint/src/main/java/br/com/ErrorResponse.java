package br.com;

public class ErrorResponse  {

    private String errorMessage;

    public ErrorResponse(String message) {
        this.setErrorMessage(message);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}