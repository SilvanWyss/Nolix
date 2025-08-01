package ch.nolix.system.objectschema.schematool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectschema.modelexaminer.ContentModelExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.BaseContentType;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschema.model.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelexaminer.IContentModelExaminer;
import ch.nolix.systemapi.objectschema.schematool.IColumnTool;

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
    return column.getContentModel().getContentType();
  }

  @Override
  public IDatabase getParentDatabase(final IColumn column) {
    return column.getStoredParentTable().getStoredParentDatabase();
  }

  @Override
  public boolean isABackReferenceColumn(final IColumn column) {

    final var contentModel = column.getContentModel();

    return CONTENT_MODEL_EXAMINER.isAbstractBackReferenceModel(contentModel);
  }

  @Override
  public boolean isAReferenceColumn(final IColumn column) {

    final var contentModel = column.getContentModel();

    return CONTENT_MODEL_EXAMINER.isAbstractReferenceModel(contentModel);
  }

  @Override
  public boolean isAValueColumn(final IColumn column) {

    final var contentModel = column.getContentModel();

    return CONTENT_MODEL_EXAMINER.isAbstractValueModel(contentModel);
  }

  @Override
  public boolean isAValidBackReferenceColumn(IColumn column) {

    final var contentModel = column.getContentModel();

    if (contentModel instanceof IAbstractBackReferenceModel abstractBackReferenceModel) {

      final var backReferencedColumn = abstractBackReferenceModel.getBackReferencedColumn();
      final var backReferencedColumnContentModel = backReferencedColumn.getContentModel();

      if (!CONTENT_MODEL_EXAMINER.isAbstractReferenceModel(backReferencedColumnContentModel)) {
        return false;
      }

      return referencesGivenTable(backReferencedColumn, column.getStoredParentTable());
    }

    return false;
  }

  @Override
  public boolean referencesBackGivenColumn(final IColumn column, final IColumn probableBackReferencedColumn) {
    return column.getContentModel().referencesBackColumn(probableBackReferencedColumn);
  }

  @Override
  public boolean referencesGivenTable(final IColumn column, final ITable table) {
    return column.getContentModel().referencesTable(table);
  }
}
