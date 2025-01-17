package ch.nolix.system.noderawdata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalog;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IEntityIndexNodeMapper;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public final class EntityIndexNodeMapper implements IEntityIndexNodeMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapEntityCreationDtoToEntityIndexNode(
    final EntityCreationDto entityCreationDto,
    final TableViewDto tableView) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalog.ENTITY_HEAD, tableView.id(), entityCreationDto.id());
  }
}
