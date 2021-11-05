//package declaration
package ch.nolix.system.noderawobjectschema.structure;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.document.node.BaseNode;

//class
public class TableNodeSearcher {
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public final BaseNode getRefColumnNodeFromTableNodeByColumnHeader(
		final BaseNode tableNode,
		final String columnHeader
	) {
		return
		getRefColumnNodesFromTableNode(tableNode).getRefFirst(
			csn -> columnNodeSearcher.getRefHeaderNodeFromColumnNode(csn).getRefOneAttribute().hasHeader(columnHeader)
		);
	}
	
	//method
	public final IContainer<BaseNode> getRefColumnNodesFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefAttributes(SubNodeHeaderCatalogue.COLUMN).from(3);
	}
	
	//method
	public final BaseNode getRefNameNodeFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefFirstAttribute(SubNodeHeaderCatalogue.NAME);
	}
}
