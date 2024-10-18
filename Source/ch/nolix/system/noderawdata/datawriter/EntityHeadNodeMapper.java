package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

public final class EntityHeadNodeMapper {

  public Node createEntityHeadNode(final ITableInfo tableInfo, final INewEntityDto newEntity) {
    return Node.withHeaderAndChildNode(StructureHeaderCatalogue.ENTITY_HEAD, tableInfo.getTableId(), newEntity.getId());
  }
}
