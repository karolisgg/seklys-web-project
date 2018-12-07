package lt.baltictalents.followeveryone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

   @ExceptionHandler
   public ResponseEntity<ErrorResponse> handleException(DeviceNotFound exc) {
      ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            exc.getMessage(),
            System.currentTimeMillis());
      return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler
   public ResponseEntity<ErrorResponse> handleException(NameAlreadyExists exc) {
      ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            exc.getMessage(),
            System.currentTimeMillis());
      return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
   }
}
