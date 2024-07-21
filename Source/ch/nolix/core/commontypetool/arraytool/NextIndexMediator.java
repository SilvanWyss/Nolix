//package declaration
package ch.nolix.core.commontypetool.arraytool;

//own imports
import ch.nolix.coreapi.commontypetoolapi.arraytoolapi.INextIndexMediator;

//class
public final class NextIndexMediator implements INextIndexMediator {

  //attribute
  private final int nextIndex;

  //constructor
  private NextIndexMediator(final int nextIndex) {
    this.nextIndex = nextIndex;
  }

  //static method
  public static NextIndexMediator forNextIndex(final int nextIndex) {
    return new NextIndexMediator(nextIndex);
  }

  //method
  @Override
  public int andGetNextIndex() {
    return nextIndex;
  }
}
