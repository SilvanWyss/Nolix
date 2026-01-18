/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.errorcontrol.validation;

/**
 * @author Silvan Wyss
 */
public interface IValidationResponse {
  String getMessage();

  boolean isOk();
}
