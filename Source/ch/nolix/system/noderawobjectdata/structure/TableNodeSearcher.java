//package declaration
package ch.nolix.system.noderawobjectdata.structure;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.noderawobjectdata.tabledefinition.FieldIndexCatalogue;

//class
public final class TableNodeSearcher {
	
	//method
	public BaseNode getRefRecordNodeFromTableNode(final BaseNode tableNode, final String id) {
		return
		tableNode.getRefFirstAttribute(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.RECORD)
			&& a.getRefAttributeAt(FieldIndexCatalogue.ID_INDEX).hasHeader(id)
		);
	}
	
	//method
	public IContainer<BaseNode> getRefRecordNodesFromTableNode(final BaseNode tableNode) {
		return tableNode.getRefAttributes(SubNodeHeaderCatalogue.RECORD);
	}
	
	//method
	public BaseNode removeAndGetRefRecordNodeFromTableNode(BaseNode tableNode, String id) {
		return
		tableNode.removeAndGetRefFirstAttribute(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.RECORD)
			&& a.getRefAttributeAt(FieldIndexCatalogue.ID_INDEX).hasHeader(id)
		);
	}
	
	//method
	public boolean tableNodeContainsRecordNodeWithId(final BaseNode tableNode, final String id) {
		return
		tableNodeContainsRecordNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, FieldIndexCatalogue.ID_INDEX, id);
	}
	
	//method
	public boolean tableNodeContainsRecordNodeWhoseFieldAtGivenIndexHasGivenHeader(
		final BaseNode tableNode,
		final int valueIndex,
		final String header
	) {
		return
		tableNode.containsAttribute(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.RECORD)
			&& a.getRefAttributeAt(valueIndex).hasHeader(header)
		);
	}
}
