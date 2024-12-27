package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import java.util.Optional;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalReference;

public interface IOptionalReferenceTool {

  boolean canClear(IOptionalReference<?> optionalReference);

  boolean canSetGivenEntity(IOptionalReference<?> optionalReference, IEntity entity);

  Optional<? extends IField> getOptionalStoredBackReferencingField(IOptionalReference<?> optionalReference);
}
