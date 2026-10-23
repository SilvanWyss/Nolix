/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.validation.response;

/**
 * @author Silvan Wyss
 */
public interface IValidationResponse {
  /**
   * @return the message of the current {@link IValidationResponse}
   */
  String getMessage();

  /**
   * @return true if the current {@link IValidationResponse} is ok, false
   *         otherwise
   */
  boolean isOk();
}
