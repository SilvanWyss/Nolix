package ch.nolix.systemapi.objectschema.modelexaminer;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IAbstractReferenceModel;
import ch.nolix.systemapi.objectschema.model.IAbstractValueModel;
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
   * @return true if the given contentModel is a {@link IAbstractReferenceModel},
   *         false otherwise.
   */
  boolean isAbstractReferenceModel(IContentModel contentModel);

  /**
   * @param contentModel
   * @return true if the given contentModel is a {@link IAbstractValueModel},
   *         false otherwise.
   */
  boolean isAbstractValueModel(IContentModel contentModel);
}
