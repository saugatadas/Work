package BookMyShow.exception;

public class UserDuplicateEmailException extends RuntimeException{
    public UserDuplicateEmailException(String message) {
        super(message);
    }
}
