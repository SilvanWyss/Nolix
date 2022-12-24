//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.objectdatabase.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class Column extends ImmutableDatabaseObject implements IColumn<DataImplementation> {
	
	//static method
	static Column withNameAndIdAndParametrizedPropertyTypeAndParentTable(
		final String name,
		final String id,
		final IParametrizedPropertyType<DataImplementation> parametrizedPropertyType,
		final Table<IEntity<DataImplementation>> parentTable
	) {
		return new Column(name, id, parametrizedPropertyType, parentTable);
	}
	
	//attribute
	private final String name;
	
	//attribute
	private final String id;
	
	//attribute
	private final IParametrizedPropertyType<DataImplementation> parametrizedPropertyType;
	
	//attribute
	private final Table<IEntity<DataImplementation>> parentTable;
	
	//constructor
	private Column(
		final String name,
		final String id,
		final IParametrizedPropertyType<DataImplementation> parametrizedPropertyType,
		final Table<IEntity<DataImplementation>> parentTable
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		GlobalValidator.assertThat(parametrizedPropertyType).thatIsNamed(ParametrizedPropertyType.class).isNotNull();
		GlobalValidator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();
		
		this.name = name;
		this.id = id;
		this.parametrizedPropertyType = parametrizedPropertyType;
		this.parentTable = parentTable;
	}
	
	//method
	@Override
	public String getId() {
		return id;
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
	public ITable<DataImplementation, IEntity<DataImplementation>> getRefParentTable() {
		return parentTable;
	}
	
	//method
	@Override
	public boolean technicalContainsGivenValueInPersistedData(final String value) {
		return
		getRefDataAndSchemaAdapter().tableContainsEntityWithGivenValueAtGivenColumn(
			getRefParentTable().getName(),
			getName(),
			value
		);
	}
	
	//method
	private IDataAndSchemaAdapter getRefDataAndSchemaAdapter() {
		return parentTable.internalGetRefDataAndSchemaAdapter();
	}
}
