//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.objectdataapi.dataapi.IColumn;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IParametrizedPropertyType;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//class
public final class Column extends ImmutableDatabaseObject implements IColumn<DataImplementation> {
	
	//attribute
	private final String header;
	
	//attribute
	private final ParametrizedPropertyType parametrizedPropertyType;
	
	//attribute
	private final Table<IEntity<DataImplementation>> parentTable;
	
	//constructor
	Column(
		final String header,
		final ParametrizedPropertyType parametrizedPropertyType,
		final Table<IEntity<DataImplementation>> parentTable
	) {
		
		Validator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		Validator.assertThat(parametrizedPropertyType).thatIsNamed(ParametrizedPropertyType.class).isNotNull();
		Validator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();
		
		this.header = header;
		this.parametrizedPropertyType = parametrizedPropertyType;
		this.parentTable = parentTable;
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	@Override
	public IParametrizedPropertyType<DataImplementation> getParametrizedPropertyType() {
		return parametrizedPropertyType;
	}
	
	//method
	@Override
	public ITable<DataImplementation, IEntity<DataImplementation>> getParentTable() {
		return parentTable;
	}
	
	//method
	@Override
	public boolean referencesInLocalData(final IEntity<DataImplementation> entity) {
		//TODO: Implement.
		return false;
	}
}
