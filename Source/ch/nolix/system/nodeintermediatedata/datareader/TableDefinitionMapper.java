//package declaration
package ch.nolix.system.nodeintermediatedata.datareader;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.nodeintermediatedata.structure.TableNodeSearcher;
import ch.nolix.system.nodeintermediatedata.tabledefinition.TableDefinition;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.sqlintermediatedata.sqlapi.IColumnDefinition;

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
	private IContainer<IColumnDefinition> getContentColumnDefinitionsFromTableNode(BaseNode tableNode) {
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
