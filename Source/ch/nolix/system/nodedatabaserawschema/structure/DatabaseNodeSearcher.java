//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.document.node.BaseNode;

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
	
	//method
	public int getTableNodeCount(final BaseNode databaseNode) {
		return databaseNode.getRefAttributes().getCount(a -> a.hasHeader(SubNodeHeaderCatalogue.TABLE));
	}
}
