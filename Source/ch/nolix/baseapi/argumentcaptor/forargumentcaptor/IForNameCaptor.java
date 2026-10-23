/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForNameCaptor}
 */
public interface IForNameCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IForNameCaptor} capture the given name.
   * 
   * @param name
   * @return the successor of the current {@link IForNameCaptor}
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the current {@link IForNameCaptor} does not have
   *                          a successor
   */
  S forName(String name);

  /**
   * @return the name the current {@link IForNameCaptor} has captured
   * @throws RuntimeException if the current {@link IForNameCaptor} has not
   *                          captured a name
   */
  String getName();
}
