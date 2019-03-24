//package declaration
package ch.nolix.nolixTest;

//own imports
import ch.nolix.core.fileSystem.FolderAccessor;
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.core.math.Calculator;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 80
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new {@link NolixTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("--- Nolix ---");
		System.out.println();
		
		measureSizeOfNolixLibrary();
		System.out.println();
		
		new NolixTestPool().run();
	}
	
	//static method
	private static void measureSizeOfNolixLibrary() {
		
		final var fileAccesssors =
		new FolderAccessor().getParentFolderAccessor().getFolderAccessor("Source").getFileAccessorsRecursively();
		
		final var productiveClassCount =
		Calculator.round(
			fileAccesssors.getCount(
				fa -> 
				!fa.getName().endsWith("Test.java")
				&& !fa.getName().endsWith("Launcher.java")
				&& !fa.getName().endsWith("Tutorial.java")
			)
		)
		.downToNext(10);
		
		System.out.println("number of productive classes: ca. " + productiveClassCount);
		
		final var testClassCount =
		Calculator.round(fileAccesssors.getCount(fa -> fa.getName().endsWith("Test.java"))).downToNext(10);
		
		System.out.println("number of test classes: ca. " +  testClassCount);
		
		final var launcherClassCount =
		Calculator.round(fileAccesssors.getCount(fa -> fa.getName().endsWith("Launcher.java"))).downToNext(10);
		
		System.out.println("number of launcher classes: ca. " + launcherClassCount);
		
		final var tutorialClassCount =
		Calculator.round(fileAccesssors.getCount(fa -> fa.getName().endsWith("Tutorial.java"))).downToNext(10);
		
		System.out.println("number of tutorial classes: ca. " + tutorialClassCount);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link NolixTestPool} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private Launcher() {
		throw new UninstantiableClassException(Launcher.class);
	}
}
