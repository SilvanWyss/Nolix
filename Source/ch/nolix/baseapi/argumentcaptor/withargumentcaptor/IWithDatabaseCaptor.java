/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <D> the type of the database of a {@link IWithDatabaseCaptor}.
 * @param <S> the type of the successor of a {@link IWithDatabaseCaptor}.
 */
public interface IWithDatabaseCaptor<D, S> extends ArgumentCaptor<S> {
  D getStoredDatabase();

  S withDatabase(final D database);
}
