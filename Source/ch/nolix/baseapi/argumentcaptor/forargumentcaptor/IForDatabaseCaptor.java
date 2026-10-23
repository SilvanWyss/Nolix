/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForDatabaseCaptor}
 */
public interface IForDatabaseCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IForDatabaseCaptor} capture the given database.
   * 
   * @param database
   * @return the successor of the current {@link IForDatabaseCaptor}
   * @throws RuntimeException if the given database is null or blank
   * @throws RuntimeException if the current {@link IForDatabaseCaptor} does not
   *                          have a successor
   */
  S forDatabase(String database);

  /**
   * @return the database the current {@link IForDatabaseCaptor} has captured
   * @throws RuntimeException if the current {@link IForDatabaseCaptor} has not
   *                          captured a database
   */
  String getDatabase();
}
