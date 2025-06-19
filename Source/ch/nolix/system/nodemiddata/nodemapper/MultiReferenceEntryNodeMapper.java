package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;
import ch.nolix.systemapi.nodemiddataapi.nodemapperapi.IMultiReferenceEntryNodeMapper;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public final class MultiReferenceEntryNodeMapper implements IMultiReferenceEntryNodeMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapMultiReferenceEntryDtoToMultiReferenceEntryNode(
    final MultiReferenceEntryDto multiReferenceEntryDto,
    final DatabaseViewDto databaseView) {

    final var referencedEntityId = multiReferenceEntryDto.referencedEntityId();
    final var referencedEntityTableName = multiReferenceEntryDto.referencedEntityTableName();

    //TODO: Create DatabaseViewSearcher
    final var referencedEntityTableView = //
    databaseView.tableSchemaViews().getStoredFirst(t -> t.name().equals(referencedEntityTableName));

    final var referencedEntityTableId = referencedEntityTableView.id();

    return Node.withChildNode(Node.withHeader(referencedEntityId), Node.withHeader(referencedEntityTableId));
  }
}
