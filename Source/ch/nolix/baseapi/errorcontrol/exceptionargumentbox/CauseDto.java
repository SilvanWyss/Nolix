/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.errorcontrol.exceptionargumentbox;

/**
 * @author Silvan Wyss
 * @param cause
 */
public record CauseDto(Throwable cause) {
}
