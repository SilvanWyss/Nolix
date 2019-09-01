//package declaration
package ch.nolix.common.moduleManager;

//Java import
import java.io.File;

import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.validator.Validator;

//abstract class
/**
 * A module is a level 3 controller that has:
 * -a name
 * -belongs to a central controller
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 80
 */
public abstract class Module
implements Named {
	
	//attributes
	private ModuleManager moduleManager;
	
	//constructor
	/**
	 * Creates a new module that belongs to the given central controller.
	 * 
	 * @param moduleManager
	 * @throws NullArgumentException if the given central controller is null.
	 * @throws InvalidArgumentException
	 * if the given central controller contains already a module with the same name as this module.
	 */
	public Module(final ModuleManager moduleManager) {
		
		//Checks if the given central controller is not null.
		Validator.suppose(moduleManager).isOfType(ModuleManager.class);
		
		//Sets the central controller this module belongs to.
		this.moduleManager = moduleManager;
		
		//Adds this module to the given central controller.
		moduleManager.addModule(this);
	}
	
	//method
	/**
	 * @return the directory of this module.
	 */
	public final String getDirectory() {
		
		final String directory = moduleManager.getDirectory() + "/" + getName();
		
		Validator.suppose(directory).specifiesProbableDirectoryOnLocalMachine(directory);
		
		final File file = new File(directory);
		if (!file.exists()) {
			file.mkdir();
		}
		
		return directory;
	}
	
	//method
	/**
	 * @return the name of this module.
	 */
	@Override
	public final String getName() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @param name
	 * @return the module with the given name of the central central controller this module belongs to.
	 * @throws InvalidArgumentException
	 * if the central controller this model belongs to does not contain a module with the given code name.
	 */
	protected final <M extends Module> M getRefModuleByName(final String name) {
		return moduleManager.getModuleByName(name);
	}
}
