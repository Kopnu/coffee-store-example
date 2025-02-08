/*
 * Korni.love. Do not reproduce without permission in writing.
 * Copyright (c) 2021 Sergei Kornilov.
 */

package love.korni.shopexample.dto;

import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * Error response
 *
 * @author Sergei_Konilov
 */
@Accessors(chain = true)
public record ErrorResponse(String error, String message, String path, Integer status, OffsetDateTime timestamp) {
}
