//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public final class Column extends ImmutableDatabaseObject implements IColumn<DataImplementation> {
	
	//attribute
	private final String name;
	
	//attribute
	private final ParametrizedPropertyType parametrizedPropertyType;
	
	//attribute
	private final Table<IEntity<DataImplementation>> parentTable;
	
	//constructor
	Column(
		final String name,
		final ParametrizedPropertyType parametrizedPropertyType,
		final Table<IEntity<DataImplementation>> parentTable
	) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(parametrizedPropertyType).thatIsNamed(ParametrizedPropertyType.class).isNotNull();
		Validator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();
		
		this.name = name;
		this.parametrizedPropertyType = parametrizedPropertyType;
		this.parentTable = parentTable;
	}
	
	//method
	@Override
	public String getName() {
		return name;
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
}
