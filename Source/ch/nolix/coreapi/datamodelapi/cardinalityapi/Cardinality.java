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

    if (baseCardinality == null) {
      throw new IllegalArgumentException("The given base cardinality is null.");
    }

    this.baseCardinality = baseCardinality;
  }

  //method
  public final BaseCardinality getBaseCardinality() {
    return baseCardinality;
  }
}
