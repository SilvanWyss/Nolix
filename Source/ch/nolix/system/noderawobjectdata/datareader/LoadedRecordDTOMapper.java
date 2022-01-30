//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.noderawobjectdata.structure.RecordNodeSearcher;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableDefinition;
import ch.nolix.system.sqlrawobjectdata.datadto.LoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

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
