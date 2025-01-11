package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;

final class EntityNodeMapper {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  public Node createNodeFromEntityWithSaveStamp(
    final TableViewDto tableView,
    final EntityCreationDto newEntity,
    final long saveStamp) {
    return //
    Node.withHeaderAndChildNodes(
      NodeHeaderCatalog.ENTITY,
      createAttributesFromNewEntityWithSaveStamp(newEntity, saveStamp, tableView));
  }

  private IContainer<Node> createAttributesFromNewEntityWithSaveStamp(
    final EntityCreationDto newEntity,
    final long saveStamp,
    final TableViewDto tableView) {

    final var attributes = new Node[2 + tableView.columnViews().getCount()];

    attributes[0] = createIdAttributeFrom(newEntity);
    attributes[1] = createSaveStampAttribute(saveStamp);

    for (final var f : newEntity.contentFields()) {

      final var columnInfo = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableView, f.columnName());
      final var index = columnInfo.oneBasedOrdinalIndex() - 1;

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
