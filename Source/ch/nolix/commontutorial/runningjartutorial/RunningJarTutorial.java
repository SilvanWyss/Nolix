package ch.nolix.commontutorial.runningjartutorial;

import ch.nolix.common.environment.runningjar.RunningJar;

public final class RunningJarTutorial {
	
	public static void main(String[] args) {
		System.out.print(
			RunningJar.getResource("ch/nolix/commonTutorial/RunningJarTutorial/resources/Willkommen_und_Abschied.txt")
		);
	}
	
	private RunningJarTutorial() {}
}
