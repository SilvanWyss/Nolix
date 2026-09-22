/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.toargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IToDatabaseCaptor}
 */
public interface IToDatabaseCaptor<S> extends ArgumentCaptor<S> {
  String getDatabaseName();

  S toDatabase(String databaseName);
}
