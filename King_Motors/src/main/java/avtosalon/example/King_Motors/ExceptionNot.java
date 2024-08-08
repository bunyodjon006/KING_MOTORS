package avtosalon.example.King_Motors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ExceptionNot extends  RuntimeException {
    private static final long SeriaUID = 1L;
    public  ExceptionNot(String message){
        super(message);

    }
}
