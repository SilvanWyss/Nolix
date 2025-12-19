package ch.nolix.systemapi.nodemiddata.modelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

/**
 * @author Silvan Wyss
 */
public interface IEntityLoadingDtoMapper {
  /**
   * @param entityNode
   * @param tableView
   * @return a new {@link EntityLoadingDto} from the given entityNode.
   * @throws RuntimeException if the given entityNode is null.
   */
  EntityLoadingDto mapEntityNodeToEntityLoadingDto(IMutableNode<?> entityNode, TableViewDto tableView);
}
