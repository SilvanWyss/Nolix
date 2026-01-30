/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IEntityExaminer;
import ch.nolix.systemapi.objectdata.modelexaminer.IEntityExaminerHelper;

/**
 * @author Silvan Wyss
 */
public final class EntityExaminer extends DatabaseObjectExaminer implements IEntityExaminer {
  private static final IEntityExaminerHelper ENTITY_EXAMINER_HELPER = new EntityExaminerHelper();

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
  public boolean canSetParentTable(final IEntity entity) {
    return //
    entity != null
    && entity.isOpen()
    && !entity.belongsToTable();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetParentTable(final IEntity entity, final ITable<? extends IEntity> table) {
    return //
    canSetParentTable(entity)
    && table != null
    && table.isOpen()
    && entity.getClass() == table.getEntityType();
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

  private boolean isReferencedInPersistedDataIgnoringLocallyDeletedEntities(final IEntity entity) {
    if (entity.isReferencedInPersistedData()) {
      final var locallyDeletedEntities = //
      ENTITY_EXAMINER_HELPER.getLocallyDeletedEntitiesIds(entity.getStoredParentDatabase());

      return entity.isReferencedInPersistedDataIgnoringGivenEntities(locallyDeletedEntities);
    }

    return false;
  }
}
