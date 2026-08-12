/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaseproperty;

/**
 * @author Silvan Wyss
 */
public enum Cardinality {
  TO_ONE(BaseCardinality.SINGLE),
  TO_ONE_OR_NONE(BaseCardinality.SINGLE),
  TO_MANY(BaseCardinality.MULTI);

  private final BaseCardinality baseCardinality;

  Cardinality(final BaseCardinality baseCardinality) {
    this.baseCardinality = baseCardinality;
  }

  public BaseCardinality getBaseCardinality() {
    return baseCardinality;
  }
}
