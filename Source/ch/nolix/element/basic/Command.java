/*
 * file:	Command.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	130
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.common.constants.StringManager;
import ch.nolix.common.container.List;
import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.common.exception.UnsupportedMethodException;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.util.Validator;

//class
public class Command extends Element {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "Command";
	
	//attributes
	private final ILevel1Controller controller;
	private final Statement value = new Statement();
	
	//constructor
	/**
	 * Creates new command with the given controller.
	 * 
	 * @param controller
	 * @throws Exception if the given contorller is null
	 */
	public Command(ILevel1Controller controller) {
		
		Validator.throwExceptionIfValueIsNull("controller", controller);
		
		this.controller = controller;
	}
	
	//constructor
	/**
	 * Creates new command with the given contorand value.
	 * 
	 * @param controller
	 * @param value
	 * @throws Exception if:
	 *  -the given controller is null
	 *  -the given value is not valid
	 */
	public Command(ILevel1Controller controller, String value) {		
		
		//Calls other constructor.
		this(controller);
		
		setValue(value);
	}
	
	//method
	/**
	 * @return the attributes of this command
	 */
	public final List<Specification> getAttributes() {
		return new List<Specification>().addAtEnd(new Specification(value.toString()));
	}
	
	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public final Object getRawReference(Statement request) {
		throw new UnsupportedMethodException(this, "get raw reference");
	}
	
	//method
	/**
	 * @return the value of this command
	 */
	public final String getValue() {
		return value.toString();
	}
	
	//method
	/**
	 * @throws UnsupportedMethodException
	 */
	public final void removeAttribute(String attribute) {
		throw new UnsupportedMethodException(this, "remove attribute");	
	}
	
	//method
	/**
	 * Resets this command.
	 */
	public final void reset() {
		value.setValue(StringManager.DEFAULT_STRING);
	}
	
	//method
	/**
	 * Runs this command.
	 */
	public final void run() {
		controller.run(value);
	}
	
	//method
	/**
	 * Sets the given attribute to this command.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(Specification attribute) {
		setValue(attribute.toString());
	}
	
	//method
	/**
	 * Sets the value of this command.
	 * 
	 * @param value
	 * @throws Exception if the given value is not valid
	 */
	public final void setValue(String value) {
		this.value.setValue(value);
	}	
}
