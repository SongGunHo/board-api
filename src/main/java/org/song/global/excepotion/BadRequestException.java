package org.song.global.excepotion;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class BadRequestException extends CommonException {

    public BadRequestException(){
        super("BadRequest", HttpStatus.BAD_REQUEST);
        setErrorCode(true);
    }

    public BadRequestException(String meassge){
        super(meassge, HttpStatus.BAD_REQUEST);
    }
    public BadRequestException(Map<String , List<String>> errorsMessage){
        super(errorsMessage, HttpStatus.BAD_REQUEST);
    }
}
