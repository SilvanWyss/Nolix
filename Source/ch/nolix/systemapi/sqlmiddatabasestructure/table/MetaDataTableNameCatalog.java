/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.table;

/**
 * Of the {@link MetaDataTableNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class MetaDataTableNameCatalog {
  public static final String DATABASE_PROPERTY = "DatabaseProperty";

  /**
   * Prevents that an instance of the {@link MetaDataTableNameCatalog} can be
   * created.
   */
  private MetaDataTableNameCatalog() {
  }
}
