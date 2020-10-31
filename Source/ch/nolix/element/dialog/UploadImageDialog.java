//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.Layer;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Uploader;
import ch.nolix.element.widget.VerticalStack;

//class
public final class UploadImageDialog extends Layer {
	
	//attributes
	private final ImageWidget imageWidget = new ImageWidget();
	private final IElementTaker<Image> imageTaker;
	
	//constructor
	public UploadImageDialog(final IElementTaker<Image> imageTaker) {
		
		Validator.assertThat(imageTaker).thatIsNamed("image taker").isNotNull();
		
		this.imageTaker = imageTaker;
		
		fillUp();
	}
	
	//constructor
	public UploadImageDialog(final Image currentImage, final IElementTaker<Image> imageTaker) {
		
		Validator.assertThat(imageTaker).thatIsNamed("image taker").isNotNull();
		
		imageWidget.setImage(currentImage);
		this.imageTaker = imageTaker;
		
		fillUp();
	}
	
	//method
	public boolean containsImage() {
		return imageWidget.containsAny();
	}
	
	//method
	public UploadImageDialog selectImage(final Image image) {
		
		imageWidget.setImage(image);
		
		return this;
	}
	
	//method
	private void confirm() {
		
		removeSelfFromGUI();
		
		if (containsImage()) {
			imageTaker.run(imageWidget.getRefImage());
		}
	}
	
	private void fillUp() {
		setRootWidget(
			new VerticalStack()
			.setRole(ContainerRole.DialogContainer)
			.addWidget(
				imageWidget,
				new Uploader(this::selectImage),
				new HorizontalStack()
				.addWidget(
					new Button()
					.setRole(ButtonRole.CancelButton)
					.setText("Cancel")
					.setLeftMouseButtonPressAction(this::removeSelfFromGUI),
					new Button()
					.setRole(ButtonRole.ConfirmButton)
					.setText("Select")
					.setLeftMouseButtonPressAction(this::confirm)
				)
			)
		);
	}
	
	//method
	private void selectImage(final byte[] image) {
		selectImage(Image.fromBytes(image));
	}
}
