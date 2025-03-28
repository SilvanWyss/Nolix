package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

/**
 * @author Silvan Wyss
 * @version 2025-03-28
 */
public enum ContentModelColumn implements INameHolder {
  CONTENT_TYPE(ContentModelColumnNameCatalog.CONTENT_TYPE),
  DATA_TYPE(ContentModelColumnNameCatalog.DATA_TYPE),
  REFERENCED_TABLE_ID(ContentModelColumnNameCatalog.REFERENCED_TABLE_ID),
  BACK_REFERENCED_COLUM_ID(ContentModelColumnNameCatalog.BACK_REFERENCED_COLUM_ID);

  private final String name;

  /**
   * Creates a new {@link ContentModelColumn} with the given name.
   * 
   * @param name
   */
  ContentModelColumn(final String name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }
}
