package ch.nolix.system.objectschema.schematool;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.IContentModelExaminer;

public final class ParameterizedFieldTypeTool extends DatabaseObjectExaminer
implements IContentModelExaminer {

  @Override
  public boolean isAbstractBackReferenceModel(final IContentModel contentModel) {
    return (contentModel.getContentType().getBaseType() == BaseContentType.BASE_BACK_REFERENCE);
  }

  @Override
  public boolean isAbstractReferenceModel(IContentModel contentModel) {
    return (contentModel.getContentType().getBaseType() == BaseContentType.BASE_REFERENCE);
  }

  @Override
  public boolean isAbstractValueModel(IContentModel contentModel) {
    return (contentModel.getContentType().getBaseType() == BaseContentType.BASE_VALUE);
  }
}
