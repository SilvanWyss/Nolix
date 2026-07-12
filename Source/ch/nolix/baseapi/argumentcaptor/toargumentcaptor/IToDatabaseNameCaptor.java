/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.toargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IToDatabaseNameCaptor}
 */
public interface IToDatabaseNameCaptor<S> extends ArgumentCaptor<S> {
  String getDatabaseName();

  S toDatabase(final String databaseName);
}
