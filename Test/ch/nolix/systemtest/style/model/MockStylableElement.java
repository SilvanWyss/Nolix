package ch.nolix.systemtest.style.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.style.stylable.AbstractStylableElement;
import ch.nolix.systemapi.style.stylable.IStylableElement;

public final class MockStylableElement extends AbstractStylableElement<MockStylableElement> {
  private String role;

  public String getRole() {
    assertHasRole();

    return role;
  }

  @Override
  public IContainer<? extends IStylableElement<?>> getStoredChildStylableElements() {
    return ImmutableList.createEmpty();
  }

  @Override
  public boolean hasRole(String role) {
    return (hasRole() && getRole().equals(role));
  }

  public void removeRole() {
    role = null;
  }

  @Override
  protected void resetStylableElement() {
    removeRole();
  }

  @Override
  protected void resetStyle() {
    //Does nothing.
  }

  private void assertHasRole() {
    if (!hasRole()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.ROLE);
    }
  }

  private boolean hasRole() {
    return (role != null);
  }
}
