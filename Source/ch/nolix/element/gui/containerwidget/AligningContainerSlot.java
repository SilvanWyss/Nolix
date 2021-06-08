//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.gui.widget.Widget;

//class
final class AligningContainerSlot implements Clearable {
	
	//optional attribute
	private Widget<?, ?> widget;
	
	//method
	@Override
	public void clear() {
		widget = null;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public void fillUpWidgetInto(final LinkedList<Widget<?, ?>> list) {
		if (widget != null) {
			list.addAtEnd(widget);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public int getHeight() {
				
		if (widget == null) {
			return 0;
		}
		
		return widget.getHeight();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public Widget<?, ?> getRefWidget() {
		
		if (widget == null) {
			throw new EmptyArgumentException(this);
		}
		
		return widget;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public int getWidth() {
				
		if (widget == null) {
			return 0;
		}
		
		return widget.getWidth();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (widget == null);
	}
	
	//method
	public void setPositionOnParentContentArea(
		final int xPositionOnParentContentArea,
		final int yPositionOnParentContentArea
	) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		if (widget == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, Widget.class);
		}
		
		widget.setPositionOnParent(xPositionOnParentContentArea, yPositionOnParentContentArea);
	}
	
	//method
	public void setWidget(final Widget<?, ?> widget) {
		
		Validator.assertThat(widget).thatIsNamed(Widget.class).isNotNull();
		
		this.widget = widget;
	}
}
