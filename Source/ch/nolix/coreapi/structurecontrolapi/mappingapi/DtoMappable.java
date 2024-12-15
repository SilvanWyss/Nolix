package ch.nolix.coreapi.structurecontrolapi.mappingapi;

/**
 * A {@link DtoMappable} can be mapped to a Dto (data transfer object).
 * 
 * @author Silvan Wyss
 * @version 2024-12-15
 * @param <D> is the type of the Dto a {@link DtoMappable} can be mapped to.
 */
public interface DtoMappable<D> {

  /**
   * @return a new Dto from the current {@link DtoMappable}.
   */
  D toDto();
}
