//package declaration
package ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

//class
public final class NameHolderStub implements INameHolder {

  //attribute
  private final String name;

  //constructor
  private NameHolderStub(final String name) {
    this.name = name;
  }

  //static method
  public static NameHolderStub withName(final String name) {
    return new NameHolderStub(name);
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
