//package declaration
package ch.nolix.system.noderawobjectschema.structure;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.node.BaseNode;

//class
public class TableNodeSearcher {
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public final BaseNode getRefColumnNodeFromTableNodeByColumnName(
		final BaseNode tableNode,
		final String columnName
	) {
		return
		getRefColumnNodesFromTableNode(tableNode).getRefFirst(
			csn -> columnNodeSearcher.getRefNameNodeFromColumnNode(csn).getRefOneAttribute().hasHeader(columnName)
		);
	}
	
	//method
	public final IContainer<BaseNode> getRefColumnNodesFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefAttributes(SubNodeHeaderCatalogue.COLUMN);
	}
	
	//method
	public final BaseNode getRefIdNodeFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefFirstAttribute(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public final BaseNode getRefNameNodeFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefFirstAttribute(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public String getTableIdFromTableNode(final BaseNode tableNode) {
		return getRefIdNodeFromTableNode(tableNode).getOneAttributeHeader();
	}
	
	//method
	public String getTableNameFromTableNode(final BaseNode tableNode) {
		return getRefNameNodeFromTableNode(tableNode).getOneAttributeHeader();
	}
}
