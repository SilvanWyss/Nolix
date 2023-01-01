//package declaration
package ch.nolix.system.nodedatabaserawdata.structure;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.FieldIndexCatalogue;

//class
public final class TableNodeSearcher {
	
	//method
	public IMutableNode<?> getRefEntityNodeFromTableNode(final IMutableNode<?> tableNode, final String id) {
		return
		tableNode.getRefFirstChildNodeThat(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
			&& a.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id)
		);
	}
	
	//method
	public IMutableNode<?> getRefEntityNodeFromTableNodeOrNull(final IMutableNode<?> tableNode, final String id) {
		return
		tableNode.getRefFirstChildNodeThatOrNull(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
			&& a.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id)
		);
	}
	
	//method
	public IContainer<? extends IMutableNode<?>> getRefEntityNodesFromTableNode(final IMutableNode<?> tableNode) {
		return tableNode.getRefChildNodesWithHeader(SubNodeHeaderCatalogue.ENTITY);
	}
	
	//method
	public IMutableNode<?> removeAndGetRefEntityNodeFromTableNode(IMutableNode<?> tableNode, String id) {
		return
		tableNode.removeAndGetRefFirstChildNodeThat(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
			&& a.getRefChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id)
		);
	}
	
	//method
	public boolean tableNodeContainsEntityNodeWithGivenId(final IMutableNode<?> tableNode, final String id) {
		return
		tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, FieldIndexCatalogue.ID_INDEX, id);
	}
	
	//method
	public boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
		final IMutableNode<?> tableNode,
		final int valueIndex,
		final String value
	) {
		return
		tableNode.containsChildNodeThat(
			(final var a) -> {
				
				if (!a.hasHeader(SubNodeHeaderCatalogue.ENTITY)) {
					return false;
				}
				
				final var field = a.getRefChildNodeAt1BasedIndex(valueIndex);
				return (field.hasHeader(value) || field.containsChildNodeWithHeader(value));
			}
		);
	}
	
	//method
	public boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(
		final IMutableNode<?> tableNode,
		final int valueIndex,
		final String header
	) {
		return
		tableNode.containsChildNodeThat(
			a ->
			a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
			&& a.getRefChildNodeAt1BasedIndex(valueIndex).hasHeader(header)
		);
	}
}
