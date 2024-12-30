package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.IEntityExaminer;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.IFieldExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class EntityExaminer extends DatabaseObjectExaminer implements IEntityExaminer {

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final IEntity entity) {

    if (isNewOrEdited(entity)) {
      return entity.internalGetStoredFields().containsOnly(FIELD_EXAMINER::isSetForCaseWhenIsMandatoryAndNewOrEdited);
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canBeDeleted(final IEntity entity) {
    return //
    entity != null
    && entity.isLoaded()
    && !isReferencedIgnoringLocallyDeletedEntities(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canBeInsertedIntoTable(final IEntity entity) {
    return //
    entity != null
    && entity.isNew()
    && entity.belongsToTable();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferenced(final IEntity entity) {
    return //
    isReferencedInLocalData(entity)
    || entity.isReferencedInPersistedData();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferencedIgnoringLocallyDeletedEntities(IEntity entity) {
    return //
    isReferencedInLocalData(entity)
    || isReferencedInPersistedDataIgnoringLocallyDeletedEntities(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferencedInLocalData(final IEntity entity) {

    if (!entity.belongsToTable()) {
      return false;
    }

    for (final var t : entity.getStoredParentTable().getStoredParentDatabase().getStoredTables()) {
      if (t.internalGetStoredEntitiesInLocalData().containsAny(e -> referencesGivenEntity(e, entity))) {
        return true;
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesGivenEntity(final IEntity sourceEntity, final IEntity entity) {
    return sourceEntity.internalGetStoredFields().containsAny(p -> p.referencesEntity(entity));
  }

  private IContainer<String> getLocallyDeletedEntities(final IEntity entity) {
    return //
    entity
      .getStoredParentTable()
      .getStoredParentDatabase()
      .getStoredTables()
      .toMultiple(ITable::internalGetStoredEntitiesInLocalData).getStoredSelected(IEntity::isDeleted)
      .to(IEntity::getId);
  }

  private boolean isReferencedInPersistedDataIgnoringLocallyDeletedEntities(final IEntity entity) {

    if (entity.isReferencedInPersistedData()) {

      final var locallyDeletedEntities = getLocallyDeletedEntities(entity);

      return entity.isReferencedInPersistedDataIgnoringGivenEntities(locallyDeletedEntities);
    }

    return false;
  }
}
