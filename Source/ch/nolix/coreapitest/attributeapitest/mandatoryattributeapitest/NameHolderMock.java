//package declaration
package ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

//class
public final class NameHolderMock implements INameHolder {

  //attribute
  private final String name;

  //constructor
  private NameHolderMock(final String name) {
    this.name = name;
  }

  //static method
  public static NameHolderMock withName(final String name) {
    return new NameHolderMock(name);
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
