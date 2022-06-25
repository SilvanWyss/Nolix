//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.system.nodedatabaserawdata.structure.EntityNodeSearcher;
import ch.nolix.system.sqlrawdata.datadto.LoadedRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
public final class LoadedRecordDTOMapper {
	
	//static attributes
	private static final ContentFieldDTOMapper contentFieldDTOMapper = new ContentFieldDTOMapper();
	private static final EntityNodeSearcher entityNodeSearcher = new EntityNodeSearcher();
	
	//method
	public ILoadedRecordDTO createLoadedRecordDTOFromRecordNode(
		final BaseNode recordNode,
		final ITableInfo tableInfo
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
		final BaseNode entityNode,
		final ITableInfo tableInfo
	) {
		
		final var contentFields = new LinkedList<ILoadedContentFieldDTO>();
		for (final var ci : tableInfo.getColumnInfos()) {
			
			final var contentFieldNode = entityNode.getRefAttributeAt(ci.getColumnIndexOnEntityNode());
			
			contentFields.addAtEnd(contentFieldDTOMapper.createContentFieldDTOFromContentFieldNode(contentFieldNode, ci));
		}
		
		return contentFields;
	}
	
	//method
	private String getIdFromRecordNode(final BaseNode recordNode) {
		
		final var idNode = entityNodeSearcher.getRefIdNodeFromRecordNode(recordNode);
		
		return idNode.getSingleChildNodeHeader();
	}
	
	//method
	private String getSaveStampFromRecordNode(BaseNode recordNode) {
		
		final var saveStampNode = entityNodeSearcher.getRefSaveStampNodeFromRecordNode(recordNode);
		
		return saveStampNode.getSingleChildNodeHeader();
	}
}
