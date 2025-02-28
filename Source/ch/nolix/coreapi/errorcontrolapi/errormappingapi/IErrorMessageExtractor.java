package ch.nolix.coreapi.errorcontrolapi.errormappingapi;

/**
 * @author Silvan Wyss
 * @version 2025-02-28
 */
public interface IErrorMessageExtractor {

  /**
   * @param error
   * @return the message of the given error. Handles the case when the given error
   *         is null or when the message of the given error is null or blank.
   */
  String getMessageOfError(Throwable error);
}
