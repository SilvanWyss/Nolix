/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IWithNodeDatabaseCaptor}
 */
public interface IWithNodeDatabaseCaptor<S> extends ArgumentCaptor<S> {
  /**
   * @return the node database the current {@link IWithNodeDatabaseCaptor} has
   *         captured
   * @throws RuntimeException if the current {@link IWithNodeDatabaseCaptor} has
   *                          not captured a node database
   */
  IMutableNode<?> getStoredNodeDatabase();

  /**
   * Lets the current {@link IWithNodeDatabaseCaptor} capture the given
   * nodeDatabase.
   * 
   * @param nodeDatabase
   * @return the successor of the current {@link IWithNodeDatabaseCaptor}
   * @throws RuntimeException if the given nodeDatabase is null
   * @throws RuntimeException if the current {@link IWithNodeDatabaseCaptor} does
   *                          not have a successor
   */
  S withNodeDatabase(IMutableNode<?> nodeDatabase);

  /**
   * Lets the current {@link IWithNodeDatabaseCaptor} capture a new temporary
   * in-memory node database.
   * 
   * @return the successor of the current {@link IWithNodeDatabaseCaptor}
   * @throws RuntimeException if the current {@link IWithNodeDatabaseCaptor} does
   *                          not have a successor
   */
  S withTemporaryInMemoryNodeDatabase();
}
