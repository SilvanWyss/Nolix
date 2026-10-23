/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IWithLoginNameCaptor}.
 */
public interface IWithLoginNameCaptor<S> extends ArgumentCaptor<S> {
  /**
   * @return the login name the current {@link IWithLoginNameCaptor} has captured
   * @throws RuntimeException if the current {@link IWithLoginNameCaptor} has not
   *                          captured a login name
   */
  String getLoginName();

  /**
   * Lets the current {@link IWithLoginNameCaptor} capture the given loginName.
   * 
   * @param loginName
   * @return the successor of the current {@link IWithLoginNameCaptor}
   * @throws RuntimeException if the given loginName is null or blank
   * @throws RuntimeException if the current {@link IWithLoginNameCaptor} does not
   *                          have a successor
   */
  S withLoginName(String loginName);
}
