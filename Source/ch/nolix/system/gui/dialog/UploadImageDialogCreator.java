//package declaration
package ch.nolix.system.gui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.system.gui.base.Layer;
import ch.nolix.system.gui.base.LayerRole;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.containerwidget.HorizontalStack;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.ButtonRole;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Uploader;

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
		
		GlobalValidator.assertThat(imageWidget).thatIsNamed(ImageWidget.class).isNotNull();
		GlobalValidator.assertThat(imageTaker).thatIsNamed("image taker").isNotNull();
		
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
