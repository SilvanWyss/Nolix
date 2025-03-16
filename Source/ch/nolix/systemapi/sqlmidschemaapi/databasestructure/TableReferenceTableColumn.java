package ch.nolix.systemapi.sqlmidschemaapi.databasestructure;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

/**
 * @author Silvan Wyss
 * @version 2025-01-19
 */
public enum TableReferenceTableColumn implements INameHolder {
  REFERENCE_COLUMN_ID("ReferenceColumnId"),
  REFERENCED_TABLE_ID("ReferencedTableId");

  private final String name;

  /**
   * Creates a new {@link TableReferenceTableColumn} with the given name.
   * 
   * @param name
   */
  TableReferenceTableColumn(final String name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return name;
  }
}
