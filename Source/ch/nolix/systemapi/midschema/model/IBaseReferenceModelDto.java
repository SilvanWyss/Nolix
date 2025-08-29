package ch.nolix.systemapi.midschema.model;

/**
 * @author Silvan Wyss
 * @version 2025-01-24
 */
public interface IBaseReferenceModelDto extends IContentModelDto {

  /**
   * @return the id of the referenced table of the current
   *         {@link IBaseReferenceModelDto};
   */
  String getReferencedTableId();
}
