package dev.dias375.os.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.dias375.os.domain.exception.DomainException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;

		var issue = new Issue();
		issue.setStatus(status.value());
		issue.setTitulo(ex.getMessage());
		issue.setDataHora(LocalDateTime.now());

		return super.handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var campos = new ArrayList<Issue.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String problema = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Issue.Campo(problema, mensagem));

		}

		var issue = new Issue();
		issue.setStatus(status.value());
		issue.setTitulo("Um ou mais campos são inválidos. Verifique o preenchimento.");
		issue.setDataHora(LocalDateTime.now());
		issue.setCampos(campos);

		return super.handleExceptionInternal(ex, issue, headers, status, request);
	}
}
