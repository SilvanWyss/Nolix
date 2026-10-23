/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForNodeDatabaseCaptor}
 */
public interface IForNodeDatabaseCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IForNodeDatabaseCaptor} capture the given
   * nodeDatabase.
   * 
   * @param nodeDatabase
   * @return the successor of the current {@link IForNodeDatabaseCaptor}
   * @throws RuntimeException if the given nodeDatabase is null
   * @throws RuntimeException if the current {@link IForNodeDatabaseCaptor} does
   *                          not have a successor
   */
  S forNodeDatabase(IMutableNode<?> nodeDatabase);

  /**
   * Lets the current {@link IForNodeDatabaseCaptor} capture a new temporary
   * in-memory node database.
   * 
   * @return the successor of the current {@link IForNodeDatabaseCaptor}
   * @throws RuntimeException if the current {@link IForNodeDatabaseCaptor} does
   *                          not have a successor
   */
  S forTemporaryInMemoryNodeDatabase();

  /**
   * @return the node database the current {@link IForNodeDatabaseCaptor} has
   *         captured
   * @throws RuntimeException if the current {@link IForNodeDatabaseCaptor} has
   *                          not captured a node database
   */
  IMutableNode<?> getStoredNodeDatabase();
}
