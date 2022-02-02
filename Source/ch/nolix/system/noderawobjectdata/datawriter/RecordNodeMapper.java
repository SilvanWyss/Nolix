//package declaration
package ch.nolix.system.noderawobjectdata.datawriter;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.system.noderawobjectdata.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;

//class
final class RecordNodeMapper {
	
	//method
	public Node createNodeFromRecordWithSaveStamp(
		final TableInfo tableInfo,
		final IRecordDTO record,
		final long saveStamp
	) {
		return
		Node.withHeaderAndAttributes(
			SubNodeHeaderCatalogue.RECORD,
			createAttributesFromRecordWithSaveStamp(record, saveStamp, tableInfo)
		);
	}
	
	//method
	private IContainer<Node> createAttributesFromRecordWithSaveStamp(
		final IRecordDTO record,
		final long saveStamp,
		final TableInfo tableInfo
	) {
		
		final var attributes = new Node[2 + tableInfo.getColumnCount()];
		
		attributes[0] = createIdAttributeFrom(record);
		attributes[1] = createSaveStampAttribute(saveStamp);
		
		for (final var cf : record.getContentFields()) {
			
			final var index = 1 + tableInfo.getIndexOfColumnByColumnName(cf.getColumnName());
			
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
	private Node createIdAttributeFrom(final IRecordDTO record) {
		return Node.withHeader(record.getId());
	}
	
	//method
	private Node createSaveStampAttribute(final long saveStamp) {
		return Node.withHeader(saveStamp);
	}
}
