//package declaration
package ch.nolix.system.nodedatabaserawdata.datawriter;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.nodedatabaserawdata.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
final class EntityNodeMapper {
	
	//method
	public Node createNodeFromRecordWithSaveStamp(
		final ITableInfo tableInfo,
		final IRecordDTO pRecord,
		final long saveStamp
	) {
		return
		Node.withHeaderAndChildNodes(
			SubNodeHeaderCatalogue.RECORD,
			createAttributesFromRecordWithSaveStamp(pRecord, saveStamp, tableInfo)
		);
	}
	
	//method
	private IContainer<Node> createAttributesFromRecordWithSaveStamp(
		final IRecordDTO pRecord,
		final long saveStamp,
		final ITableInfo tableInfo
	) {
		
		final var attributes = new Node[2 + tableInfo.getColumnInfos().getElementCount()];
		
		attributes[0] = createIdAttributeFrom(pRecord);
		attributes[1] = createSaveStampAttribute(saveStamp);
		
		for (final var cf : pRecord.getContentFields()) {
			
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
	private Node createIdAttributeFrom(final IRecordDTO pRecord) {
		return Node.withHeader(pRecord.getId());
	}
	
	//method
	private Node createSaveStampAttribute(final long saveStamp) {
		return Node.withHeader(saveStamp);
	}
}
