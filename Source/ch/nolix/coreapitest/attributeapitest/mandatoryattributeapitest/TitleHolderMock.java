//package declaration
package ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITitleHolder;

//class
public final class TitleHolderMock implements ITitleHolder {

  //attribute
  private final String title;

  //constructor
  private TitleHolderMock(final String title) {
    this.title = title;
  }

  //static method
  public static TitleHolderMock withTitle(final String title) {
    return new TitleHolderMock(title);
  }

  //method
  @Override
  public String getTitle() {
    return title;
  }
}
