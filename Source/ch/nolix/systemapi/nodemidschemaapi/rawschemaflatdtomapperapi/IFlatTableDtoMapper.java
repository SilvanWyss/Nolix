package ch.nolix.systemapi.nodemidschemaapi.rawschemaflatdtomapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;

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
