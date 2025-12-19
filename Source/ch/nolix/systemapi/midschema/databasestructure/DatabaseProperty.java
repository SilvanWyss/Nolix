package ch.nolix.systemapi.midschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
public enum DatabaseProperty implements INameHolder {
  SCHEMA_TIMESTAMP(DatabasePropertyNameCatalog.SCHEMA_TIMESTAMP);

  private final String name;

  /**
   * Creates a new {@link DatabaseProperty} with the given name.
   * 
   * @param name
   */
  DatabaseProperty(final String name) {
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
