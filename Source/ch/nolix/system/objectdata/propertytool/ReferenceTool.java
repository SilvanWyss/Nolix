//package declaration
package ch.nolix.system.objectdata.propertytool;

//own imports
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IReferenceTool;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//class
public final class ReferenceTool extends PropertyTool implements IReferenceTool {

  //method
  @Override
  public IEntityUpdateDto forReferenceCreateEntityUpdateDtoForSetEntity(
    final IReference<?> reference,
    final IEntity entity) {

    final var parentEntity = reference.getStoredParentEntity();

    return //
    new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      new ContentFieldDto(reference.getName(), entity.getId()));
  }

  //method
  @Override
  public boolean toReferenceCanSetEntity(final IReference<?> reference, final IEntity entity) {
    return //
    canSetEntity(reference)
    && isOpen(entity)
    && reference.getReferencedTableName().equals(entity.getParentTableName())
    && !reference.referencesEntity(entity); //Important: When a Reference is set new data records could be created.
  }

  //method
  private boolean canSetEntity(final IReference<?> reference) {
    return //
    isOpen(reference)
    && reference.belongsToEntity();
  }
}
