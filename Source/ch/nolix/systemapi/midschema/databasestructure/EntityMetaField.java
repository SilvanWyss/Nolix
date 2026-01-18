/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.databasestructure;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;

/**
 * @author Silvan Wyss
 */
public enum EntityMetaField implements INameHolder {
  ID(PascalCaseVariableCatalog.ID),
  SAVE_STAMP(PascalCaseVariableCatalog.SAVE_STAMP),
  VALID_FROM_DATE_TIME(PascalCaseVariableCatalog.VALID_FROM_DATE_TIME),
  VALID_TO_DATE_TIME(PascalCaseVariableCatalog.VALID_TO_DATE_TIME);

  private final String name;

  /**
   * Creates a new {@link EntityMetaField} with the given name.
   * 
   * @param name
   */
  EntityMetaField(final String name) {
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
