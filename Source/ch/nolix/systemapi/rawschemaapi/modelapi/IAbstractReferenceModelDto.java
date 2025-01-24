package ch.nolix.systemapi.rawschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-01-24
 */
public interface IAbstractReferenceModelDto extends IContentModelDto {

  /**
   * @return the ids of the referenced tables of the current
   *         {@link IAbstractReferenceModelDto};
   */
  IContainer<String> getReferencedTableIds();
}
