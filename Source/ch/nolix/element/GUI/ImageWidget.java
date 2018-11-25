//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.entity.MutableOptionalProperty;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.element.image.Image;
import ch.nolix.element.painter.IPainter;

//class
public final class ImageWidget
extends BorderWidget<ImageWidget, ImageWidgetLook>
implements Clearable<ImageWidget> {
	
	//attribute
	private final MutableOptionalProperty<Image> image =
	new MutableOptionalProperty<Image>(
		Image.TYPE_NAME,
		i -> setImage(i),
		s -> Image.createFromSpecification(s),
		i -> i.getSpecification()
	);
	
	//constructor
	public ImageWidget() {
		reset();
		approveProperties();
		applyUsableConfiguration();
	}
	
	//constructor
	public ImageWidget(final Image image) {
		
		this();
		
		setImage(image);
	}
	
	//method
	public ImageWidget clear() {
		
		image.clear();
		
		return this;
	}
	
	//method
	public CursorIcon getContentAreaCursorIcon() {
		return getCustomCursorIcon();
	}
	
	//method
	public Image getRefImage() {
		return image.getValue();
	}

	//method
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public boolean isEmpty() {
		return image.isEmpty();
	}
	
	//method
	public boolean keepsFocus() {
		return false;
	}
	
	//method
	public ImageWidget setImage(final Image image) {
		
		this.image.setValue(image);
		
		return this;
	}
	
	//method
	protected void applyUsableConfigurationWhenConfigurationIsReset() {}
	
	//method
	protected ImageWidgetLook createWidgetLook() {
		return new ImageWidgetLook();
	}
	
	//method
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	protected int getContentAreaHeight() {
		return (isEmpty() ? 0 : getRefImage().getHeight());
	}

	//method
	protected int getContentAreaWidth() {
		return (isEmpty() ? 0 : getRefImage().getWidth());
	}

	//method
	protected void paintContentArea(
		final ImageWidgetLook borderWidgetLook,
		final IPainter painter
	) {
		if (containsAny()) {
			painter.paintImage(getRefImage());
		}
	}
}
