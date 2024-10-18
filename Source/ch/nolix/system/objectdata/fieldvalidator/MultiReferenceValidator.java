package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.MultiReferenceTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IMultiReferenceTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IMultiReferenceValidator;

public final class MultiReferenceValidator extends FieldValidator implements IMultiReferenceValidator {

  private static final IMultiReferenceTool MULTI_REFERENCE_TOOL = new MultiReferenceTool();

  @Override
  public void assertCanAddGivenEntity(final IMultiReference<?> multiReference, final IEntity entity) {
    if (!MULTI_REFERENCE_TOOL.canAddGivenEntity(multiReference, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot add the given entity");
    }
  }

  @Override
  public void assertCanClear(final IMultiReference<?> multiReference) {
    if (!MULTI_REFERENCE_TOOL.canClear(multiReference)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot clear");
    }
  }

  @Override
  public <E extends IEntity> void assertCanRemoveEntity(
    final IMultiReference<E> multiReference,
    final E entity) {
    if (!MULTI_REFERENCE_TOOL.canRemoveEntity(multiReference, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot remove the given " + entity);
    }
  }
}
