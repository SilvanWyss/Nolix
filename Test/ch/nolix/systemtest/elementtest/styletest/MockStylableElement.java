//package declaration
package ch.nolix.systemtest.elementtest.styletest;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.element.style.StylableElement;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
public final class MockStylableElement extends StylableElement<MockStylableElement> {

  //optional attribute
  private String role;

  //method
  public String getRole() {

    assertHasRole();

    return role;
  }

  //method
  @Override
  public IContainer<? extends IStylableElement<?>> getStoredChildStylableElements() {
    return ImmutableList.createEmpty();
  }

  //method
  @Override
  public boolean hasRole(String role) {
    return (hasRole() && getRole().equals(role));
  }

  //method
  public void removeRole() {
    role = null;
  }

  //method
  @Override
  protected void resetStylableElement() {
    removeRole();
  }

  //method
  @Override
  protected void resetStyle() {
    //Does nothing.
  }

  //method
  private void assertHasRole() {
    if (!hasRole()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.ROLE);
    }
  }

  //method
  private boolean hasRole() {
    return (role != null);
  }
}
