//package declaration
package ch.nolix.element.testableGUI;

//own imports
import ch.nolix.common.attributeAPI.IdentifiedByString;
import ch.nolix.common.invalidArgumentExceptions.ArgumentHasAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.rasterAPI.Rectangular;
import ch.nolix.common.validator.Validator;

//class
public abstract class GUIElementController implements IdentifiedByString, Rectangular {
	
	//attributes
	private GUIElement gUIElement;
	
	//method
	@Override
	public int getHeight() {
		return gUIElement.getHeight();
	} 
	
	//method
	@Override
	public final String getId() {
		return gUIElement.getId();
	}
	
	//method
	public final ITestableGUI getParentGUI() {
		return gUIElement.getParentGUI();
	}
	
	//method
	@Override
	public int getWidth() {
		return gUIElement.getWidth();
	}
	
	//method
	public final int getXPositionOnViewArea() {
		return gUIElement.getXPositionOnViewArea();
	}
	
	//method
	public final int getYPositionOnViewArea() {
		return gUIElement.getYPositionOnViewArea();
	}
	
	//method
	public void clickLeftMouseButtonOnTopLeft() {
		clickLeftMouseButton(0, 0);
	}
	
	//method
	public void clickLeftMouseButton(final int xPosition, final int yPosition) {
		getParentGUI().noteLeftMouseButtonClick(
			getXPositionOnViewArea() + xPosition,
			getYPositionOnViewArea() + yPosition
		);
	}
	
	//method
	protected BaseNode getAttribute(String name) {
		return gUIElement.getAttribute(name);
	}
	
	//method
	void setGeneralGUIElement(final GUIElement gUIElement) {
		
		Validator.suppose(gUIElement).isOfType(GUIElement.class);
		
		supposeDoesNotHaveGeneralGUIElement();
		
		this.gUIElement = gUIElement;
	}
	
	//method
	private boolean hasGeneralGUIElement() {
		return (gUIElement != null);
	}
	
	//method
	private void supposeDoesNotHaveGeneralGUIElement() {
		if (hasGeneralGUIElement()) {
			throw new ArgumentHasAttributeException(this, GUIElement.class);
		}
	}
}
