//package declaration
package ch.nolix.system.database.databaseschemaadapter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.license.Feature;
import ch.nolix.nolixlicense.Nolix2020Classic;
import ch.nolix.nolixlicense.Nolix2020Ultimate;

//class
public final class DatabaseClassic extends Feature {
	
	//method
	@Override
	public IContainer<Class<?>> getAuthorizedLicenseTypes() {
		return LinkedList.withElements(Nolix2020Classic.class, Nolix2020Ultimate.class);
	}
}
