package ch.nolix.system.objectschema.schematool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectschema.modelexaminer.ContentModelExaminer;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.IContentModelExaminer;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;

public final class ColumnTool extends DatabaseObjectExaminer implements IColumnTool {

  private static final IContentModelExaminer CONTENT_MODEL_EXAMINER = new ContentModelExaminer();

  @Override
  public void assertBelongsToTable(final IColumn column) {
    if (!column.belongsToTable()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(column, ITable.class);
    }
  }

  @Override
  public void assertDoesNotBelongToTable(final IColumn column) {
    if (column.belongsToTable()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(column, column.getStoredParentTable());
    }
  }

  @Override
  public void assertIsABackReferenceColumn(final IColumn column) {
    if (!isABackReferenceColumn(column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "is not a back reference column");
    }
  }

  @Override
  public void assertIsAReferenceColumn(final IColumn column) {
    if (!isAReferenceColumn(column)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(column, "is not any reference column");
    }
  }

  @Override
  public boolean belongsToDatabase(final IColumn column) {
    return (column.belongsToTable() && column.getStoredParentTable().belongsToDatabase());
  }

  @Override
  public BaseContentType getBaseFieldType(IColumn column) {
    return getFieldType(column).getBaseType();
  }

  @Override
  public ContentType getFieldType(final IColumn column) {

    //TODO: Adjust
    return column.getContentModels().getStoredFirst().getContentType();
  }

  @Override
  public IDatabase getParentDatabase(final IColumn column) {
    return column.getStoredParentTable().getStoredParentDatabase();
  }

  @Override
  public boolean isABackReferenceColumn(final IColumn column) {
    return column.getContentModels().containsOnly(CONTENT_MODEL_EXAMINER::isAbstractBackReferenceModel);
  }

  @Override
  public boolean isAReferenceColumn(final IColumn column) {
    return column.getContentModels().containsOnly(CONTENT_MODEL_EXAMINER::isAbstractReferenceModel);
  }

  @Override
  public boolean isAValueColumn(final IColumn column) {
    return column.getContentModels().containsOnly(CONTENT_MODEL_EXAMINER::isAbstractValueModel);
  }

  @Override
  public boolean isAValidBackReferenceColumn(IColumn column) {

    //TODO: Adjust
    final var contentModel = column.getContentModels().getStoredFirst();

    if (contentModel instanceof IAbstractBackReferenceModel abstractBackReferenceModel) {

      final var backReferencedColumn = abstractBackReferenceModel.getBackReferencedColumn();

      //TODO: Adjust
      final var backReferencedColumnContentModel = backReferencedColumn.getContentModels().getStoredFirst();

      if (!CONTENT_MODEL_EXAMINER.isAbstractReferenceModel(backReferencedColumnContentModel)) {
        return false;
      }

      return referencesGivenTable(backReferencedColumn, column.getStoredParentTable());
    }

    return false;
  }

  @Override
  public boolean referencesBackGivenColumn(
    final IColumn column,
    final IColumn probableBackReferencedColumn) {
    return column.getContentModels().containsAny(c -> c.referencesBackColumn(probableBackReferencedColumn));
  }

  @Override
  public boolean referencesGivenTable(final IColumn column, final ITable table) {
    return column.getContentModels().containsAny(c -> c.referencesTable(table));
  }
}
