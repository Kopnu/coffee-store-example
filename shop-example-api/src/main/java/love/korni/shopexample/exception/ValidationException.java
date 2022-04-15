/*
 * Korni.love. Do not reproduce without permission in writing.
 * Copyright (c) 2021 Sergei Kornilov.
 */

package love.korni.shopexample.exception;

import lombok.Getter;

/**
 * ValidationException
 *
 * @author Sergei_Konilov
 */
@Getter
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

}
