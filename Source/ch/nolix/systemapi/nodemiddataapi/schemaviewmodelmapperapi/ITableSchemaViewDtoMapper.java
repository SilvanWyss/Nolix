package ch.nolix.systemapi.nodemiddataapi.schemaviewmodelmapperapi;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public interface ITableSchemaViewDtoMapper {

  /**
   * @param tableNode
   * @return a new {@link TableViewDto} from the given tableNode.
   * @throws RuntimeException if the given tableNode is null.
   */
  TableViewDto mapTableNodeToTableViewDto(IMutableNode<?> tableNode);
}
