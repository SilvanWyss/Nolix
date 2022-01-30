//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableDefinition;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.noderawobjectschema.structure.TableNodeSearcher;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;

//class
final class TableDefinitionMapper {
	
	//static attribute
	private static final ColumnDefinitionMapper columnDefinitionMapper = new ColumnDefinitionMapper();
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//method
	public TableDefinition createTableDefinitionFromTableNode(final BaseNode tableNode) {
		return new TableDefinition(
			getTableNameFromTableNode(tableNode),
			getContentColumnDefinitionsFromTableNode(tableNode)
		);
	}
	
	//method
	private IContainer<IColumnInfo> getContentColumnDefinitionsFromTableNode(BaseNode tableNode) {
		return
		getColumnNodesInOrderFromTableNode(tableNode).to(columnDefinitionMapper::createColumnDefinitionFromColumnNode);
	}
	
	//method
	private IContainer<BaseNode> getColumnNodesInOrderFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefAttributes(SubNodeHeaderCatalogue.COLUMN);
	}
	
	//method
	private String getTableNameFromTableNode(final BaseNode tableNode) {
		return tableNodeSearcher.getRefNameNodeFromTableNode(tableNode).getOneAttributeHeader();
	}
}
