package org.song.global.excepotion;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class NotFondException
 extends CommonException {

    public NotFondException(){
        super("NotFound", HttpStatus.NOT_FOUND);
    }

    public NotFondException(String mesage){
        super(mesage, HttpStatus.NOT_FOUND);
    }

     public NotFondException(Map<String , List<String >> errorsMessage){
        super(errorsMessage, HttpStatus.NOT_FOUND);
     }
}
