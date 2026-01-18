package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.OptionalReferenceExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IOptionalReferenceExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IOptionalReferenceValidator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

/**
 * @author Silvan Wyss
 */
public final class OptionalReferenceValidator extends FieldValidator implements IOptionalReferenceValidator {
  private static final IOptionalReferenceExaminer OPTIONAL_REFERENCE_EXAMINER = new OptionalReferenceExaminer();

  @Override
  public void assertCanBeCleared(final IOptionalReference<?> optionalReference) {
    if (!OPTIONAL_REFERENCE_EXAMINER.canBeCleared(optionalReference)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalReference, "cannot be cleared");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> void assertCanSetEntity(final IOptionalReference<E> optionalReference, final E entity) {
    if (!OPTIONAL_REFERENCE_EXAMINER.canSetEntity(optionalReference, entity)) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        optionalReference,
        "cannot set the given entity '" + entity + "'");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsNotEmpty(final IOptionalReference<?> optionalReference) {
    if (optionalReference.isEmpty()) {
      throw EmptyArgumentException.forArgument(optionalReference);
    }
  }
}
