//package declaration
package ch.nolix.core.commontypetool.arraytool;

//class
public final class NextIndexMediator {

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
  public int andGetNextIndex() {
    return nextIndex;
  }
}
