package org.michu.webstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Brak produktów we wskazanej kategorii")
public class NoProductFoundUnderCategoryException extends RuntimeException{
	
	private static final long serialVersionUID =3935230281455340039L;
}
