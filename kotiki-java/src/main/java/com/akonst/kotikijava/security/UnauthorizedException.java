package com.akonst.kotikijava.security;

public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
        super("Unauthorized");
    }
}
