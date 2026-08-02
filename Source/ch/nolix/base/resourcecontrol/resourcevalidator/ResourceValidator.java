/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.resourcecontrol.resourcevalidator;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.resourcecontrol.resourcerequest.OpennessRequestable;

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
   * @throws RuntimeException if the given resource is null
   * @throws RuntimeException if the given resource is closed
   */
  public static void assertIsOpen(final OpennessRequestable resource) {
    if (resource == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.RESOURCE);
    }

    if (resource.isClosed()) {
      throw ClosedArgumentException.forArgument(resource);
    }
  }
}
