//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;

//class
public abstract class BaseParametrizedBackReferenceType<
	IMPL,
	C extends IColumn<IMPL>
>
extends ParametrizedPropertyType<IMPL>
implements IBaseParametrizedBackReferenceType<IMPL, C>{
	
	//attribute
	private final C backReferencedColumn;
	
	//constructor
	public BaseParametrizedBackReferenceType(final C backReferencedColumn) {
		
		Validator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();
		
		this.backReferencedColumn = backReferencedColumn;
	}
	
	//method
	@Override
	public final C getBackReferencedColumn() {
		return backReferencedColumn;
	}
}
