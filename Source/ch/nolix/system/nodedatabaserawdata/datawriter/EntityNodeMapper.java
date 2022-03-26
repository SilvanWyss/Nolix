//package declaration
package ch.nolix.system.nodedatabaserawdata.datawriter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.system.nodedatabaserawdata.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.TableInfo;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;

//class
final class EntityNodeMapper {
	
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
			
			final var columnInfo =  tableInfo.getColumnInfoByColumnName(cf.getColumnName());
			final var index = columnInfo.getColumnIndexOnEntityNode() - 1;
			
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
