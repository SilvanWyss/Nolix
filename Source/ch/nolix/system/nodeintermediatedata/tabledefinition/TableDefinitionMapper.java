//package declaration
package ch.nolix.system.nodeintermediatedata.tabledefinition;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.nodeintermediatedata.structure.ColumnNodeSearcher;
import ch.nolix.system.nodeintermediatedata.structure.TableNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;

//class
final class TableDefinitionMapper {
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public TableDefinition createTableDefinitionFromTableNode(final BaseNode tableNode) {
		return new TableDefinition(
			getTableNameFromTableNode(tableNode),
			getColumnHeadersInOrderFromTableNode(tableNode)
		);
	}
	
	//method
	private String getColumnHeaderFromColumnNode(final BaseNode columnNode) {
		return columnNodeSearcher.getRefHeaderNodeFromColumnNode(columnNode).getOneAttributeHeader();
	}
	
	//method
	private IContainer<String> getColumnHeadersInOrderFromTableNode(final BaseNode tableNode) {
		return getColumnNodesInOrderFromTableNode(tableNode).to(this::getColumnHeaderFromColumnNode);
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
