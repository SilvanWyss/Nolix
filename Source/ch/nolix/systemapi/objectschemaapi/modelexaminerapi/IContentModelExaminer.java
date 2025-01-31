package ch.nolix.systemapi.objectschemaapi.modelexaminerapi;

import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.IAbstractReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.IAbstractValueModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;

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
