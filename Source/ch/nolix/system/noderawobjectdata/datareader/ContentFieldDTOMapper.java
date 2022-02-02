//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.sqlrawobjectdata.datadto.LoadedContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datareader.ValueMapper;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldDTOMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public ILoadedContentFieldDTO createContentFieldDTOFromContentFieldNode(
		final BaseNode contentFieldNode,
		final IColumnInfo columnInfo
	) {
		
		if (!contentFieldNode.containsAttributes()) {
			return new LoadedContentFieldDTO(columnInfo.getColumnName());
		}
		
		return
		new LoadedContentFieldDTO(
			columnInfo.getColumnName(),
			valueMapper.createValueFromString(contentFieldNode.getRefAttributes().toString(), columnInfo)
		);
	}
}
