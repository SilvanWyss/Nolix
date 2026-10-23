/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForLoginNameCaptor}
 */
public interface IForLoginNameCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IForLoginNameCaptor} capture the given loginName.
   * 
   * @param loginName
   * @return the successor of the current {@link IForLoginNameCaptor}
   * @throws RuntimeException if the given loginName is null or blank
   * @throws RuntimeException if the current {@link IForLoginNameCaptor} does not
   *                          have a successor
   */
  S forLoginName(String loginName);

  /**
   * @return the login name the current {@link IForLoginNameCaptor} has captured
   * @throws RuntimeException if the current {@link IForLoginNameCaptor} has not
   *                          captured a login name
   */
  String getLoginName();
}
