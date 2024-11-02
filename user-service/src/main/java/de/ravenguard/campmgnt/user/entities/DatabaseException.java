package de.ravenguard.campmgnt.user.entities;

public class DatabaseException extends RuntimeException {
    public DatabaseException(Throwable t) {
        super(t);
    }
}
