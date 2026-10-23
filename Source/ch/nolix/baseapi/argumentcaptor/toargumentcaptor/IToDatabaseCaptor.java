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
  /**
   * @return the database the current {@link IToDatabaseCaptor} has captured
   * @throws RuntimeException if the current {@link IToDatabaseCaptor} has not
   *                          captured a database
   */
  String getDatabase();

  /**
   * Lets the current {@link IToDatabaseCaptor} capture the given database.
   * 
   * @param database
   * @return the successor of the current {@link IToDatabaseCaptor}
   * @throws RuntimeException if the given database is null or blank
   * @throws RuntimeException if the current {@link IToDatabaseCaptor} does not
   *                          have a successor
   */
  S toDatabase(String database);
}
