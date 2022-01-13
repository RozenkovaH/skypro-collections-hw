package pro.sky.skyprocollectionshw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NoFreeSlotsInArrayException extends RuntimeException {

    public NoFreeSlotsInArrayException() {
        super("No free slots in the array.");
    }

    public NoFreeSlotsInArrayException(String message) {
        super(message);
    }

    public NoFreeSlotsInArrayException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFreeSlotsInArrayException(Throwable cause) {
        super(cause);
    }
}
