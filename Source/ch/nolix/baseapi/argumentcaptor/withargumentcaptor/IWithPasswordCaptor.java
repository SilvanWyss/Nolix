/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IWithPasswordCaptor}.
 */
public interface IWithPasswordCaptor<S> extends ArgumentCaptor<S> {
  /**
   * @return the password the current {@link IWithPasswordCaptor} has captured
   * @throws RuntimeException if the current {@link IWithPasswordCaptor} has not
   *                          captured a password
   */
  String getPassword();

  /**
   * Lets the current {@link IWithPasswordCaptor} capture the given password.
   * 
   * @param password
   * @return the successor of the current {@link IWithPasswordCaptor}
   * @throws RuntimeException if the given password is null
   * @throws RuntimeException if the current {@link IWithPasswordCaptor} does not
   *                          have a successor
   */
  S withPassword(String password);
}
