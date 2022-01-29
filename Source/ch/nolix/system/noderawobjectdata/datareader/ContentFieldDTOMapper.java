//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datareader.ValueMapper;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldDTOMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public IContentFieldDTO createContentFieldDTOFromContentFieldNode(
		final BaseNode contentFieldNode,
		final IColumnInfo columnInfo
	) {
		
		if (!contentFieldNode.containsAttributes()) {
			return new ContentFieldDTO(columnInfo.getColumnName());
		}
		
		return
		new ContentFieldDTO(
			columnInfo.getColumnName(),
			valueMapper.createValueFromString(contentFieldNode.getRefAttributes().toString(), columnInfo)
		);
	}
}
