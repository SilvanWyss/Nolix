/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndDatabaseCaptor}
 */
public interface IAndDatabaseCaptor<S> extends ArgumentCaptor<S> {
  S andDatabase(String database);

  String getDatabase();
}
