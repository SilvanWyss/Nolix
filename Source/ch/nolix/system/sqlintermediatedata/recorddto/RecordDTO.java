//package declaration
package ch.nolix.system.sqlintermediatedata.recorddto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IContentFieldDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;

//class
public final class RecordDTO implements IRecordDTO {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//multi-attribute
	private final IContainer<IContentFieldDTO> contentFields;
	
	//constructor
	public RecordDTO(
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
	public String getId() {
		return id;
	}
	
	//method
	@Override
	public String getSaveStamp() {
		return saveStamp;
	}
	
	//method
	@Override
	public IContainer<IContentFieldDTO> getContentFields() {
		return contentFields;
	}
}
