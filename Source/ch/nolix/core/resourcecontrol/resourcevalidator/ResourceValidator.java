package ch.nolix.core.resourcecontrol.resourcevalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;

/**
 * @author Silvan Wyss
 * @version 2024-12-19
 */
public class ResourceValidator implements IResourceValidator {

  //TODO: Create ResourceExaminer
  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsOpen(final CloseStateRequestable resource) {

    if (resource == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.RESOURCE);
    }

    if (resource.isClosed()) {
      throw ClosedArgumentException.forArgument(resource);
    }
  }
}
