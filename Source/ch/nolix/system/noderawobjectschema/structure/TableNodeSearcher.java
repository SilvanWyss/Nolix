//package declaration
package ch.nolix.system.noderawobjectschema.structure;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.document.node.BaseNode;

//class
public final class TableNodeSearcher {
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public BaseNode getColumnNodeFromTableNode(final BaseNode tableNode, final String columnHeader) {
		return
		getColumnNodesFromTableNode(tableNode).getRefFirst(
			csn -> columnNodeSearcher.getHeaderNodeFromColumnNode(csn).getRefOneAttribute().hasHeader(columnHeader)
		);
	}
	
	//method
	public IContainer<BaseNode> getColumnNodesFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefAttributes(SubNodeHeaderCatalogue.COLUMN).from(3);
	}
	
	//method
	public BaseNode getNameNodeFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefFirstAttribute(SubNodeHeaderCatalogue.NAME);
	}
}
