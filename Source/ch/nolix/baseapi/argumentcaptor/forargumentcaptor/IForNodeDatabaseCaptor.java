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
  S forNodeDatabase(final IMutableNode<?> nodeDatabase);

  S forTemporaryInMemoryNodeDatabase();

  IMutableNode<?> getStoredNodeDatabase();
}
