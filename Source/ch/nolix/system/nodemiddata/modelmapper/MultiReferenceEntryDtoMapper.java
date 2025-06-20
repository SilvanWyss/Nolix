package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.midschemaview.modelsearcher.DatabaseViewSearcher;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelsearcherapi.IDatabaseViewSearcher;
import ch.nolix.systemapi.nodemiddataapi.modelmapperapi.IMultiReferenceEntryDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-02-21
 */
public final class MultiReferenceEntryDtoMapper implements IMultiReferenceEntryDtoMapper {

  private static final IDatabaseViewSearcher DATABASE_VIEW_SEARCHER = new DatabaseViewSearcher();

  /**
   * {@inheritDoc}
   */
  @Override
  public MultiReferenceEntryDto mapMultiReferenceEntryNodeToMultiReferenceEntryDto(
    final IMutableNode<?> multiReferenceEntryNode,
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName,
    final DatabaseViewDto databaseView) {

    final var referencedEntityId = multiReferenceEntryNode.getStoredChildNodeAtOneBasedIndex(1).getHeader();
    final var referencedEntityTableId = multiReferenceEntryNode.getStoredChildNodeAtOneBasedIndex(2).getHeader();

    final var referencedEntityTableView = //
    DATABASE_VIEW_SEARCHER.getTableViewByTableId(databaseView, referencedEntityTableId);

    final var referencedEntityTableName = referencedEntityTableView.name();

    return //
    new MultiReferenceEntryDto(
      tableName,
      entityId,
      multiReferenceColumnName,
      referencedEntityId,
      referencedEntityTableName);
  }
}
