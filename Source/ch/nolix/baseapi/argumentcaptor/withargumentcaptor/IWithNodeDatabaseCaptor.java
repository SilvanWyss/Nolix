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
  IMutableNode<?> getStoredNodeDatabase();

  S withNodeDatabase(IMutableNode<?> nodeDatabase);

  S withTemporaryInMemoryNodeDatabase();
}
