package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Downloader;

/**
 * The {@link DownloaderTutorial} is a tutorial for {@link Downloader}s.
 * Of the {@link DownloaderTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-07
 */
public final class DownloaderTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Downloader}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Downloader Tutorial");
		
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
		frame.setRootWidget(downloader);
	}
	
	/**
	 * Avoids that an instance of the {@link DownloaderTutorial} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private DownloaderTutorial() {
		throw new UninstantiableClassException(getClass());
	}
}
