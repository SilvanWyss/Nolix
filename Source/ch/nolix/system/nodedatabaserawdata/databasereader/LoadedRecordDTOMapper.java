//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.structure.EntityNodeSearcher;
import ch.nolix.system.sqlrawdata.databasedto.LoadedRecordDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class LoadedRecordDTOMapper {
	
	//static attributes
	private static final ContentFieldDTOMapper contentFieldDTOMapper = new ContentFieldDTOMapper();
	private static final EntityNodeSearcher entityNodeSearcher = new EntityNodeSearcher();
	
	//method
	public ILoadedEntityDTO createLoadedRecordDTOFromRecordNode(
		final IMutableNode<?> recordNode,
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
		final IMutableNode<?> entityNode,
		final ITableInfo tableInfo
	) {
		
		final var contentFields = new LinkedList<ILoadedContentFieldDTO>();
		for (final var ci : tableInfo.getColumnInfos()) {
			
			final var contentFieldNode = entityNode.getRefChildNodeAt1BasedIndex(ci.getColumnIndexOnEntityNode());
			
			contentFields.addAtEnd(contentFieldDTOMapper.createContentFieldDTOFromContentFieldNode(contentFieldNode, ci));
		}
		
		return contentFields;
	}
	
	//method
	private String getIdFromRecordNode(final IMutableNode<?> recordNode) {
		
		final var idNode = entityNodeSearcher.getRefIdNodeFromRecordNode(recordNode);
		
		return idNode.getHeader();
	}
	
	//method
	private String getSaveStampFromRecordNode(IMutableNode<?> recordNode) {
		
		final var saveStampNode = entityNodeSearcher.getRefSaveStampNodeFromRecordNode(recordNode);
		
		return saveStampNode.getHeader();
	}
}
