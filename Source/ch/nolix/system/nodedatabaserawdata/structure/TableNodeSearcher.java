//package declaration
package ch.nolix.system.nodedatabaserawdata.structure;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.FieldIndexCatalogue;

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
	public BaseNode getRefEntityNodeFromTableNodeOrNull(final BaseNode tableNode, final String id) {
		return
		tableNode.getRefFirstAttributeOrNull(
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
	public boolean tableNodeContainsRecordNodeWithGivenId(final BaseNode tableNode, final String id) {
		return
		tableNodeContainsRecordNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, FieldIndexCatalogue.ID_INDEX, id);
	}
	
	//method
	public boolean tableNodeContainsRecordNodeWhoseFieldAtGivenIndexContainsGivenValue(
		final BaseNode tableNode,
		final int valueIndex,
		final String value
	) {
		return
		tableNode.containsAttribute(
			(final BaseNode a) -> {
				
				if (!a.hasHeader(SubNodeHeaderCatalogue.RECORD)) {
					return false;
				}
				
				final var field = a.getRefAttributeAt(valueIndex);
				return (field.hasHeader(value) || field.containsAttributeWithHeader(value));
			}
		);
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
