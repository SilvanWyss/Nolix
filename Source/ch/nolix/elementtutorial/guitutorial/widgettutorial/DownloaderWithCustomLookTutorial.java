package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.common.environment.filesystem.FileAccessor;
import ch.nolix.common.environment.filesystem.FileSystemAccessor;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.Downloader;

/**
 * The {@link DownloaderWithCustomLookTutorial} is a tutorial for {@link Downloader}s.
 * Of the {@link DownloaderWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 * @lines 60
 */
public final class DownloaderWithCustomLookTutorial {
	

	/**
	 * Creates a {@link Frame} with a {@link Downloader}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("Downloader with custom look Tutorial");
		
		//Creates a file for the Downloader.
		var filePath = "tutorial.txt";
		var i = 1;
		while (FileSystemAccessor.exists(filePath)) {
			filePath = "tutorial" + i + ".txt";
			i++;
		}
		FileSystemAccessor.createFile(filePath, "tutorial");
		
		//Creates a Downloader.
		final var downloader = new Downloader().setText("Download file").setFileProvider(new FileAccessor(filePath));
		
		//Configures the look of the Downloader.
		downloader.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 1)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.BASE, 10)
		);
		
		//Adds the Downloader to the Frame.
		frame.addLayerOnTop(downloader);
	}
	
	/**
	 * Prevents that an instance of the {@link DownloaderWithCustomLookTutorial} can be created.
	 */
	private DownloaderWithCustomLookTutorial() {}
}
