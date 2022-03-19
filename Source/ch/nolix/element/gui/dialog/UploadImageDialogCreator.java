//package declaration
package ch.nolix.element.gui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.base.LayerRole;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.ButtonRole;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.Uploader;

//class
public final class UploadImageDialogCreator {
	
	//method
	public Layer createUploadImageDialogWithCurrentImageAndImageTaker(
		final MutableImage currentImage,
		final IElementTaker<MutableImage> imageTaker
	) {
		
		final var imageWidget = new ImageWidget();
		imageWidget.setImage(currentImage);
		
		return createUploadImageDialogWithImageWidgetAndImageTaker(imageWidget, imageTaker);
	}
	
	//method
	public Layer createUploadImageDialogWithImageTaker(final IElementTaker<MutableImage> imageTaker) {
		return createUploadImageDialogWithImageWidgetAndImageTaker(new ImageWidget(), imageTaker);
	}
	
	//method
	private Layer createUploadImageDialogWithImageWidgetAndImageTaker(
		final ImageWidget imageWidget,
		final IElementTaker<MutableImage> imageTaker
	) {
		
		Validator.assertThat(imageWidget).thatIsNamed(ImageWidget.class).isNotNull();
		Validator.assertThat(imageTaker).thatIsNamed("image taker").isNotNull();
		
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootWidget(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.add(
				imageWidget,
				new Uploader().setFileTaker(data -> imageWidget.setImage(MutableImage.fromBytes(data))),
				new HorizontalStack()
				.add(
					new Button()
					.setRole(ButtonRole.CANCEL_BUTTON)
					.setText("Cancel")
					.setLeftMouseButtonPressAction(b -> b.getParentLayer().removeSelfFromGUI()),
					new Button()
					.setRole(ButtonRole.CONFIRM_BUTTON)
					.setText("Select")
					.setLeftMouseButtonPressAction(
						b -> {
							
							b.getParentLayer().removeSelfFromGUI();
							
							if (imageWidget.containsAny()) {
								imageTaker.run(imageWidget.getRefImage());
							}
						}
					)
				)
			)
		);
	}
}
