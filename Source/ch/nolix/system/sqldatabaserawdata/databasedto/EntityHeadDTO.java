//package declaration
package ch.nolix.system.sqldatabaserawdata.databasedto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;

//class
public record EntityHeadDTO(String id, String saveStamp) implements IEntityHeadDTO {
	
	//constructor
	public EntityHeadDTO(final String id, final String saveStamp) { //NOSONAR: This implementations checks the given arguments.
		
		if (id == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ID);
		}
		
		if (saveStamp == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.SAVE_STAMP);
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
