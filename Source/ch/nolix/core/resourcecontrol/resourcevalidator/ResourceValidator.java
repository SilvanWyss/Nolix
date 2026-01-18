/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.resourcecontrol.resourcevalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.resourcecontrol.closecontroller.CloseStateRequestable;

/**
 * The {@link ResourceValidator} provides methods to validate resources.
 * 
 * Of the {@link ResourceValidator} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class ResourceValidator {
  /**
   * Prevents that an instance of the {@link ResourceValidator} can be created.
   */
  private ResourceValidator() {
  }

  /**
   * @param resource
   * @throws ArgumentIsNullException if the given resource is null.
   * @throws ClosedArgumentException if the given resource is closed.
   */
  public static void assertIsOpen(final CloseStateRequestable resource) {
    if (resource == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.RESOURCE);
    }

    if (resource.isClosed()) {
      throw ClosedArgumentException.forArgument(resource);
    }
  }
}
