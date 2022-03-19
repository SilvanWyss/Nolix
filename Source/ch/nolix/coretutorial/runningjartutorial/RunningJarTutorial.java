package ch.nolix.coretutorial.runningjartutorial;

//own imports
import ch.nolix.core.environment.runningjar.RunningJar;

public final class RunningJarTutorial {
	
	public static void main(String[] args) {
		System.out.print(
			RunningJar.getResource("ch/nolix/commonTutorial/RunningJarTutorial/resource/Willkommen_und_Abschied.txt")
		);
	}
	
	private RunningJarTutorial() {}
}
