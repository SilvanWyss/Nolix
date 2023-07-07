//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class TableNodeSearcher {
	
	//constant
	private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();
	
	//method
	public IMutableNode<?> getOriColumnNodeFromTableNodeByColumnName(
		final IMutableNode<?> tableNode,
		final String columnName
	) {
		return
		getOriColumnNodesFromTableNode(tableNode).getOriFirst(
			csn -> COLUMN_NODE_SEARCHER.getOriNameNodeFromColumnNode(csn).getOriSingleChildNode().hasHeader(columnName)
		);
	}
	
	//method
	public IContainer<? extends IMutableNode<?>> getOriColumnNodesFromTableNode(final IMutableNode<?> tableNode) {
		return tableNode.getOriChildNodesWithHeader(SubNodeHeaderCatalogue.COLUMN);
	}
	
	//method
	public IMutableNode<?> getOriIdNodeFromTableNode(final IMutableNode<?> tableNode) {
		return tableNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public IMutableNode<?> getOriNameNodeFromTableNode(final IMutableNode<?> tableNode) {
		return tableNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public String getTableIdFromTableNode(final IMutableNode<?> tableNode) {
		return getOriIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
	}
	
	//method
	public String getTableNameFromTableNode(final IMutableNode<?> tableNode) {
		return getOriNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();
	}
}
