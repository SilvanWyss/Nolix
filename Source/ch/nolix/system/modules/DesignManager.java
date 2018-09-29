//package declaration
package ch.nolix.system.modules;

//Java import
import java.io.File;

import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.moduleManager.Module;
import ch.nolix.core.moduleManager.ModuleManager;
import ch.nolix.element.configuration.StandardConfiguration;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 150
 */
public final class DesignManager extends Module {
	
	//request
	public static final String DESIGN_NAMES_REQUEST = "DesignNames";
	
	//constructor
	/**
	 * Creates a new design manager with the given central controller.
	 * 
	 * @param moduleManager
	 * @throws NullArgumentException if the given central controller is not an instance.
	 */
	public DesignManager(final ModuleManager moduleManager) {
		
		//Calls constructor of the base class.
		super(moduleManager);
	}

	//method
	/**
	 * Adds the given design to this design manager.
	 * 
	 * @param design
	 * @return this design manager.
	 * @throws UnexistingAttributeException if the given design has no name.
	 * @throws InvalidArgumentException
	 * if this design manager contains already a design with the same name the given design.
	 */
	public final DesignManager addDesign(final StandardConfiguration design) {
		
		//Extracts the name of the given design.
		final String name = design.getName();
		
		//Checks if this design manager does not contain a design with the name of the given name.
		if (containsDesign(name)) {
			throw new InvalidArgumentException(
				new ArgumentName(name),
				new Argument(design),
				new ErrorPredicate(
					"is invalid because the design manager contains already a design with the name '"
					+ name
					+ "'."
				)
			);
		}
		
		design.saveAsTo(StandardConfiguration.TYPE_NAME, getDirectory() + "/" + name + ".spec");
		
		return this;
	}
	
	//method
	/**
	 * Adds the given design to this design manager
	 * if this design manager does not contain a design with the name of the given design.
	 * 
	 * @param design
	 * @return this design manager.
	 * @throws UnexistingAttributeException if the given design has no name.
	 */
	public final DesignManager addDesignIfPossible(final StandardConfiguration design) {
		
		//Extracts the name of the given design.
		final String name = design.getName();
		
		//Handles the case that this design manager
		//does not contain a design with the name of the given design.
		if (!containsDesign(name)) {
			addDesign(design);
		}
		
		return this;
	}
	
	//method
	/**
	 * @param name
	 * @return true if this design manager contains a design with the given name.
	 */
	public boolean containsDesign(final String name) {
		try {
			getDesignByName(name);
			return true;
		}
		catch (final Exception e) {
			return false;
		}
	}
	
	//method
	/**
	 * @param name
	 * @return the design with the given name from this design manager
	 * @throws UnexistingAttributeException
	 * if this design manager contains no design with the given name.
	 */
	public StandardConfiguration getDesignByName(final String name) {
		
		//Iterates the files with the designs of this design manager.
		for (final File f: new File(getDirectory()).listFiles()) {
						
			StandardConfiguration design =
			StandardConfiguration.createConfigurationFromFile(f.getAbsolutePath());
			
			//Handles the case that the current design has the given name.
			if (design.hasName(name)) {
				return design;
			}				
		}
		
		throw new UnexistingAttributeException(this, "design with the name '" + name + "'.");
	}
	
	//method
	/**
	 * @return the names of the designs of this design manager
	 */
	public final ReadContainer<String> getDesignNames() {
		
		final List<String> designNames = new List<>();
		
		//Iterates the files with the designs of this design manager.
		for (final File f: new File(getDirectory()).listFiles()) {
			StandardConfiguration design =
			StandardConfiguration.createConfigurationFromFile(f.getAbsolutePath());
			designNames.addAtEnd(design.getName());
						
		}	
		
		return new ReadContainer<>(designNames);
	}
}
