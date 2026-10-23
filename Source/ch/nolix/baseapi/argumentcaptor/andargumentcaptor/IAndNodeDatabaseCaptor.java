/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndNodeDatabaseCaptor}
 */
public interface IAndNodeDatabaseCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IAndNodeDatabaseCaptor} capture the given
   * nodeDatabase.
   * 
   * @param nodeDatabase
   * @return the successor of the current {@link IAndNodeDatabaseCaptor}
   * @throws RuntimeException if the given nodeDatabase is null
   * @throws RuntimeException if the current {@link IAndNodeDatabaseCaptor} does
   *                          not have a successor
   */
  S andNodeDatabase(IMutableNode<?> nodeDatabase);

  /**
   * Lets the current {@link IAndNodeDatabaseCaptor} capture a new temporary
   * in-memory node database.
   * 
   * @return the successor of the current {@link IAndNodeDatabaseCaptor}
   * @throws RuntimeException if the current {@link IAndNodeDatabaseCaptor} does
   *                          not have a successor
   */
  S andTemporaryInMemoryNodeDatabase();

  /**
   * @return the node database the current {@link IAndNodeDatabaseCaptor} has
   *         captured
   * @throws RuntimeException if the current {@link IAndNodeDatabaseCaptor} has
   *                          not captured a node database
   */
  IMutableNode<?> getStoredNodeDatabase();
}
