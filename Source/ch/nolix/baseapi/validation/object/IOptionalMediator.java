/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.validation.object;

/**
 * @author Silvan Wyss
 */
public interface IOptionalMediator {
  /**
   * @param object
   * @throws RuntimeException if the argument of the current
   *                          {@link IOptionalMediator} does not contain an object
   *                          that equals the given object
   */
  void containsEqualObject(Object object);

  /**
   * @param object
   * @throws RuntimeException if the argument of the current
   *                          {@link IOptionalMediator} does not contain the given
   *                          object
   */
  void containsObject(Object object);

  /**
   * @param type
   * @throws RuntimeException if the argument of the current
   *                          {@link IOptionalMediator} does not contain an object
   *                          of the given type
   */
  void containsObjectOfType(Class<Object> type);

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link IOptionalMediator} is not empty
   */
  void isEmpty();

  /**
   * @throws RuntimeException if the argument of the current
   *                          {@link IOptionalMediator} is not present
   */
  void isPresent();
}
