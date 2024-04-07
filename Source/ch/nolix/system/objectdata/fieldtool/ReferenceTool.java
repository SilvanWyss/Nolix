//package declaration
package ch.nolix.system.objectdata.fieldtool;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IReferenceTool;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//class
public final class ReferenceTool extends FieldTool implements IReferenceTool {

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
  public Optional<IBaseBackReference<IEntity>> getOptionalStoredBaseBackReferenceForReference(
    final IReference<IEntity> reference) {

    if (reference.isEmpty()) {
      return Optional.empty();
    }

    return getOptionalStoredBaseBackReferenceOfReferenceWhenContainsAny(reference);
  }

  //method
  @Override
  public boolean toReferenceCanSetEntity(final IReference<?> reference, final IEntity entity) {
    return //
    canSetEntity(reference)
    && isOpen(reference)
    && reference.getReferencedTableName().equals(entity.getParentTableName())
    && !reference.referencesEntity(entity); //Important: When a Reference is set new data records could be created.
  }

  //method
  private boolean canSetEntity(final IReference<?> reference) {
    return //
    isOpen(reference)
    && reference.belongsToEntity();
  }

  //method
  @SuppressWarnings("unchecked")
  private Optional<IBaseBackReference<IEntity>> getOptionalStoredBaseBackReferenceOfReferenceWhenContainsAny(
    final IReference<IEntity> reference) {

    final var referencedEntity = reference.getStoredReferencedEntity();

    final var backReference = //
    referencedEntity.internalGetStoredProperties().getOptionalStoredFirst(p -> p.referencesBackProperty(reference));

    return backReference.map(br -> (IBaseBackReference<IEntity>) br);
  }

  //method
  private boolean isOpen(final IDatabaseObject databaseObject) {
    return //
    databaseObject != null
    && databaseObject.isOpen();
  }
}
