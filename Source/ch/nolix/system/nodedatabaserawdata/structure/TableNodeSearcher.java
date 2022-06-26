//package declaration
package ch.nolix.system.nodedatabaserawdata.structure;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.FieldIndexCatalogue;

//class
public final class TableNodeSearcher {
	
	//method
	public BaseNode<?> getRefRecordNodeFromTableNode(final BaseNode<?> tableNode, final String id) {
		return
		tableNode.getRefFirstChildNodeThat(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.RECORD)
			&& a.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id)
		);
	}
	
	//method
	public BaseNode<?> getRefEntityNodeFromTableNodeOrNull(final BaseNode<?> tableNode, final String id) {
		return
		tableNode.getRefFirstChildNodeThatOrNull(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.RECORD)
			&& a.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id)
		);
	}
	
	//method
	public IContainer<BaseNode<?>> getRefRecordNodesFromTableNode(final BaseNode<?> tableNode) {
		return tableNode.getRefChildNodesWithHeader(SubNodeHeaderCatalogue.RECORD);
	}
	
	//method
	public BaseNode<?> removeAndGetRefRecordNodeFromTableNode(BaseNode<?> tableNode, String id) {
		return
		tableNode.removeAndGetRefFirstChildNodeThat(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.RECORD)
			&& a.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id)
		);
	}
	
	//method
	public boolean tableNodeContainsRecordNodeWithGivenId(final BaseNode<?> tableNode, final String id) {
		return
		tableNodeContainsRecordNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, FieldIndexCatalogue.ID_INDEX, id);
	}
	
	//method
	public boolean tableNodeContainsRecordNodeWhoseFieldAtGivenIndexContainsGivenValue(
		final BaseNode<?> tableNode,
		final int valueIndex,
		final String value
	) {
		return
		tableNode.containsChildNodeThat(
			(final BaseNode<?> a) -> {
				
				if (!a.hasHeader(SubNodeHeaderCatalogue.RECORD)) {
					return false;
				}
				
				final var field = a.getRefChildNodeAt1BasedIndex(valueIndex);
				return (field.hasHeader(value) || field.containsChildNodeWithHeader(value));
			}
		);
	}
	
	//method
	public boolean tableNodeContainsRecordNodeWhoseFieldAtGivenIndexHasGivenHeader(
		final BaseNode<?> tableNode,
		final int valueIndex,
		final String header
	) {
		return
		tableNode.containsChildNodeThat(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.RECORD)
			&& a.getRefChildNodeAt1BasedIndex(valueIndex).hasHeader(header)
		);
	}
}
