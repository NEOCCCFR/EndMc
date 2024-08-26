package fr.neocfr.endmc.exceptions;

import java.io.Serial;

public class ExecutionError extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8997033296075099860L;

    public ExecutionError(String message, Throwable cause) {
        super(message, cause);
    }
}
