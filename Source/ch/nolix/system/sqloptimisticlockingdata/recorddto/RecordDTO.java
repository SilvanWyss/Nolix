//package declaration
package ch.nolix.system.sqloptimisticlockingdata.recorddto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PluralLowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.IRecordDTO;

//class
public final class RecordDTO implements IRecordDTO {
	
	//attributes
	private final String id;
	private final String saveStamp;
	
	//multi-attribute
	private final IContainer<String> values;
	
	//constructor
	public RecordDTO(final String id, final String saveStamp, final IContainer<String> values) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (saveStamp == null) {
			throw new ArgumentIsNullException("save stamp");
		}
		
		if (values == null) {
			throw new ArgumentIsNullException(PluralLowerCaseCatalogue.VALUES);
		}
		
		this.id = id;
		this.saveStamp = saveStamp;
		this.values = values;
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
	public IContainer<String> getValues() {
		return values;
	}
}
