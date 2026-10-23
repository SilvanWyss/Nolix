/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndLoginNameCaptor}
 */
public interface IAndLoginNameCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IAndLoginNameCaptor} capture the given loginName.
   * 
   * @param loginName
   * @return the successor of the current {@link IAndLoginNameCaptor}
   * @throws RuntimeException if the given loginName is null or blank
   * @throws RuntimeException if the current {@link IAndLoginNameCaptor} does not
   *                          have a successor
   */
  S andLoginName(String loginName);

  /**
   * @return the login name the current {@link IAndLoginNameCaptor} has captured
   * @throws RuntimeException if the current {@link IAndLoginNameCaptor} has not
   *                          captured a login name
   */
  String getLoginName();
}
