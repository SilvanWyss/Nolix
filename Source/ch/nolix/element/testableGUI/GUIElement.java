//package declaration
package ch.nolix.element.testableGUI;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.attributeAPI.IdentifiedByString;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.rasterAPI.Rectangular;
import ch.nolix.common.validator.Validator;

//class
public abstract class GUIElement implements IdentifiedByString, Rectangular {
	
	//attributes
	private final ITestableGUI parentGUI;
	private final String id;
	
	//constructor
	public GUIElement(final ITestableGUI parentGUI, final String id) {
		
		Validator.suppose(parentGUI).thatIsNamed("parent GUI").isNotNull();
		Validator.suppose(id).thatIsNamed(VariableNameCatalogue.ID).isNotBlank();
		
		this.parentGUI = parentGUI;
		this.id = id;
	}
	
	//method
	public final String getId() {
		return id;
	}
	
	//method
	public final ITestableGUI getParentGUI() {
		return parentGUI;
	}
	
	//method
	public final GUIController getRefGUIController() {
		return parentGUI.getRefGUIController();
	}
	
	//method declaration
	public abstract int getXPositionOnViewArea();
	
	//method declaration
	public abstract int getYPositionOnViewArea();
	
	//method
	public final <GE extends GUIElementController> GE to(final Class<GE> type) {
		try {
			
			final var lGUIElement = type.getConstructor().newInstance();
			lGUIElement.setGUIElement(this);
			
			return lGUIElement;
		}
		catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			| NoSuchMethodException
			| SecurityException exception
		) {
			throw new RuntimeException(exception);
		}
	}
	
	//method declaration
	protected abstract BaseNode getAttribute(String name);
}
