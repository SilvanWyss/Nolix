/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.errorcontrol.validation;

/**
 * @author Silvan Wyss
 */
public interface IValidationResponse {
  String getMessage();

  boolean isOk();
}
