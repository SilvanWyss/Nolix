//package declaration
package ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

//class
public final class MockNameHolder implements INameHolder {

  //attribute
  private final String name;

  //constructor
  private MockNameHolder(final String name) {
    this.name = name;
  }

  //static method
  public static MockNameHolder withName(final String name) {
    return new MockNameHolder(name);
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
