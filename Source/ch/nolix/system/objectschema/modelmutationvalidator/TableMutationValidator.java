package ch.nolix.system.objectschema.modelmutationvalidator;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.model.AbstractBackReferenceModel;
import ch.nolix.system.objectschema.model.AbstractReferenceModel;
import ch.nolix.system.objectschema.modelvalidator.DatabaseValidator;
import ch.nolix.system.objectschema.modelvalidator.TableValidator;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.systemapi.databaseobject.modelvalidator.IDatabaseObjectValidator;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelmutationvalidator.ITableMutationValidator;
import ch.nolix.systemapi.objectschema.modelvalidator.IDatabaseValidator;
import ch.nolix.systemapi.objectschema.modelvalidator.ITableValidator;
import ch.nolix.systemapi.objectschema.schematool.IColumnTool;
import ch.nolix.systemapi.objectschema.schematool.IDatabaseTool;

/**
 * @author Silvan Wyss
 * @version 2021-07-11
 */
public final class TableMutationValidator implements ITableMutationValidator {

  private static final IDatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  private static final IDatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private static final ITableValidator TABLE_VALIDATOR = new TableValidator();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanAddColumnToTable(final ITable table, final IColumn column) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);
    TABLE_VALIDATOR.assertDoesNotContainColumnWithName(table, column.getName());

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(column);
    DATABASE_OBJECT_VALIDATOR.assertIsNew(column);

    if (COLUMN_TOOL.isAReferenceColumn(column) && table.belongsToDatabase()) {

      final var abstractReferenceModel = (AbstractReferenceModel) column.getContentModel();
      final var referencedTable = abstractReferenceModel.getReferencedTable();

      DATABASE_VALIDATOR.assertContainsTable(table.getStoredParentDatabase(), referencedTable);
    }

    if (COLUMN_TOOL.isABackReferenceColumn(column) && table.belongsToDatabase()) {

      final var abstractBackReferenceModel = (AbstractBackReferenceModel) (column.getContentModel());
      final var backReferencedColumn = abstractBackReferenceModel.getBackReferencedColumn();

      DATABASE_TOOL.assertContainsTableWithGivenColumn(table.getStoredParentDatabase(), backReferencedColumn);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanDeleteTable(final ITable table) {
    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);
    DATABASE_OBJECT_VALIDATOR.assertIsNotNew(table);
    DATABASE_OBJECT_VALIDATOR.assertIsNotDeleted(table);
    TABLE_VALIDATOR.assertIsNotReferenced(table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanSetNameToTable(final ITable table, final String name) {

    DATABASE_OBJECT_VALIDATOR.assertIsOpen(table);

    if (table.belongsToDatabase()) {
      DATABASE_TOOL.assertDoesNotContainTableWithGivenName(table.getStoredParentDatabase(), name);
    }

    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
  }
}
