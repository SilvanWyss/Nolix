package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.system.gui.main.Frame;
import ch.nolix.system.gui.widget.Downloader;

/**
 * The {@link DownloaderTutorial} is a tutorial for {@link Downloader}s.
 * Of the {@link DownloaderTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-07-07
 */
public final class DownloaderTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Downloader}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Downloader tutorial");
		
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
		
		//Adds the Downloader to the Frame.
		frame.pushLayerWithRootWidget(downloader);
	}
	
	/**
	 * Prevents that an instance of the {@link DownloaderTutorial} can be created.
	 */
	private DownloaderTutorial() {}
}
