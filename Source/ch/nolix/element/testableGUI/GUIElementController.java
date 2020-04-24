//package declaration
package ch.nolix.element.testableGUI;

//own imports
import ch.nolix.common.attributeAPI.IdentifiedByString;
import ch.nolix.common.invalidArgumentException.ArgumentHasAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.rasterAPI.Rectangular;
import ch.nolix.common.validator.Validator;

//class
public abstract class GUIElementController implements IdentifiedByString, Rectangular {
	
	//attributes
	private GUIElement mGUIElement;
	
	//method
	@Override
	public int getHeight() {
		return mGUIElement.getHeight();
	} 
	
	//method
	@Override
	public final String getId() {
		return mGUIElement.getId();
	}
	
	//method
	public final ITestableGUI getParentGUI() {
		return mGUIElement.getParentGUI();
	}
	
	//method
	@Override
	public final int getWidth() {
		return mGUIElement.getWidth();
	}
	
	//method
	public final int getXPositionOnViewArea() {
		return mGUIElement.getXPositionOnViewArea();
	}
	
	//method
	public final int getYPositionOnViewArea() {
		return mGUIElement.getYPositionOnViewArea();
	}
	
	//method
	public final void clickLeftMouseButtonOnTopLeft() {
		clickLeftMouseButton(0, 0);
	}
	
	//method
	public final void clickLeftMouseButton(final int xPosition, final int yPosition) {
		getParentGUI().noteLeftMouseButtonClick(
			getXPositionOnViewArea() + xPosition,
			getYPositionOnViewArea() + yPosition
		);
	}
	
	//method
	protected final BaseNode getAttribute(String name) {
		return mGUIElement.getAttribute(name);
	}
	
	//method
	protected final GUIController getRefGUIController() {
		return mGUIElement.getRefGUIController();
	}
	
	//method
	final void setGUIElement(final GUIElement gUIElement) {
		
		Validator.assertThat(gUIElement).isOfType(GUIElement.class);
		
		supposeDoesNotHaveGUIElement();
		
		this.mGUIElement = gUIElement;
	}
	
	//method
	private boolean hasGUIElement() {
		return (mGUIElement != null);
	}
	
	//method
	private void supposeDoesNotHaveGUIElement() {
		if (hasGUIElement()) {
			throw new ArgumentHasAttributeException(this, GUIElement.class);
		}
	}
}
