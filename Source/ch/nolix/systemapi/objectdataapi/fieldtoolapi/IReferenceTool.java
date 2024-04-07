//package declaration
package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//interface
public interface IReferenceTool extends IFieldTool {

  //method declaration
  IEntityUpdateDto forReferenceCreateEntityUpdateDtoForSetEntity(IReference<?> reference, IEntity entity);

  Optional<IBaseBackReference<IEntity>> getOptionalStoredBaseBackReferenceForReference(IReference<IEntity> reference);

  //method declaration
  boolean toReferenceCanSetEntity(final IReference<?> reference, IEntity entity);
}
