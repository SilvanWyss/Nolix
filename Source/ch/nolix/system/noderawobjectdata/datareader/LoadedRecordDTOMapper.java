//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.noderawobjectdata.structure.EntityNodeSearcher;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
import ch.nolix.system.sqlrawobjectdata.datadto.LoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

//class
public final class LoadedRecordDTOMapper {
	
	//static attributes
	private static final ContentFieldDTOMapper contentFieldDTOMapper = new ContentFieldDTOMapper();
	private static final EntityNodeSearcher recordNodeSearcher = new EntityNodeSearcher();
	
	//method
	public ILoadedRecordDTO createLoadedRecordDTOFromRecordNode(
		final BaseNode recordNode,
		final TableInfo tableInfo
	) {
		return
		new LoadedRecordDTO(
			getIdFromRecordNode(recordNode),
			getSaveStampFromRecordNode(recordNode),
			createContentFieldsFromRecordNode(recordNode, tableInfo)
		);
	}
	
	//method
	private IContainer<ILoadedContentFieldDTO> createContentFieldsFromRecordNode(
		final BaseNode recordNode,
		final TableInfo tableInfo
	) {
		
		final var contentFields = new LinkedList<ILoadedContentFieldDTO>();
		var index = 1;
		for (final var ccd : tableInfo.getColumnInfos()) {
			
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
