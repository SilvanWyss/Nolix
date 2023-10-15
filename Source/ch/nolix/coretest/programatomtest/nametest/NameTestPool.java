//package declaration
package ch.nolix.coretest.programatomtest.nametest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class NameTestPool extends TestPool {

  // constructor
  public NameTestPool() {
    super(
        LowerCaseCatalogueTest.class,
        PascalCaseCatalogueTest.class,
        PluralLowerCaseCatalogueTest.class,
        PluralPascalCaseCatalogueTest.class);
  }
}
