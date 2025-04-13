package ch.nolix.core.resourcecontrol.resourcevalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.resourcecontrol.resourceexaminer.ResourceExaminer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrolapi.resourceexaminerapi.IResourceExaminer;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;

/**
 * @author Silvan Wyss
 * @version 2024-12-19
 */
public class ResourceValidatorUnit implements IResourceValidator {

  private static final IResourceExaminer RESOURCE_EXAMINER = new ResourceExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsOpen(final CloseStateRequestable resource) {
    if (!RESOURCE_EXAMINER.isOpen(resource)) {
      throw ClosedArgumentException.forArgument(resource);
    }
  }
}
