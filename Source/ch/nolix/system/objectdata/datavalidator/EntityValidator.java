//package declaration
package ch.nolix.system.objectdata.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.datavalidatorapi.IEntityValidator;

//class
public final class EntityValidator implements IEntityValidator {

  //constant
  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  //method
  @Override
  public void assertBelongsToTable(final IEntity entity) {
    if (!entity.belongsToTable()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(entity, ITable.class);
    }
  }

  //method
  @Override
  public void assertCanBeDeleted(final IEntity entity) {
    if (!ENTITY_TOOL.canBeDeleted(entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(entity, "cannot be deleted");
    }
  }

  //method
  @Override
  public void assertDoesNotBelongToTable(final IEntity entity) {
    if (entity.belongsToTable()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(entity, entity.getStoredParentTable());
    }
  }

  //method
  @Override
  public void assertHasSaveStamp(final IEntity entity) {
    if (!entity.hasSaveStamp()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(entity, LowerCaseVariableCatalogue.SAVE_STAMP);
    }
  }

  //method
  @Override
  public void assertIsNotReferenced(final IEntity entity) {
    if (ENTITY_TOOL.isReferenced(entity)) {
      throw ReferencedArgumentException.forArgument(entity);
    }
  }
}
