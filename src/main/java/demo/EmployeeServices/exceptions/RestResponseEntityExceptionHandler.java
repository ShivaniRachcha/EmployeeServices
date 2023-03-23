
package demo.EmployeeServices.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ErrorResponseEntity> handleSQLIntegrityConstraintViolationException(
			SQLIntegrityConstraintViolationException sqlICE) {
		String errorMessage = sqlICE.getMessage();
		String sqlState = sqlICE.getSQLState();
		ErrorResponseEntity responseEntity = new ErrorResponseEntity(errorMessage, sqlState);
		return new ResponseEntity<ErrorResponseEntity>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponseEntity> handleDataIntegrityViolationException(
			DataIntegrityViolationException dataIVE) {

		String errorMessage = dataIVE.getMessage();
		Throwable rootCause = dataIVE.getRootCause();
		String rootCauseMessage = rootCause != null ? rootCause.getMessage() : errorMessage;
		ErrorResponseEntity responseEntity = new ErrorResponseEntity(rootCauseMessage);
		return new ResponseEntity<ErrorResponseEntity>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataValidationFailedException.class)
	public ResponseEntity<ErrorResponseEntity> handleDataValidationFailedException(DataValidationFailedException de) {
		ErrorResponseEntity responseEntity = new ErrorResponseEntity(de.getMessage());
		return new ResponseEntity<ErrorResponseEntity>(responseEntity, HttpStatus.PRECONDITION_FAILED);
	}

}
