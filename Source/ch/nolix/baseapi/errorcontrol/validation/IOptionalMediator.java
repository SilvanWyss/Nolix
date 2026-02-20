/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.errorcontrol.validation;

/**
 * @author Silvan Wyss
 */
public interface IOptionalMediator {
  void containsEqualObject(Object object);

  void containsObject(Object object);

  void containsObjectOfType(Class<Object> type);

  void isEmpty();

  void isPresent();
}
