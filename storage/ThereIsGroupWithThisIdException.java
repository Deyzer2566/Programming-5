package storage;

public class ThereIsGroupWithThisIdException extends RuntimeException{
    ThereIsGroupWithThisIdException(String message){
        super(message);
    }
}
