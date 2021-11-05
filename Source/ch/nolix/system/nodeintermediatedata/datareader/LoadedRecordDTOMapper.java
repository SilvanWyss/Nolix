//package declaration
package ch.nolix.system.nodeintermediatedata.datareader;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.nodeintermediatedata.structure.RecordNodeSearcher;
import ch.nolix.system.nodeintermediatedata.tabledefinition.TableDefinition;
import ch.nolix.system.sqlintermediatedata.recorddto.LoadedRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IContentFieldDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ILoadedRecordDTO;

//class
public final class LoadedRecordDTOMapper {
	
	//static attributes
	private static final ContentFieldDTOMapper contentFieldDTOMapper = new ContentFieldDTOMapper();
	private static final RecordNodeSearcher recordNodeSearcher = new RecordNodeSearcher();
	
	//method
	public ILoadedRecordDTO createLoadedRecordDTOFromRecordNode(
		final BaseNode recordNode,
		final TableDefinition tableDefinition
	) {
		return
		new LoadedRecordDTO(
			getIdFromRecordNode(recordNode),
			getSaveStampFromRecordNode(recordNode),
			createContentFieldsFromRecordNode(recordNode, tableDefinition)
		);
	}
	
	//method
	private IContainer<IContentFieldDTO> createContentFieldsFromRecordNode(
		final BaseNode recordNode,
		final TableDefinition tableDefinition
	) {
		
		final var contentFields = new LinkedList<IContentFieldDTO>();
		var index = 1;
		for (final var ccd : tableDefinition.getContentColumnDefinitions()) {
			
			final var contentFieldNode = recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(recordNode, index);
			
			contentFields.addAtEnd(contentFieldDTOMapper.createContentFieldDTOFromContentFieldNode(contentFieldNode, ccd));
		}
		
		return contentFields;
	}
	
	//method
	private String getIdFromRecordNode(final BaseNode recordNode) {
		
		final var idNode = recordNodeSearcher.getRefIdNodeFromRecordNode(recordNode);
		
		return idNode.getOneAttributeHeader();
	}
	
	//method
	private String getSaveStampFromRecordNode(BaseNode recordNode) {
		
		final var saveStampNode = recordNodeSearcher.getRefSaveStampNodeFromRecordNode(recordNode);
		
		return saveStampNode.getOneAttributeHeader();
	}
}
