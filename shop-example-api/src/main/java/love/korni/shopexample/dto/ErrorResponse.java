/*
 * Korni.love. Do not reproduce without permission in writing.
 * Copyright (c) 2021 Sergei Kornilov.
 */

package love.korni.shopexample.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * Error response
 *
 * @author Sergei_Konilov
 */
@Getter
@RequiredArgsConstructor
@Accessors(chain = true)
public class ErrorResponse {
    private final String error;
    private final String message;
    private final String path;
    private final Integer status;
    private final OffsetDateTime timestamp;
}
