//package declaration
package ch.nolix.core.centralController;

//Java import
import java.io.File;

//own imports
import ch.nolix.core.controllerInterfaces.ILevel3Controller;
import ch.nolix.core.interfaces.Named;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A module is a level 3 controller that has:
 *  -a name
 *  -belongs to a central controller
 *  
 *  @author Silvan Wyss
 *  @month 2016-04
 *  @lines 80
 */
public abstract class Module
implements Named, ILevel3Controller {
	
	//attributes
	private CentralController centralController;
	
	//constructor
	/**
	 * Creates new module that belongs to the given central controller.
	 * 
	 * @param centralController
	 * @throws NullArgumentException if the given central controller is null.
	 * @throws InvalidArgumentException
	 * if the given central controller contains already a module with the same name as this module.
	 */
	public Module(final CentralController centralController) {
		
		//Checks if the given central controller is not null.
		Validator.supposeThat(centralController).thatIsInstanceOf(CentralController.class).isNotNull();
		
		//Sets the central controller this module belongs to.
		this.centralController = centralController;
		
		//Adds this module to the given central controller.
		centralController.addModule(this);
	}
	
	//method
	/**
	 * @param name
	 * @return the module with the given name of the central central controller this module belongs to.
	 * @throws InvalidArgumentException
	 * if the central controller this model belongs to contains no module with the given code name.
	 */
	public final <M extends Module> M getModuleByName(final String name) {
		return centralController.getModuleByName(name);
	}
	
	//method
	/**
	 * @return the name of this module.
	 */
	public final String getName() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return the directory of this module.
	 * @throws Exception if this module has no directory
	 */
	protected final String getDirectory() {	
		
		final String directory = centralController.getDirectory() + "/" + getName();
		
		//TODO: Add specifiesDirectoryOnLocalMachine method to validator.
		//Validator.supposeThat(directory).specifiesProbableDirectoryOnLocalMachine(directory);
		
		final File file = new File(directory);
		if (!file.exists()) {
			file.mkdir();
		}
		
		return directory;
	}
}
