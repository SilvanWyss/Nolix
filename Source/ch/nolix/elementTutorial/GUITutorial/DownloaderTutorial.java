//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.element.GUI.Downloader;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link DownloaderTutorial} provides a tutorial for a {@link Downloader}.
 * Of the {@link DownloaderTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-07
 * @lines 60
 */
public final class DownloaderTutorial {
	
	//main method
	/**
	 * Creates a {@link Frame} with a {@link Downloader}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a file getter for the downloader.
		new FileSystemAccessor().overwriteFile("tutorial.txt", "Tutorial");
		
		//Creates a downloader
		final var downloader =
		new Downloader()
		.setText("Download file")
		.setFileProvider(new FileAccessor("tutorial.txt")) ;
		
		//Configures the look of the downloader.
			downloader
			.getRefBaseLook()
			.setBackgroundColor(Color.ALICE_BLUE)
			.setPaddings(5)
			.setTextSize(50)
			.setTextColor(Color.DARK_BLUE);
			
			downloader
			.getRefHoverLook()
			.setTextColor(Color.BLUE);
			
			downloader
			.getRefFocusLook()
			.setTextColor(Color.BLUE_VIOLET);
			
		//Creates a frame with the downloader.
		new Frame()
		.setTitle("Downloader Tutorial")
		.setRootWidget(downloader);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link DownloaderTutorial} can be created.
	 */
	private DownloaderTutorial() {}
}
