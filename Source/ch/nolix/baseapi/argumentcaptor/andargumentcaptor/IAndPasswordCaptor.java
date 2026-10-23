/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndPasswordCaptor}
 */
public interface IAndPasswordCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IAndPasswordCaptor} capture the given password.
   * 
   * @param password
   * @return the successor of the current {@link IAndPasswordCaptor}
   * @throws RuntimeException if the given password is null
   * @throws RuntimeException if the current {@link IAndPasswordCaptor} does not
   *                          have a successor
   */
  S andPassword(String password);

  /**
   * @return the password the current {@link IAndPasswordCaptor} has captured
   * @throws RuntimeException if the current {@link IAndPasswordCaptor} has not
   *                          captured a password
   */
  String getPassword();
}
