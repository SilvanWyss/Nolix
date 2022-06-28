//package declaration
package ch.nolix.system.nodedatabaserawschema.structure;

import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public class TableNodeSearcher {
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//method
	public final IMutableNode<?> getRefColumnNodeFromTableNodeByColumnName(
		final IMutableNode<?> tableNode,
		final String columnName
	) {
		return
		getRefColumnNodesFromTableNode(tableNode).getRefFirst(
			csn -> columnNodeSearcher.getRefNameNodeFromColumnNode(csn).getRefSingleChildNode().hasHeader(columnName)
		);
	}
	
	//method
	public final IContainer<IMutableNode<?>> getRefColumnNodesFromTableNode(final IMutableNode<?> tableNode) {
		
		//TODO: Refactor this.
		return tableNode.getRefChildNodesWithHeader(SubNodeHeaderCatalogue.COLUMN).asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	public final IMutableNode<?> getRefIdNodeFromTableNode(final IMutableNode<?> tableNode) {
		return tableNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.ID);
	}
	
	//method
	public final IMutableNode<?> getRefNameNodeFromTableNode(final INode<?> tableNode) {
		
		//TODO: Refactor this.
		return (IMutableNode<?>)tableNode.getRefFirstChildNodeWithHeader(SubNodeHeaderCatalogue.NAME);
	}
	
	//method
	public String getTableIdFromTableNode(final IMutableNode<?> tableNode) {
		return getRefIdNodeFromTableNode(tableNode).getSingleChildNodeHeader();
	}
	
	//method
	public String getTableNameFromTableNode(final IMutableNode<?> tableNode) {
		return getRefNameNodeFromTableNode(tableNode).getSingleChildNodeHeader();
	}
}
