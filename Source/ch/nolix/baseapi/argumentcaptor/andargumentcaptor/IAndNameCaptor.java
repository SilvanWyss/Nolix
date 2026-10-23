/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndNameCaptor}
 */
public interface IAndNameCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IAndNameCaptor} capture the given name.
   * 
   * @param name
   * @return the successor of the current {@link IAndNameCaptor}
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the current {@link IAndNameCaptor} does not have
   *                          a successor
   */
  S andName(String name);

  /**
   * @return the name the current {@link IAndNameCaptor} has captured
   * @throws RuntimeException if the current {@link IAndNameCaptor} has not
   *                          captured a name
   */
  String getName();
}
