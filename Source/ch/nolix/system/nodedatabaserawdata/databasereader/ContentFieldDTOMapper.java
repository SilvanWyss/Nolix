//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.sqlrawdata.databasedto.LoadedContentFieldDTO;
import ch.nolix.system.sqlrawdata.databasereader.ValueMapper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldDTOMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public ILoadedContentFieldDTO createContentFieldDTOFromContentFieldNode(
		final IMutableNode<?> contentFieldNode,
		final IColumnInfo columnInfo
	) {
		
		if (contentFieldNode.containsChildNodes()) {
			return new LoadedContentFieldDTO(columnInfo.getColumnName());
		}
		
		if (!contentFieldNode.hasHeader()) {
			return new LoadedContentFieldDTO(columnInfo.getColumnName());
		}
		
		return
		new LoadedContentFieldDTO(
			columnInfo.getColumnName(),
			valueMapper.createValueFromString(contentFieldNode.getHeader(), columnInfo)
		);
	}
}
