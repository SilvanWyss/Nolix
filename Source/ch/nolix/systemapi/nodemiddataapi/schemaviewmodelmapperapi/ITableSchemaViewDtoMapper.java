package ch.nolix.systemapi.nodemiddataapi.schemaviewmodelmapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.middataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public interface ITableSchemaViewDtoMapper {

  /**
   * @param tableNode
   * @return a new {@link TableSchemaViewDto} from the given tableNode.
   * @throws RuntimeException if the given tableNode is null.
   */
  TableSchemaViewDto mapTableNodeToTableViewDto(IMutableNode<?> tableNode);
}
