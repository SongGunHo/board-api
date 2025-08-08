package org.song.file.exception;

import org.song.global.excepotion.NotFondException;

public class FileNotFoundException extends NotFondException {

    public FileNotFoundException() {
        super("NotFound.file");
        setErrorCode(true);
    }
}
