package ch.nolix.system.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.fieldtool.IOptionalReferenceTool;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

public final class OptionalReferenceTool extends FieldTool implements IOptionalReferenceTool {

  @Override
  public boolean canClear(final IOptionalReference<?> optionalReference) {
    return optionalReference != null
    && optionalReference.belongsToEntity()
    && optionalReference.getStoredParentEntity().isOpen();
  }

  @Override
  public boolean canSetGivenEntity(final IOptionalReference<?> optionalReference, final IEntity entity) {
    return canSetEntity(optionalReference)
    && entity != null
    && entity.isOpen()
    && optionalReference.getReferencedTableName().equals(entity.getParentTableName());
  }

  @Override
  public Optional<? extends IField> getOptionalStoredBackReferencingField(
    final IOptionalReference<?> optionalReference) {
    return optionalReference
      .getStoredReferencedEntity()
      .internalGetStoredFields()
      .getOptionalStoredFirst(p -> p.referencesBackField(optionalReference));
  }

  private boolean canSetEntity(final IOptionalReference<?> optionalReference) {
    return optionalReference != null
    && optionalReference.belongsToEntity()
    && optionalReference.getStoredParentEntity().isOpen();
  }
}
