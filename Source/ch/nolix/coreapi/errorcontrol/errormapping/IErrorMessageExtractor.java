/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.errorcontrol.errormapping;

/**
 * @author Silvan Wyss
 */
public interface IErrorMessageExtractor {
  /**
   * @param error
   * @return the message of the given error. Handles the case when the given error
   *         is null or when the message of the given error is null or blank.
   */
  String getMessageOfError(Throwable error);
}
