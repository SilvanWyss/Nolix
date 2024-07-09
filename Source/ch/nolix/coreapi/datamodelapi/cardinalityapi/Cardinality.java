//package declaration
package ch.nolix.coreapi.datamodelapi.cardinalityapi;

//enum
public enum Cardinality {
  TO_ONE(BaseCardinality.SINGLE),
  TO_ONE_OR_NONE(BaseCardinality.SINGLE),
  TO_MANY(BaseCardinality.MULTI);

  //attribute
  private final BaseCardinality baseCardinality;

  //constructor
  Cardinality(final BaseCardinality baseCardinality) {
    this.baseCardinality = baseCardinality;
  }

  //method
  public BaseCardinality getBaseCardinality() {
    return baseCardinality;
  }
}
