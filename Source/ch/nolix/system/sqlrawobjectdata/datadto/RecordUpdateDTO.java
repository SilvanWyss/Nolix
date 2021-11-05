//package declaration
package ch.nolix.system.sqlrawobjectdata.datadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class RecordUpdateDTO implements IRecordUpdateDTO {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//multi-attribute
	private final IContainer<IContentFieldDTO> updatedContentFields;
	
	//constructor
	public RecordUpdateDTO(
		final String id,
		final String saveStamp,
		final IContainer<IContentFieldDTO> updatedContentFields
	) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (saveStamp == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.SAVE_STAMP);
		}
		
		if (updatedContentFields == null) {
			throw new ArgumentIsNullException("updated content fields");
		}
		
		this.id = id;
		this.saveStamp = saveStamp;
		this.updatedContentFields = updatedContentFields;
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
	public IContainer<IContentFieldDTO> getUpdatedContentFields() {
		return updatedContentFields;
	}
}
