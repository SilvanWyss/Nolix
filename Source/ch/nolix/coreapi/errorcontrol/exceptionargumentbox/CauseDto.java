package ch.nolix.coreapi.errorcontrol.exceptionargumentbox;

/**
 * @author Silvan Wyss
 * @param cause
 */
public record CauseDto(Throwable cause) {
}
