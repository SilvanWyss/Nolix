//package declaration
package ch.nolix.system.databaseSchemaAdapter;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.license.Feature;
import ch.nolix.nolixLicense.Nolix2020Classic;
import ch.nolix.nolixLicense.Nolix2020Ultimate;

//class
public final class DatabaseClassic extends Feature {
	
	//method
	@Override
	public IContainer<Class<?>> getAuthorizedLicenseTypes() {
		return LinkedList.withElements(Nolix2020Classic.class, Nolix2020Ultimate.class);
	}
}
