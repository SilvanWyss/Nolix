/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.column;

/**
 * Of the {@link EntityIndexColumnNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class EntityIndexColumnNameCatalog {
  public static final String ENTITY_ID = "EntityId";

  public static final String TABLE_ID = "TableId";

  /**
   * Prevents that an instance of the {@link EntityIndexColumnNameCatalog} can be
   * created.
   */
  private EntityIndexColumnNameCatalog() {
  }
}
