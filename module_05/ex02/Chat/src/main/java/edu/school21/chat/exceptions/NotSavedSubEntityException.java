package edu.school21.chat.exceptions;

public class NotSavedSubEntityException extends RuntimeException {

    @Override
    public void printStackTrace() {
        System.out.println("The message can't be saved");
    }
}
