package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 * @version 2025-09-05
 */
public enum ReferenceableTableColumn implements INameHolder {
  PARENT_BASE_REFERENCE_COLUMN_ID(ReferenceableTableNameCatalog.PARENT_BASE_REFERENCE_COLUMN_ID),
  REFERENCEABLE_TABLE_ID(ReferenceableTableNameCatalog.REFERENCEABLE_TABLE_ID);

  private final String name;

  /**
   * Creates a new {@link ReferenceableTableColumn} with the given name.
   * 
   * @param name
   */
  ReferenceableTableColumn(final String name) {
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
