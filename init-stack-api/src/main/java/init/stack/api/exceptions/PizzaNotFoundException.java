package init.stack.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PizzaNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public PizzaNotFoundException (String message) {
        super(message);
    }
}
