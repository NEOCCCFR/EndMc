package fr.neocfr.endmc.exceptions;

import java.io.Serial;

public class CreationError extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6580002384540837582L;

    public CreationError(String message) {
        super(message);
    }
}
