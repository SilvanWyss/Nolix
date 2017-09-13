/*
 * file:	DesignManager
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	10
 */

//package declaration
package ch.nolix.system.modules;

//Java import
import java.io.File;






//own imports


import ch.nolix.core.centralController.CentralController;
import ch.nolix.core.centralController.Module;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.invalidStateException.UnsupportedMethodException;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.configuration.StandardConfiguration;

//class
public final class DesignManager extends Module {
	
	//request
	public static final String DESIGN_NAMES_REQUEST = "DesignNames";
	
	//constructor
	/**
	 * Creates new design manager with the given central controller.
	 * 
	 * @param centralController
	 * @throws Exception if the given central controller is null
	 */
	public DesignManager(final CentralController centralController) {
		
		//Calls constructor of the base class.
		super(centralController);
	}

	//method
	/**
	 * Adds the given design to this design manager.
	 * 
	 * @param design
	 * @return this design manager
	 * @throws Exception if this design manager contains already a design with the same name the given design has
	 */
	public final DesignManager addDesign(final StandardConfiguration design) {
		
		final String name = design.getName();
		
		if (containsDesign(name)) {
			throw new RuntimeException("Design manager contains already a design with the name '" + name + "'.");
		}
		
		design.saveAsTo(StandardConfiguration.TYPE_NAME, getDirectory() + "/" + name + ".spec");
		
		return this;
	}
	
	//method
	public final DesignManager addDesignIfPossible(final StandardConfiguration design) {
		
		final String name = design.getName();
		
		if (!containsDesign(name)) {
			design.saveAsTo(StandardConfiguration.TYPE_NAME, getDirectory() + "/" + name + ".spec");
		}
		
		return this;
	}
	
	public final DesignManager addDesignIfNeeded(final StandardConfiguration... designs) {
		
		for (StandardConfiguration d: designs) {
			addDesignIfPossible(d);
		}
		
		return this;
	}
	
	//method
	/**
	 * @param name
	 * @return true if this design manager contains a design with the given name
	 */
	public boolean containsDesign(final String name) {
		
		//Iterates the files with the designs of this design manager.
		for (File f: new File(getDirectory()).listFiles()) {
			if (f.getName().split("\\.")[0].equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	//method
	/**
	 * @param name
	 * @return the design with the given name from this design manager
	 * @throws UnexistingAttributeException if this design manager contains no design with the given name
	 */
	public StandardConfiguration getDesignByName(final String name) {
		
		//Iterates the files with the designs of this design manager.
		for (File f: new File(getDirectory()).listFiles()) {
			if (f.getName().split("\\.")[0].equals(name)) {
				StandardConfiguration design = new StandardConfiguration();
				design.loadFrom(f.getAbsolutePath());
				design.setName(f.getName().split("\\.")[0]);
				return design;
			}			
		}
		
		throw new UnexistingAttributeException(this, "design with name '" + name + "'.");
	}
	
	//method
	/**
	 * @return the names of the designs of this design manager
	 */
	public final List<String> getDesignNames() {
		
		final List<String> designNames = new List<String>();
		
		//Iterates the files with the designs of this design manager.
		for (File f: new File(getDirectory()).listFiles()) {
			designNames.addAtEnd(f.getName().split("\\.")[0]);
		}		
		
		return designNames;
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests
	 * @throws Exception if the given request is not valid
	 */
	public Object getRawData(final Statement request) {
		switch (request.getHeader()) {
			case DESIGN_NAMES_REQUEST:
				return getDesignNames();
			default:
				throw new InvalidArgumentException(
					new ArgumentName("request"),
					new Argument(request)
				);
		}
	}

	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public Object getRawReference(final Statement request) {
		throw new UnsupportedMethodException(this, "getRawReference");
	}

	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public void run(final Statement command) {
		throw new UnsupportedMethodException(this, "run");
	}
}
