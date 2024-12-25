package ch.nolix.systemapi.noderawschemaapi.flatdtomapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawschemaapi.flatschemadto.FlatTableDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IFlatTableDtoMapper {

  /**
   * @param tableNode
   * @return a new {@link FlatTableDto} from the given tableNode.
   * @throws RuntimeException if the given tableNode is not valid.
   */
  FlatTableDto mapTableNodeToFlatTableDto(IMutableNode<?> tableNode);
}
