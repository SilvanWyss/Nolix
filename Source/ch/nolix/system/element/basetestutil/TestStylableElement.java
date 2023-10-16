//package declaration
package ch.nolix.system.element.basetestutil;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.element.base.StylableElement;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
public final class TestStylableElement extends StylableElement<TestStylableElement> {

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
    return new ImmutableList<>();
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
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.ROLE);
    }
  }

  //method
  private boolean hasRole() {
    return (role != null);
  }
}
