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
  S andNodeDatabase(IMutableNode<?> nodeDatabase);

  S andTemporaryInMemoryNodeDatabase();

  IMutableNode<?> getStoredNodeDatabase();
}
