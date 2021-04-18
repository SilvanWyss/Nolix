package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.common.environment.filesystem.FileAccessor;
import ch.nolix.common.environment.filesystem.FileSystemAccessor;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.Downloader;

/**
 * The {@link DownloaderTutorial} is a tutorial for {@link Downloader}s.
 * Of the {@link DownloaderTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-07
 * @lines 50
 */
public final class DownloaderTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Downloader}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Downloader Tutorial");
		
		//Creates a file for the Downloader.
		FileSystemAccessor.overwriteFile("tutorial.txt", "Tutorial");
		
		//Creates a Downloader.
		final var downloader =
		new Downloader()
		.setText("Download file")
		.setFileProvider(new FileAccessor("tutorial.txt")) ;
		
		//Configures the look of the downloader.
		downloader.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 5)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.BASE, 5)
			.setTextSizeForState(WidgetLookState.BASE, 50)
		);
		
		//Adds the downloader to the frame.
		frame.addLayerOnTop(downloader);
	}
	
	/**
	 * Prevents that an instance of the {@link DownloaderTutorial} can be created.
	 */
	private DownloaderTutorial() {}
}
