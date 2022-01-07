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
		return tableNode.getRefAttributes(SubNodeHeaderCatalogue.COLUMN).from(3);
	}
	
	//method
	public final BaseNode getRefNameNodeFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefFirstAttribute(SubNodeHeaderCatalogue.NAME);
	}
}
