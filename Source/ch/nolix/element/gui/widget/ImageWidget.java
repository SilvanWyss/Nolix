//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
public final class ImageWidget extends BorderWidget<ImageWidget, ImageWidgetLook> implements Clearable {
	
	//constant
	private static final String IMAGE_HEADER = PascalCaseCatalogue.IMAGE;
	
	//attribute
	private final MutableOptionalValue<MutableImage> image =
	new MutableOptionalValue<>(
		IMAGE_HEADER,
		this::setImage,
		MutableImage::fromSpecification,
		MutableImage::getSpecification
	);
	
	//constructor
	public ImageWidget() {
		reset();
	}
	
	//method
	@Override
	public void clear() {
		image.clear();
	}
	
	//method
	public MutableImage getRefImage() {
		return image.getValue();
	}

	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return !image.hasValue();
	}
	
	//method
	public ImageWidget setImage(final MutableImage mutableImage) {
		
		this.image.setValue(mutableImage);
		
		return this;
	}
	
	//method
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
	//method
	@Override
	protected ImageWidgetLook createLook() {
		return new ImageWidgetLook();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefImage().getHeight();
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefImage().getWidth();
	}
	
	//method
	@Override
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
		
	//method
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
		
	//method
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void paintContentArea(final IPainter painter, final ImageWidgetLook imageWidgetLook) {
		if (containsAny()) {
			painter.paintImage(getRefImage());
		}
	}
	
	//method
	@Override
	protected void recalculateBorderWidget() {}
	
	//method
	@Override
	protected void resetBorderWidgetConfiguration() {}
	
	//method
	@Override
	protected void resetBorderWidget() {}
}
