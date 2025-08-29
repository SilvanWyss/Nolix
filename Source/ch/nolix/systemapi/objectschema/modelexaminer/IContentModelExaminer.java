package ch.nolix.systemapi.objectschema.modelexaminer;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IBaseReferenceModel;
import ch.nolix.systemapi.objectschema.model.IBaseValueModel;
import ch.nolix.systemapi.objectschema.model.IContentModel;

/**
 * @author Silvan Wyss
 * @version 2024-01-31
 */
public interface IContentModelExaminer extends IDatabaseObjectExaminer {

  /**
   * @param contentModel
   * @return true if the given contentModel is a
   *         {@link IAbstractBackReferenceModel}, false otherwise.
   */
  boolean isAbstractBackReferenceModel(IContentModel contentModel);

  /**
   * @param contentModel
   * @return true if the given contentModel is a {@link IBaseReferenceModel},
   *         false otherwise.
   */
  boolean isAbstractReferenceModel(IContentModel contentModel);

  /**
   * @param contentModel
   * @return true if the given contentModel is a {@link IBaseValueModel},
   *         false otherwise.
   */
  boolean isAbstractValueModel(IContentModel contentModel);
}
