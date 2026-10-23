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
  /**
   * Lets the current {@link IAndDatabaseCaptor} capture the given database.
   * 
   * @param database
   * @return the successor of the current {@link IAndDatabaseCaptor}
   * @throws RuntimeException if the given database is null or blank
   * @throws RuntimeException if the current {@link IAndDatabaseCaptor} does not
   *                          have a successor
   */
  S andDatabase(String database);

  /**
   * @return the database the current {@link IAndDatabaseCaptor} has captured
   * @throws RuntimeException if the current {@link IAndDatabaseCaptor} has not
   *                          captured a database
   */
  String getDatabase();
}
