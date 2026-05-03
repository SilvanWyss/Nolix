/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddatabasestructure.table;

/**
 * Of the {@link DataTableNameCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class DataTableNameCatalog {
  public static final String ENTITY_INDEX = "EntityIndex";

  public static final String MULTI_VALUE_ENTRY = "MultiValueEntry";

  public static final String MULTI_REFERENCE_ENTRY = "MultiReferenceEntry";

  public static final String MULTI_BACK_REFERENCE_ENTRY = "MultiBackReferenceEntry";

  /**
   * Prevents that an instance of the {@link DataTableNameCatalog} can be created.
   */
  private DataTableNameCatalog() {
  }
}
