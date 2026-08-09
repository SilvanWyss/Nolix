/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.style.model;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.system.style.stylable.AbstractStylableElement;
import ch.nolix.systemapi.style.stylable.StylableElement;

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
  public ExtendedIterable<? extends StylableElement<?>> getStoredChildStylableElements() {
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
    // Does nothing.
  }

  private void assertHasRole() {
    if (!hasRole()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.ROLE);
    }
  }

  private boolean hasRole() {
    return (memberRole != null);
  }
}
