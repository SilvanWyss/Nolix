//package declaration
package ch.nolix.system.sqlrawobjectdata.datadto;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;

//class
public final class RecordHeadDTO implements IRecordDeletionDTO {
	
	//attribute
	private final String id;
	
	//attribute
	private final String saveStamp;
	
	//constructor
	public RecordHeadDTO(final String id, final String saveStamp) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (saveStamp == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.SAVE_STAMP);
		}
		
		this.id = id;
		this.saveStamp = saveStamp;
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
