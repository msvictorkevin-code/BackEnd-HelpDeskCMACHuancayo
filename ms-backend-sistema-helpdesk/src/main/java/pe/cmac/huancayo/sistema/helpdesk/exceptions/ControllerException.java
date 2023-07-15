package pe.cmac.huancayo.sistema.helpdesk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.utils.Messages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ControllerException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        RestResponse<Object> response = new RestResponse<>();
        Map<String, Object> errors = new HashMap<>();

        List<String> exceptionalErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        errors.put("Errors", exceptionalErrors);
        response.setStatus(false);
        response.setMessage(Messages.MSG_ERROR_REQUEST);
        response.setData(errors);
        return response;
    }
}
