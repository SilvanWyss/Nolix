//package declaration
package ch.nolix.system.nodeintermediatedata.datawriter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.document.node.Node;
import ch.nolix.system.nodeintermediatedata.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.nodeintermediatedata.tabledefinition.TableDefinition;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;

//class
final class RecordNodeMapper {
	
	//method
	public Node createNodeFromRecordWithSaveStamp(
		final TableDefinition tableDefinition,
		final IRecordDTO record,
		final long saveStamp
	) {
		return
		Node.withHeaderAndAttributes(
			SubNodeHeaderCatalogue.RECORD,
			createAttributesFromRecordWithSaveStamp(record, saveStamp, tableDefinition)
		);
	}
	
	//method
	private IContainer<Node> createAttributesFromRecordWithSaveStamp(
		final IRecordDTO record,
		final long saveStamp,
		final TableDefinition tableDefinition
	) {
		
		final var attributes = new Node[2 + tableDefinition.getContentColumnCount()];
		
		attributes[0] = createIdAttributeFrom(record);
		attributes[1] = createSaveStampAttribute(saveStamp);
		
		for (final var cf : record.getContentFields()) {
			
			final var index = 1 + tableDefinition.getIndexOfContentColumnWithHeader(cf.getColumnHeader());
			
			final var value = cf.getValueOrNull();
			if (value == null) {
				attributes[index] = new Node();
			} else {
				attributes[index] =	Node.withHeader(value);
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
