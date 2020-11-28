package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.common.filesystem.FileAccessor;
import ch.nolix.common.filesystem.FileSystemAccessor;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.Downloader;

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
		downloader
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBackgroundColor(Color.LAVENDER)
			.setPaddings(5)
			.setTextSize(50)
		);
		
		//Adds the downloader to the frame.
		frame.addLayerOnTop(downloader);
	}
	
	/**
	 * Avoids that an instance of the {@link DownloaderTutorial} can be created.
	 */
	private DownloaderTutorial() {}
}
