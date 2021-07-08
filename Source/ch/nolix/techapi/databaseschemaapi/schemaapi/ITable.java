//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Namable;
import ch.nolix.common.container.IContainer;

//interface
public interface ITable<
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IBaseParametrizedPropertyType<? super Object>
>
extends Namable<T> {
	
	//method declaration
	T addColumn(C column);
	
	//method
	default boolean containsColumnWithHeader(final String header) {
		return getRefColumns().contains(c -> c.hasHeader(header));
	}
	
	//method declaration
	T createColumnWithHeaderAndParametrizedPropertyType(final String header, final PPT parametrizedPropertyType);
	
	//method declaration
	void deleteColumn(C column);
	
	//method
	default void deleteColumnByHeader(final String header) {
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
