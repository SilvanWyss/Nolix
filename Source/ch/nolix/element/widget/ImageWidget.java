//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;

//class
public final class ImageWidget
extends BorderWidget<ImageWidget, ImageWidgetLook>
implements Clearable<ImageWidget> {
	
	//attribute
	private final MutableOptionalValue<Image> image =
	new MutableOptionalValue<>(
		Image.TYPE_NAME,
		i -> setImage(i),
		s -> Image.fromSpecification(s),
		i -> i.getSpecification()
	);
	
	//constructor
	public ImageWidget() {
		resetAndApplyDefaultConfiguration();
	}
		
	//method
	@Override
	public ImageWidget clear() {
		
		image.clear();
		
		return this;
	}
	
	//method
	public Image getRefImage() {
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
		return image.isEmpty();
	}
	
	//method
	public ImageWidget setImage(final Image image) {
		
		this.image.setValue(image);
		
		return this;
	}
	
	//method
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {}
	
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
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected int getContentAreaHeight() {
		return (isEmpty() ? 0 : getRefImage().getHeight());
	}

	//method
	@Override
	protected int getContentAreaWidth() {
		return (isEmpty() ? 0 : getRefImage().getWidth());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void paintContentArea(
		final ImageWidgetLook borderWidgetLook,
		final IPainter painter
	) {
		if (containsAny()) {
			painter.paintImage(getRefImage());
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void recalculateSelfStage2() {}
}
