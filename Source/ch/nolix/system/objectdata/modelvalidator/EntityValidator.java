/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.modelexaminer.EntityExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IEntityExaminer;
import ch.nolix.systemapi.objectdata.modelvalidator.IEntityValidator;

/**
 * @author Silvan Wyss
 */
public final class EntityValidator implements IEntityValidator {
  private static final IEntityExaminer ENTITY_EXAMINER = new EntityExaminer();

  @Override
  public void assertBelongsToTable(final IEntity entity) {
    if (!entity.belongsToTable()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(entity, ITable.class);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanBeDeleted(final IEntity entity) {
    if (!ENTITY_EXAMINER.canBeDeleted(entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(entity, "cannot be deleted");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanSetParentTable(final IEntity entity, final ITable<? extends IEntity> table) {
    if (!ENTITY_EXAMINER.canSetParentTable(entity, table)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(table, "cannot be set to the Entity");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertDoesNotBelongToTable(final IEntity entity) {
    if (entity.belongsToTable()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(entity, entity.getStoredParentTable());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertHasSaveStamp(final IEntity entity) {
    if (!entity.hasSaveStamp()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(entity, LowerCaseVariableCatalog.SAVE_STAMP);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsNotReferenced(final IEntity entity) {
    if (ENTITY_EXAMINER.isReferenced(entity)) {
      throw ReferencedArgumentException.forArgument(entity);
    }
  }
}
