/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.style.model;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.style.stylable.AbstractStylableElement;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * @author Silvan Wyss
 */
public final class MockStylableElement extends AbstractStylableElement<MockStylableElement> {
  private String memberRole;

  public String getRole() {
    assertHasRole();

    return memberRole;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IStylableElement<?>> getStoredChildStylableElements() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasRole(String role) {
    return (hasRole() && getRole().equals(role));
  }

  public void removeRole() {
    memberRole = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetStylableElement() {
    removeRole();
  }

  /**
   * {@inheritDoc}
   */
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
    return (memberRole != null);
  }
}
