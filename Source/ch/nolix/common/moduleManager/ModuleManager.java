//package declaration
package ch.nolix.common.moduleManager;

//Java import
import java.io.File;

import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A central controller manages modules.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 */
public class ModuleManager {
	
	//attribute
	private final String directory;
		
	//multi-attribute
	private final LinkedList<Module> modules = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new central controller with the given directory.
	 * The central controller will created the directoy if it does not exist.
	 * 
	 * @param directory
	 * @throws InvalidArgumentException if the given directory does not specify a directory on the local machine.
	 */
	public ModuleManager(final String directory) {
		
		//Checks if the given directory specifies a directory.
		Validator.suppose(directory).specifiesProbableDirectoryOnLocalMachine(directory);
			
		//Sets the directory of this central controller.
		this.directory = directory;
		
		final File file = new File(getDirectory());
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	//method
	/**
	 * @return the directory of this central controller.
	 */
	public final String getDirectory() {
		return directory;
	}
	
	//method
	/**
	 * @param name
	 * @return the module of this central central controller with the given name.
	 * @throws InvalidArgumentException if this central controller does not contain a module with the given code name.
	 */
	@SuppressWarnings("unchecked")
	public final <M extends Module> M getModuleByName(final String name) {
		return (M)(modules.getRefFirst(m -> m.hasName(name)));
	}
	
	//method
	/**
	 * Adds the given module to this central controller.
	 * 
	 * @param module
	 * @throws InvalidArgumentException
	 * if this central controller contains an other module with the same name as the given module.
	 */
	void addModule(final Module module) {
		
		final String name = module.getName();
		if (modules.contains(m -> m.hasName(name))) {
			throw new InvalidArgumentException(this, "contains other module with the name '" + name + "'.");
		}
		
		modules.addAtEnd(module);
	}
}
