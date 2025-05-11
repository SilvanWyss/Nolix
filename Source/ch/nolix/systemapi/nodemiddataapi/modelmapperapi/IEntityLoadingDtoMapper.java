package ch.nolix.systemapi.nodemiddataapi.modelmapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.middataapi.midschemaview.TableViewDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-07
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
