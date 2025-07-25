package ch.nolix.systemapi.midschema.model;

/**
 * @author Silvan Wyss
 * @version 2025-01-24
 */
public interface IAbstractReferenceModelDto extends IContentModelDto {

  /**
   * @return the id of the referenced table of the current
   *         {@link IAbstractReferenceModelDto};
   */
  String getReferencedTableId();
}
