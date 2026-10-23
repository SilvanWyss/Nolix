/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForPasswordCaptor}
 */
public interface IForPasswordCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IForPasswordCaptor} capture the given password.
   * 
   * @param password
   * @return the successor of the current {@link IForPasswordCaptor}
   * @throws RuntimeException if the given password is null or blank
   * @throws RuntimeException if the current {@link IForPasswordCaptor} does not
   *                          have a successor
   */
  S forPassword(String password);

  /**
   * @return the password the current {@link IForPasswordCaptor} has captured
   * @throws RuntimeException if the current {@link IForPasswordCaptor} has not
   *                          captured a password
   */
  String getPassword();
}
