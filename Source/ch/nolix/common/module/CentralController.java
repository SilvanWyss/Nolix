/*
 * file:	ModulePool.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	90
 */

//package declaration
package ch.nolix.common.module;

//Java import
import java.io.File;

//own import
import ch.nolix.common.container.List;
import ch.nolix.common.util.Validator;

//class
/**
 * A central controller is a level 2 controller that:
 * -contains modules
 * -has a directory
 */
public class CentralController {
	
	//attribute
	private final String directory;
		
	//multiple attribute
	private final List<Module> modules = new List<Module>();
	
	//constructor
	/**
	 * Creates new central controller with the givenc directory
	 * 
	 * @param directory
	 * @throws Exception if:
	 * -the given directory is null
	 * -the given directory is a file
	 */
	public CentralController(final String directory) {
		
		Validator.throwExceptionIfStringIsNullOrEmpty("directory", directory);
			
		this.directory = directory;
		
		File file = new File(getDirectory());
		if (file.exists()) {
			if (!file.isDirectory()) {
				throw new RuntimeException("The directory '" + directory + "' is a file.");
			}
		}
		else {
			file.mkdir();
		}
	}
	
	//method
	/**
	 * Adds the given module to this central controller.
	 * 
	 * @param module
	 * @return this central controller
	 * @throws Exception if this central controller already contains a module with the same code name as the given module
	 */
	public final CentralController addModule(Module module) {
				
		String codeName = module.getCodeName();
		if (modules.contains(m -> m.hasCodeName(codeName))) {
			throw new RuntimeException("Module pool already contains a module with the code name '" + codeName + "'.");
		}
		
		module.setCentralController(this);
		modules.addAtEnd(module);
		
		return this;
	}
	
	//method
	/**
	 * @return the directory of this central controller
	 */
	public final String getDirectory() {
		return directory;
	}
	
	//method
	/**
	 * @param codeName
	 * @return the module of this central central controller with the given code name
	 * @throws Exception if this central controller contains no module with the given code name
	 */
	@SuppressWarnings("unchecked")
	public final <M extends Module> M getRefModuleByCodeName(String codeName) {
		return (M)(modules.getRefFirst(m -> m.hasCodeName(codeName)));
	}
}
