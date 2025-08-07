package org.song.member.exceiption;

import org.song.global.excepotion.NotFondException;

public class MemberNotFoundExceiption extends NotFondException {
    public MemberNotFoundExceiption(String message) {
        super("NotFound,member");
        setErrorCode(true);
    }
}
