//package declaration
package ch.nolix.systemapi.objectdataapi.propertytoolapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//interface
public interface IReferenceTool extends IPropertyTool {

  //method declaration
  IEntityUpdateDto forReferenceCreateEntityUpdateDtoForSetEntity(IReference<?> reference, IEntity entity);

  //method declaration
  boolean toReferenceCanSetEntity(final IReference<?> reference, IEntity entity);
}
