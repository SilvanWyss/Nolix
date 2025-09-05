package ch.nolix.systemapi.midschema.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-01-24
 */
public interface IBaseReferenceModelDto extends IContentModelDto {
  /**
   * @return the ids of the referenceable tables of the current
   *         {@link IBaseReferenceModelDto};
   */
  IContainer<String> getReferenceableTableIds();
}
