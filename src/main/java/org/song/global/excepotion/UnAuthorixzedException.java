package org.song.global.excepotion;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class UnAuthorixzedException  extends CommonException {

    public UnAuthorixzedException(){
        super("UnAuthorixzedException", HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorixzedException(String message){
        super("message", HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorixzedException(Map<String, List<String>> errorMessage){
        super(errorMessage , HttpStatus.UNAUTHORIZED);
    }
}
