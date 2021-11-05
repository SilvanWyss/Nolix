//package declaration
package ch.nolix.system.sqlrawobjectdata.recorddto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IContentFieldDTO;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.ILoadedRecordDTO;

//class
public final class LoadedRecordDTO implements ILoadedRecordDTO {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//multi-attribute
	private final IContainer<IContentFieldDTO> contentFields;
	
	//constructor
	public LoadedRecordDTO(
		final String id,
		final String saveStamp,
		final IContainer<IContentFieldDTO> contentFields
	) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (saveStamp == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.SAVE_STAMP);
		}
		
		if (contentFields == null) {
			throw new ArgumentIsNullException("content fields");
		}
		
		this.id = id;
		this.saveStamp = saveStamp;
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
	
	//method
	@Override
	public String getSaveStamp() {
		return saveStamp;
	}
}
