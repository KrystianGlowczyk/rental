package com.krglow.rental.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super("Nie znaleziono obiektu lub osoby w bazie danych.");
    }
}
