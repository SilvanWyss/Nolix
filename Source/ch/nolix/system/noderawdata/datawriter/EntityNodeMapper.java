package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.rawdataapi.dto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableInfo;

final class EntityNodeMapper {

  public Node createNodeFromEntityWithSaveStamp(
    final ITableInfo tableInfo,
    final EntityCreationDto newEntity,
    final long saveStamp) {
    return //
    Node.withHeaderAndChildNodes(
      NodeHeaderCatalogue.ENTITY,
      createAttributesFromNewEntityWithSaveStamp(newEntity, saveStamp, tableInfo));
  }

  private IContainer<Node> createAttributesFromNewEntityWithSaveStamp(
    final EntityCreationDto newEntity,
    final long saveStamp,
    final ITableInfo tableInfo) {

    final var attributes = new Node[2 + tableInfo.getColumnInfos().getCount()];

    attributes[0] = createIdAttributeFrom(newEntity);
    attributes[1] = createSaveStampAttribute(saveStamp);

    for (final var f : newEntity.contentFields()) {

      final var columnInfo = tableInfo.getColumnInfoByColumnName(f.columnName());
      final var index = columnInfo.getColumnIndexOnEntityNode() - 1;

      final var valueAsString = f.optionalContent();
      if (valueAsString.isEmpty()) {
        attributes[index] = Node.EMPTY_NODE;
      } else {
        attributes[index] = Node.withHeader(valueAsString.get());
      }
    }

    return ContainerView.forArray(attributes);
  }

  private Node createIdAttributeFrom(final EntityCreationDto newEntity) {
    return Node.withHeader(newEntity.id());
  }

  private Node createSaveStampAttribute(final long saveStamp) {
    return Node.withHeader(saveStamp);
  }
}
