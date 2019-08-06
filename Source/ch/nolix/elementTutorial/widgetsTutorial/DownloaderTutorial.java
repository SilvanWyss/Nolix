package ch.nolix.elementTutorial.widgetsTutorial;

import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.element.GUI.LayerFrame;
import ch.nolix.element.color.Color;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.widgets.Downloader;

/**
 * The {@link DownloaderTutorial} is a tutorial for {@link Downloader}s.
 * Of the {@link DownloaderTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-07
 */
public final class DownloaderTutorial {
	
	/**
	 * Creates a {@link FrameVisualizer} with a {@link Downloader}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new LayerFrame("Downloader Tutorial");
		
		//Creates a file for the downloader.
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
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setPaddings(5)
			.setTextSize(50)
			.setTextColor(Color.DARK_BLUE)
		)
		.applyOnHoverLook(hl -> hl.setBorderColors(Color.BLUE).setTextColor(Color.BLUE))
		.applyOnFocusLook(fl -> fl.setBorderColors(Color.BLUE_VIOLET).setTextColor(Color.BLUE_VIOLET))
		.applyOnHoverFocusLook(fl -> fl.setBorderColors(Color.BLUE_VIOLET).setTextColor(Color.BLUE_VIOLET));
		
		//Adds the downloader to the frame.
		frame.addLayerOnTop(downloader);
	}
	
	/**
	 * Avoids that an instance of the {@link DownloaderTutorial} can be created.
	 */
	private DownloaderTutorial() {}
}
