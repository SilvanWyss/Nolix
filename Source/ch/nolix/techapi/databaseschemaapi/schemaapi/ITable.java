//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Namable;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.generalskillapi.IFluentObject;

//interface
public interface ITable<
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<? super Object>
>
extends IFluentObject<T>, Namable<T> {
	
	//method declaration
	T addColumn(C column);
	
	//method
	default T addColumnWithHeaderAndParametrizedPropertyType(final String header, final PPT parametrizedPropertyType) {
		
		final var column = createColumnObject().setHeader(header).setParametrizedPropertyType(parametrizedPropertyType);
		addColumn(column);
		
		return asConcrete();
	}
	
	//method
	default boolean containsColumnWithHeader(final String header) {
		return getRefColumns().contains(c -> c.hasHeader(header));
	}
	
	//method declaration
	C createColumnObject();
	
	//method declaration
	void deleteColumn(C column);
	
	//method
	default void deleteColumnWithHeader(final String header) {
		deleteColumn(getRefColumnByHeader(header));
	}
	
	//method
	default int getColumnCount() {
		return getRefColumns().getElementCount();
	}
	
	//method
	default C getRefColumnByHeader(final String header) {
		return getRefColumns().getRefFirst(c -> c.hasHeader(header));
	}
	
	//method declarations
	IContainer<C> getRefColumns();
}
