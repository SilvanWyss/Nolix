//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValueEntry;

//class
public final class MultiValueEntry<V> implements IMultiValueEntry<DataImplementation, V> {
	
	//constant
	private static final DatabaseObjectHelper DATABASE_OBJECT_HELPER = new DatabaseObjectHelper();
	
	//static method
	public static <V2> MultiValueEntry<V2> loadedEntryForMultiValueAndValue(
		final IMultiValue<DataImplementation, V2> multiValue,
		final V2 value
	) {
		return new MultiValueEntry<>(multiValue, DatabaseObjectState.LOADED, value);
	}
	
	//static method
	public static <V2> MultiValueEntry<V2> newEntryForMultiValueAndValue(
		final IMultiValue<DataImplementation, V2> multiValue,
		final V2 value
	) {
		return new MultiValueEntry<>(multiValue, DatabaseObjectState.NEW, value);
	}
	
	//attribute
	private final IMultiValue<DataImplementation, V> parentMultiValue;
	
	//attribute
	private DatabaseObjectState state;
	
	//attribute
	private final V value;
	
	//constructor
	private MultiValueEntry(
		final IMultiValue<DataImplementation, V> parentMultiValue,
		final DatabaseObjectState initialState,
		final V value
	) {
		
		GlobalValidator.assertThat(parentMultiValue).thatIsNamed("parent MultiValue").isNotNull();
		GlobalValidator.assertThat(initialState).thatIsNamed("initial state").isNotNull();
		GlobalValidator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		this.parentMultiValue = parentMultiValue;
		state = initialState;
		this.value = value;
	}
	
	//method
	@Override
	public IMultiValue<DataImplementation, V> getRefParentMultiValue() {
		return parentMultiValue;
	}
	
	//method
	@Override
	public V getRefValue() {
		return value;
	}
	
	//method
	@Override
	public DatabaseObjectState getState() {
		return
		switch (getRefParentMultiValue().getState()) {
			case DELETED ->
				DatabaseObjectState.DELETED;
			case CLOSED ->
				DatabaseObjectState.CLOSED;
			default ->
				state;
		};
	}
	
	//method
	@Override
	public boolean isClosed() {
		return getRefParentMultiValue().isClosed();
	}
	
	//method
	@Override
	public boolean isDeleted() {
		return getRefParentMultiValue().isDeleted();
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		return getRefParentMultiValue().isLinkedWithRealDatabase();
	}
	
	//method
	void internalSetDeleted() {
		
		assertIsLoaded();
		
		state = DatabaseObjectState.DELETED;
	}
	
	//method
	private void assertIsLoaded() {
		DATABASE_OBJECT_HELPER.assertIsLoaded(this);
	}
}
