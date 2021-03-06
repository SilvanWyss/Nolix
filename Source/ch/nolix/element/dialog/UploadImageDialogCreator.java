//package declaration
package ch.nolix.element.dialog;

import ch.nolix.common.errorcontrol.validator.Validator;
//own imports
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.base.LayerRole;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Uploader;

//class
public final class UploadImageDialogCreator {
	
	//method
	public Layer createUploadImageDialogWithCurrentImageAndImageTaker(
		final Image currentImage,
		final IElementTaker<Image> imageTaker
	) {
		
		final var imageWidget = new ImageWidget();
		imageWidget.setImage(currentImage);
		
		return createUploadImageDialogWithImageWidgetAndImageTaker(imageWidget, imageTaker);
	}
	
	//method
	public Layer createUploadImageDialogWithImageTaker(final IElementTaker<Image> imageTaker) {
		return createUploadImageDialogWithImageWidgetAndImageTaker(new ImageWidget(), imageTaker);
	}
	
	//method
	private Layer createUploadImageDialogWithImageWidgetAndImageTaker(
		final ImageWidget imageWidget,
		final IElementTaker<Image> imageTaker
	) {
		
		Validator.assertThat(imageWidget).thatIsNamed(ImageWidget.class).isNotNull();
		Validator.assertThat(imageTaker).thatIsNamed("image taker").isNotNull();
		
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootWidget(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addWidget(
				imageWidget,
				new Uploader().setFileTaker(data -> imageWidget.setImage(Image.fromBytes(data))),
				new HorizontalStack()
				.addWidget(
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
