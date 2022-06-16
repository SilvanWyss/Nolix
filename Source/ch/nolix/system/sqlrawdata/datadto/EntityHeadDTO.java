//package declaration
package ch.nolix.system.sqlrawdata.datadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;

//class
public final class EntityHeadDTO implements IEntityHeadDTO {
	
	//attribute
	private final String id;
	
	//attribute
	private final String saveStamp;
	
	//constructor
	public EntityHeadDTO(final String id, final String saveStamp) {
		
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
