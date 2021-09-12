//package declaration
package ch.nolix.system.nodeintermediateschema.structure;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.document.node.BaseNode;

//class
public final class DatabaseNodeSearcher {
	
	//method
	public BaseNode getDatabasePropertiesNodeFromDatabaseNode(final BaseNode databaseNode) {
		return databaseNode.getRefFirstAttribute(SubNodeHeaderCatalogue.DATABASE_PROPERTIES);
	}
	
	//method
	public BaseNode getTableNodeFromDatabaseNode(final BaseNode databaseNode, final String tableName) {
		return
		getTableNodesFromDatabaseNode(databaseNode).getRefFirst(
			tsn -> tsn.getRefFirstAttribute(SubNodeHeaderCatalogue.NAME).getRefOneAttribute().hasHeader(tableName)
		);
	}
	
	//method
	public IContainer<BaseNode> getTableNodesFromDatabaseNode(final BaseNode databaseNode) {
		return databaseNode.getRefAttributes(SubNodeHeaderCatalogue.TABLE);
	}
}
