package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.rawdataapi.datadto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

public final class EntityHeadNodeMapper {

  public Node createEntityHeadNode(final ITableInfo tableInfo, final EntityCreationDto newEntity) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.ENTITY_HEAD, tableInfo.getTableId(), newEntity.id());
  }
}
