package ch.nolix.systemapi.midschemaapi.databaseproperty;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public enum DatabaseProperty implements INameHolder {
  SCHEMA_TIMESTAMP("SchemaTimestamp");

  private final String name;

  DatabaseProperty(final String label) {
    this.name = label;
  }

  @Override
  public final String getName() {
    return name;
  }
}
