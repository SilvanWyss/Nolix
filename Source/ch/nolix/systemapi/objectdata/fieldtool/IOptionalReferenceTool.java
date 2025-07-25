package ch.nolix.systemapi.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

public interface IOptionalReferenceTool {

  boolean canClear(IOptionalReference<?> optionalReference);

  boolean canSetEntity(IOptionalReference<?> optionalReference);

  boolean canSetEntity(IOptionalReference<?> optionalReference, IEntity entity);

  Optional<? extends IField> getOptionalStoredBackReferencingField(IOptionalReference<?> optionalReference);
}
