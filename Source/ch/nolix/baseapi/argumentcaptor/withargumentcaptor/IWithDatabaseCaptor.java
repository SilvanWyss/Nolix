/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IWithDatabaseCaptor}
 */
public interface IWithDatabaseCaptor<S> extends ArgumentCaptor<S> {
  /**
   * @return the database the current {@link IWithDatabaseCaptor} has captured
   * @throws RuntimeException if the current {@link IWithDatabaseCaptor} has not
   *                          captured a database
   */
  String getDatabase();

  /**
   * Lets the current {@link IWithDatabaseCaptor} capture the given database.
   * 
   * @param database
   * @return the successor of the current {@link IWithDatabaseCaptor}
   * @throws RuntimeException if the given database is null or blank
   * @throws RuntimeException if the current {@link IWithDatabaseCaptor} does not
   *                          have a successor
   */
  S withDatabase(String database);
}
