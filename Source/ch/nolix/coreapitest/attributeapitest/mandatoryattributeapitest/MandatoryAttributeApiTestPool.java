//package declaration
package ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class MandatoryAttributeApiTestPool extends TestPool {

  //constructor
  public MandatoryAttributeApiTestPool() {
    super(NameHolderTest.class, TitleHolderTest.class);
  }
}
