//package declaration
package ch.nolix.system.sqlrawobjectdata.datadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;

//class
public final class RecordDeletionDTO implements IRecordDeletionDTO {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//constructor
	public RecordDeletionDTO(final String id, final String saveStamp) {
		
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
