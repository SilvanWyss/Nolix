//package declaration
package ch.nolix.system.nodedatabaserawdata.databasewriter;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.nodedatabaserawdata.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
final class EntityNodeMapper {
	
	//method
	public Node createNodeFromEntityWithSaveStamp(
		final ITableInfo tableInfo,
		final INewEntityDTO newEntity,
		final long saveStamp
	) {
		return
		Node.withHeaderAndChildNodes(
			SubNodeHeaderCatalogue.ENTITY,
			createAttributesFromNewEntityWithSaveStamp(newEntity, saveStamp, tableInfo)
		);
	}
	
	//method
	private IContainer<Node> createAttributesFromNewEntityWithSaveStamp(
		final INewEntityDTO newEntity,
		final long saveStamp,
		final ITableInfo tableInfo
	) {
		
		final var attributes = new Node[2 + tableInfo.getColumnInfos().getElementCount()];
		
		attributes[0] = createIdAttributeFrom(newEntity);
		attributes[1] = createSaveStampAttribute(saveStamp);
		
		for (final var cf : newEntity.getContentFields()) {
			
			final var columnInfo =  tableInfo.getColumnInfoByColumnName(cf.getColumnName());
			final var index = columnInfo.getColumnIndexOnEntityNode() - 1;
			
			final var string = cf.getValueAsStringOrNull();
			if (string == null) {
				attributes[index] = Node.EMPTY_NODE;
			} else {
				attributes[index] =	Node.withHeader(string);
			}
		}
		
		return ReadContainer.forArray(attributes);
	}
	
	//method
	private Node createIdAttributeFrom(final INewEntityDTO newEntity) {
		return Node.withHeader(newEntity.getId());
	}
	
	//method
	private Node createSaveStampAttribute(final long saveStamp) {
		return Node.withHeader(saveStamp);
	}
}
