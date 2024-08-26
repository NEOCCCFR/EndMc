package fr.neocfr.endmc.exceptions;

import java.io.Serial;

public class NotFoundError extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4132669004486911997L;

    public NotFoundError(String message) {
        super(message);
    }
}
