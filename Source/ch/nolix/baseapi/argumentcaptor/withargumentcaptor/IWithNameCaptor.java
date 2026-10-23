/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IWithNameCaptor}.
 */
public interface IWithNameCaptor<S> extends ArgumentCaptor<S> {
  /**
   * @return the name the current {@link IWithNameCaptor} has captured
   * @throws RuntimeException if the current {@link IWithNameCaptor} has not
   *                          captured a name
   */
  String getName();

  /**
   * Lets the current {@link IWithNameCaptor} capture the given name.
   * 
   * @param name
   * @return the successor of the current {@link IWithNameCaptor}
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the current {@link IWithNameCaptor} does not have
   *                          a successor
   */
  S withName(String name);
}
