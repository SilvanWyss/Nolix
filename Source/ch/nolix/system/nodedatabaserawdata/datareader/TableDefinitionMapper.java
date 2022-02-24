//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.TableInfo;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
final class TableDefinitionMapper {
	
	//static attribute
	private static final ColumnDefinitionMapper columnDefinitionMapper = new ColumnDefinitionMapper();
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//method
	public TableInfo createTableDefinitionFromTableNode(final BaseNode tableNode) {
		return new TableInfo(
			getTableIdFromTableNode(tableNode),
			getTableNameFromTableNode(tableNode),
			getContentColumnDefinitionsFromTableNode(tableNode)
		);
	}
	
	//method
	private IContainer<IColumnInfo> getContentColumnDefinitionsFromTableNode(BaseNode tableNode) {
		return
		getRefColumnNodesInOrderFromTableNode(tableNode).to(columnDefinitionMapper::createColumnDefinitionFromColumnNode);
	}
	
	//method
	private IContainer<BaseNode> getRefColumnNodesInOrderFromTableNode(final BaseNode tableNode) {
		return tableNodeSearcher.getRefColumnNodesFromTableNode(tableNode);
	}
	
	//method
	private String getTableIdFromTableNode(final BaseNode tableNode) {
		return tableNodeSearcher.getTableIdFromTableNode(tableNode);
	}
	
	//method
	private String getTableNameFromTableNode(final BaseNode tableNode) {
		return tableNodeSearcher.getTableNameFromTableNode(tableNode);
	}
}
