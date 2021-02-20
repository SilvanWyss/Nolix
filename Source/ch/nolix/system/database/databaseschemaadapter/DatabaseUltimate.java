//package declaration
package ch.nolix.system.database.databaseschemaadapter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.license.Feature;
import ch.nolix.nolixlicense.Nolix2020Ultimate;

//class
public final class DatabaseUltimate extends Feature {
	
	//method
	@Override
	public IContainer<Class<?>> getAuthorizedLicenseTypes() {
		return LinkedList.withElements(Nolix2020Ultimate.class);
	}
}
