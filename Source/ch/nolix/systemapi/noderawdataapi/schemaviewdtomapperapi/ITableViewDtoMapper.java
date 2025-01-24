package ch.nolix.systemapi.noderawdataapi.schemaviewdtomapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public interface ITableViewDtoMapper {

  /**
   * @param tableNode
   * @return a new {@link TableViewDto} from the given tableNode.
   * @throws RuntimeException if the given tableNode is null.
   */
  TableViewDto mapTableNodeToTableViewDto(IMutableNode<?> tableNode);
}
