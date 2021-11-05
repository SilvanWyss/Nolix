//package declaration
package ch.nolix.system.sqlintermediatedata.datareader;

//own imports
import ch.nolix.system.sqlintermediatedata.recorddto.ContentFieldDTO;
import ch.nolix.system.sqlintermediatedata.sqlapi.IColumnDefinition;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IContentFieldDTO;

//class
public final class ContentFieldMapper {
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//method
	public IContentFieldDTO createContentFieldFromString(
		final String string,
		final IColumnDefinition contentColumnDefinition
	) {
		return
		new ContentFieldDTO(
			contentColumnDefinition.getColumnHeader(),
			valueMapper.createValueFromString(string, contentColumnDefinition)
		);
	}
}
