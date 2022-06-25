//package declaration
package ch.nolix.system.nodedatabaserawdata.datawriter;

import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.system.nodedatabaserawdata.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
final class EntityNodeMapper {
	
	//method
	public Node createNodeFromRecordWithSaveStamp(
		final ITableInfo tableInfo,
		final IRecordDTO pRecord,
		final long saveStamp
	) {
		return
		Node.withHeaderAndAttributes(
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
			final var index = columnInfo.getColumnIndexOnEntityNode();
			
			final var string = cf.getValueAsStringOrNull();
			if (string == null) {
				attributes[index] = new Node();
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
