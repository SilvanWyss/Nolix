//package declaration
package ch.nolix.coreapitest.attributeapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest.MandatoryAttributeApiTestPool;

//class
public final class AttributeApiTestPool extends TestPool {

  //constructor
  public AttributeApiTestPool() {
    super(new MandatoryAttributeApiTestPool());
  }
}
