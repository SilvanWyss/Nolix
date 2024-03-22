//package declaration
package ch.nolix.coreapitest.programcontrolapitest.savecontrolapitest;

//own imports
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.ChangeRequestable;

//class
public final class ChangeRequestableMock implements ChangeRequestable {

  //attribute
  private final boolean hasChanges;

  //constructor
  private ChangeRequestableMock(final boolean hasChanges) {
    this.hasChanges = hasChanges;
  }

  //static method
  public static ChangeRequestableMock withHasChangesFlag(final boolean hasChanges) {
    return new ChangeRequestableMock(hasChanges);
  }

  //method
  @Override
  public boolean hasChanges() {
    return hasChanges;
  }
}
