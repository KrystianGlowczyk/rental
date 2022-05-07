package com.krglow.rental.exception;

public class DateConflictException extends RuntimeException{

    public DateConflictException(){
        super("Obiekt jest niedostepny w wybranej dacie.");
    }
}
