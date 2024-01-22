//package declaration
package ch.nolix.coreapitest.programatomapitest.variableapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class VariableApiTestPool extends TestPool {

  //constructor
  public VariableApiTestPool() {
    super(
      LowerCaseVariableCatalogueTest.class,
      PascalCaseVariableCatalogueTest.class,
      PluralLowerCaseVariableCatalogueTest.class,
      PluralPascalCaseVariableCatalogueTest.class);
  }
}
