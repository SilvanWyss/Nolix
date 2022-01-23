//package declaration
package ch.nolix.system.noderawobjectschema.structure;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.document.node.BaseNode;

//class
public final class DatabaseNodeSearcher {
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public BaseNode getRefColumnNodeByColumnIdFromDatabaseNode(final BaseNode databaseNode, final String columnId) {
		return
		getRefTableNodesFromDatabaseNode(databaseNode)
		.toFromMany(tableNodeSearcher::getRefColumnNodesFromTableNode)
		.getRefFirst(cn -> columnNodeSearcher.getRefIdNodeFromColumnNode(cn).getRefOneAttribute().hasHeader(columnId));
	}
	
	//method
	public BaseNode getRefDatabasePropertiesNodeFromDatabaseNode(final BaseNode databaseNode) {
		return databaseNode.getRefFirstAttribute(SubNodeHeaderCatalogue.DATABASE_PROPERTIES);
	}
	
	//method
	public BaseNode getRefTableNodeByTableIdFromDatabaseNode(final BaseNode databaseNode, final String tableId) {
		return
		getRefTableNodesFromDatabaseNode(databaseNode).getRefFirst(
			tsn -> tsn.getRefFirstAttribute(SubNodeHeaderCatalogue.ID).getRefOneAttribute().hasHeader(tableId)
		);
	}
	
	//method
	public BaseNode getRefTableNodeByTableNameFromDatabaseNode(final BaseNode databaseNode, final String tableName) {
		return
		getRefTableNodesFromDatabaseNode(databaseNode).getRefFirst(
			tsn -> tsn.getRefFirstAttribute(SubNodeHeaderCatalogue.NAME).getRefOneAttribute().hasHeader(tableName)
		);
	}
	
	//method
	public IContainer<BaseNode> getRefTableNodesFromDatabaseNode(final BaseNode databaseNode) {
		return databaseNode.getRefAttributes(SubNodeHeaderCatalogue.TABLE);
	}
}
