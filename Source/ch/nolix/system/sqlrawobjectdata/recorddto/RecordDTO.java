//package declaration
package ch.nolix.system.sqlrawobjectdata.recorddto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IContentFieldDTO;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IRecordDTO;

//class
public final class RecordDTO implements IRecordDTO {
	
	//attributes
	private final String id;
	
	//multi-attribute
	private final IContainer<IContentFieldDTO> contentFields;
	
	//constructor
	public RecordDTO(final String id, final IContainer<IContentFieldDTO> contentFields) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (contentFields == null) {
			throw new ArgumentIsNullException("content fields");
		}
		
		this.id = id;
		this.contentFields = contentFields;
	}
	
	//method
	@Override
	public IContainer<IContentFieldDTO> getContentFields() {
		return contentFields;
	}
	
	//method
	@Override
	public String getId() {
		return id;
	}
}
