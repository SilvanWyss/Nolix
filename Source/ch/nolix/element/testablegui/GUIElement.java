//package declaration
package ch.nolix.element.testablegui;

//Java import
import java.lang.reflect.InvocationTargetException;

import ch.nolix.common.attributeapi.IdentifiedByString;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.exception.WrapperException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.rasterapi.Rectangular;
import ch.nolix.common.validator.Validator;

//class
public abstract class GUIElement implements IdentifiedByString, Rectangular {
	
	//attributes
	private final ITestableGUI parentGUI;
	private final String id;
	
	//constructor
	public GUIElement(final ITestableGUI parentGUI, final String id) {
		
		Validator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		Validator.assertThat(id).thatIsNamed(VariableNameCatalogue.ID).isNotBlank();
		
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
			throw new WrapperException(exception);
		}
	}
	
	//method declaration
	protected abstract BaseNode getAttribute(String name);
}
