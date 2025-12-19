package ch.nolix.systemapi.sqlmidschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
public enum BackReferenceableColumnColumn implements INameHolder {
  PARENT_BASE_BACK_REFERENCE_COLUMN_ID(BackReferenceableColumnColumnNameCatalog.PARENT_BASE_BACK_REFERENCE_COLUMN_ID),
  BACK_REFERENCEABLE_COLUMN_ID(BackReferenceableColumnColumnNameCatalog.BACK_REFERENCEABLE_COLUMN_ID);

  private final String name;

  /**
   * Creates a new {@link BackReferenceableColumnColumn} with the given name.
   * 
   * @param name
   */
  BackReferenceableColumnColumn(final String name) {
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
