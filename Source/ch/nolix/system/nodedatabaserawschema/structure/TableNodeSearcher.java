//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public class TableNodeSearcher {
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public final IMutableNode<?> getOriColumnNodeFromTableNodeByColumnName(
		final IMutableNode<?> tableNode,
		final String columnName
	) {
		return
		getOriColumnNodesFromTableNode(tableNode).getOriFirst(
			csn -> columnNodeSearcher.getOriNameNodeFromColumnNode(csn).getOriSingleChildNode().hasHeader(columnName)
		);
	}
	
	//method
	public final IContainer<? extends IMutableNode<?>> getOriColumnNodesFromTableNode(final IMutableNode<?> tableNode) {
		return tableNode.getOriChildNodesWithHeader(SubNodeHeaderCatalogue.COLUMN);
	}
	
	//method
	public final IMutableNode<?> getOriIdNodeFromTableNode(final IMutableNode<?> tableNode) {
		return tableNode.getOriFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public final IMutableNode<?> getOriNameNodeFromTableNode(final IMutableNode<?> tableNode) {
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
