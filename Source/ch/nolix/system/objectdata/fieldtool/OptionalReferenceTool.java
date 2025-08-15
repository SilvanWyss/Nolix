package ch.nolix.system.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.fieldtool.IOptionalReferenceTool;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

public final class OptionalReferenceTool extends FieldTool implements IOptionalReferenceTool {

  @Override
  @SuppressWarnings("unchecked")
  public Optional<IBaseBackReference<IEntity>> getOptionalStoredBaseBackReference(
    final IOptionalReference<?> optionalReference) {
    return //
    (Optional<IBaseBackReference<IEntity>>) //
    optionalReference
      .getStoredReferencedEntity()
      .internalGetStoredFields()
      .getOptionalStoredFirst(p -> p.referencesBackField(optionalReference));
  }
}
