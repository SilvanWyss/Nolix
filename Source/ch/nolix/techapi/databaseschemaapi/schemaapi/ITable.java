//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Namable;
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.Deletable;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ITable<
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<?>
> extends Deletable, IDatabaseObject, Namable<T> {
	
	//method declaration
	T addColumn(C column);
	
	//method declaration
	boolean belongsToDatabase();
	
	//method declaration
	T createColumnWithHeaderAndParametrizedPropertyType(final String header, final PPT parametrizedPropertyType);
	
	//method declaration
	IFlatTableDTO getFlatDTO();
	
	//method declaration
	IDatabase<?, ?, ?, ?> getParentDatabase();
	
	//method declarations
	IContainer<C> getRefColumns();
	
	//method declaration
	ITableDTO toDTO();
}
