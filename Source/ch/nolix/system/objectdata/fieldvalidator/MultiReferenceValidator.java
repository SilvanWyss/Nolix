package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.MultiReferenceExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IMultiReferenceExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IMultiReferenceValidator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

public final class MultiReferenceValidator extends FieldValidator implements IMultiReferenceValidator {
  private static final IMultiReferenceExaminer MULTI_REFERENCE_EXAMINER = new MultiReferenceExaminer();

  @Override
  public <E extends IEntity> void assertCanAddEntity(final IMultiReference<E> multiReference, final E entity) {
    if (!MULTI_REFERENCE_EXAMINER.canAddEntity(multiReference, entity)) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        multiReference,
        "cannot add the given entity '" + entity + "'");
    }
  }

  @Override
  public void assertCanBeCleared(final IMultiReference<?> multiReference) {
    if (!MULTI_REFERENCE_EXAMINER.canBeCleared(multiReference)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot be cleared");
    }
  }

  @Override
  public <E extends IEntity> void assertCanRemoveEntity(final IMultiReference<E> multiReference, final E entity) {
    if (!MULTI_REFERENCE_EXAMINER.canRemoveEntity(multiReference, entity)) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot remove the given '" + entity + "'");
    }
  }
}
