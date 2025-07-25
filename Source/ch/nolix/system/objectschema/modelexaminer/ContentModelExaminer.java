package ch.nolix.system.objectschema.modelexaminer;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.modelexaminer.IContentModelExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-01-31
 */
public final class ContentModelExaminer extends DatabaseObjectExaminer implements IContentModelExaminer {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAbstractBackReferenceModel(final IContentModel contentModel) {
    return //
    contentModel != null &&
    contentModel.getContentType().getBaseType() == BaseContentType.BASE_BACK_REFERENCE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAbstractReferenceModel(final IContentModel contentModel) {
    return //
    contentModel != null &&
    contentModel.getContentType().getBaseType() == BaseContentType.BASE_REFERENCE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAbstractValueModel(final IContentModel contentModel) {
    return //
    contentModel != null &&
    contentModel.getContentType().getBaseType() == BaseContentType.BASE_VALUE;
  }
}
