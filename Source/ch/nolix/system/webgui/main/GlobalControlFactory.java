//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public final class GlobalControlFactory {

  //constant
  private static final ControlFactory CONTROL_FACTORY = new ControlFactory();

  //static method
  public static boolean canCreateControlOfType(final String type) {
    return CONTROL_FACTORY.canCreateControlOfType(type);
  }

  //static method
  public static Control<?, ?> createControlFromSpecification(final INode<?> specification) {
    return CONTROL_FACTORY.createControlFromSpecification(specification);
  }

  //static method
  public static Control<?, ?> createControlOfType(final String type) {
    return CONTROL_FACTORY.createControlOfType(type);
  }

  //static method
  public static void registerControlClass(
      final Class<Control<?, ?>> controlClass,
      final @SuppressWarnings("unchecked") Class<Control<?, ?>>... controlClasses) {
    CONTROL_FACTORY.registerControlClass(controlClass, controlClasses);
  }

  //constructor
  private GlobalControlFactory() {
  }
}
