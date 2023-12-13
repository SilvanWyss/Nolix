//package declaration
package ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITitleHolder;

//class
public final class MockTitleHolder implements ITitleHolder {

  //attribute
  private final String title;

  //constructor
  private MockTitleHolder(final String title) {
    this.title = title;
  }

  //static method
  public static MockTitleHolder withTitle(final String title) {
    return new MockTitleHolder(title);
  }

  //method
  @Override
  public String getTitle() {
    return title;
  }
}
