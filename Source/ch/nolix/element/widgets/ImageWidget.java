//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.element.GUI_API.Widget;
import ch.nolix.element.base.MutableOptionalProperty;
import ch.nolix.element.image.Image;
import ch.nolix.element.painter.IPainter;

//class
public final class ImageWidget
extends BorderWidget<ImageWidget, ImageWidgetLook>
implements Clearable<ImageWidget> {
	
	//attribute
	private final MutableOptionalProperty<Image> image =
	new MutableOptionalProperty<>(
		Image.TYPE_NAME,
		i -> setImage(i),
		s -> Image.createFromSpecification(s),
		i -> i.getSpecification()
	);
	
	//constructor
	public ImageWidget() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	public ImageWidget(final Image image) {
		
		this();
		
		setImage(image);
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
	protected ImageWidgetLook createWidgetLook() {
		return new ImageWidgetLook();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final List<Widget<?, ?>> list) {}
	
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
	@Override
	protected void paintContentArea(
		final ImageWidgetLook borderWidgetLook,
		final IPainter painter
	) {
		if (containsAny()) {
			painter.paintImage(getRefImage());
		}
	}
}
