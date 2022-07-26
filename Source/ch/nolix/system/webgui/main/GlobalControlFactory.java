//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public final class GlobalControlFactory {
	
	//static attribute
	private static final ControlFactory controlFactory = new ControlFactory();
	
	//static method
	public static boolean canCreateControlOfType(final String type) {
		return controlFactory.canCreateControlOfType(type);
	}
	
	//static method
	public static Control<?, ?> createControlFromSpecification(final INode<?> specification) {
		return controlFactory.createControlFromSpecification(specification);
	}
	
	//static method
	public static Control<?, ?> createControlOfType(final String type) {
		return controlFactory.createControlOfType(type);
	}
	
	//static method
	public static void registerControlClass(@SuppressWarnings("unchecked") final Class<Control<?, ?>>... controlClasses) {
		controlFactory.registerControlClass(controlClasses);
	}
	
	//constructor
	private GlobalControlFactory() {}
}
