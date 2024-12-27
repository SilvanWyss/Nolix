package ch.nolix.system.objectschema.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.validator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.contentmodel.AbstractBackReferenceModel;
import ch.nolix.system.objectschema.contentmodel.AbstractReferenceModel;
import ch.nolix.system.objectschema.modelvalidator.DatabaseValidator;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.system.objectschema.schematool.ParameterizedFieldTypeTool;
import ch.nolix.system.objectschema.schematool.TableTool;
import ch.nolix.systemapi.databaseobjectapi.validatorapi.IDatabaseObjectValidator;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelmutationvalidatorapi.IColumnMutationValidator;
import ch.nolix.systemapi.objectschemaapi.modelvalidatorapi.IDatabaseValidator;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IParameterizedFieldTypeTool;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.ITableTool;

/**
 * @author Silvan Wyss
 * @version 2021-07-11
 */
public final class ColumnMutationValidator implements IColumnMutationValidator {

  private static final IDatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  private static final IDatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private static final ITableTool TABLE_TOOL = new TableTool();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  private static final IParameterizedFieldTypeTool PARAMETERIZED_FIELD_TYPE_TOOL = //
  new ParameterizedFieldTypeTool();

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanBeDeleted(final IColumn column) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    DATABASE_OBJECT_VALIDATOR.assertIsNotDeleted(column);

    if (column.isBackReferenced()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is back referenced");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanSetName(final IColumn column, final String name) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);

    if (column.belongsToTable()) {
      TABLE_TOOL.assertDoesNotContainColumnWithGivenName(column.getStoredParentTable(), name);
    }

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanSetContentModel(final IColumn column, final IContentModel contentModel) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);

    if (column.containsAny()) {
      throw NonEmptyArgumentException.forArgument(this);
    }

    if (PARAMETERIZED_FIELD_TYPE_TOOL.isABaseReferenceType(contentModel)
    && COLUMN_TOOL.belongsToDatabase(column)) {

      final var baseParameterizedReferenceType = (AbstractReferenceModel) contentModel;
      final var referencedTables = baseParameterizedReferenceType.getReferencedTables();

      DATABASE_VALIDATOR.assertContainsTables(COLUMN_TOOL.getParentDatabase(column), referencedTables);
    }

    if (!PARAMETERIZED_FIELD_TYPE_TOOL.isABaseReferenceType(contentModel) && column.isBackReferenced()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is back referenced");
    }

    if (PARAMETERIZED_FIELD_TYPE_TOOL.isABaseBackReferenceType(contentModel)
    && COLUMN_TOOL.belongsToDatabase(column)) {

      final var baseParameterizedBackReferenceType = (AbstractBackReferenceModel) contentModel;
      final var backReferencedColumn = baseParameterizedBackReferenceType.getBackReferencedColumn();

      DATABASE_TOOL.assertContainsTableWithGivenColumn(
        COLUMN_TOOL.getParentDatabase(column),
        backReferencedColumn);
    }
  }
}
