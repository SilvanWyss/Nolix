//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.coreapi.containerapi.IContainer;

//class
public class TableNodeSearcher {
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public final BaseNode<?> getRefColumnNodeFromTableNodeByColumnName(
		final BaseNode<?> tableNode,
		final String columnName
	) {
		return
		getRefColumnNodesFromTableNode(tableNode).getRefFirst(
			csn -> columnNodeSearcher.getRefNameNodeFromColumnNode(csn).getRefSingleChildNode().hasHeader(columnName)
		);
	}
	
	//method
	public final IContainer<BaseNode<?>> getRefColumnNodesFromTableNode(final BaseNode<?> tableNode) {
		
		//TODO: Refactor this.
		return tableNode.getRefChildNodesWithHeader(SubNodeHeaderCatalogue.COLUMN).asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	public final BaseNode<?> getRefIdNodeFromTableNode(final BaseNode<?> tableNode) {
		return tableNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public final BaseNode<?> getRefNameNodeFromTableNode(final BaseNode<?> tableNode) {
		return tableNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public String getTableIdFromTableNode(final BaseNode<?> tableNode) {
		return getRefIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
	}
	
	//method
	public String getTableNameFromTableNode(final BaseNode<?> tableNode) {
		return getRefNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();
	}
}
