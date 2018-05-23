package ch.nolix.tutorialApplication.movieManager;

import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.databaseAdapter.ReferenceProperty;

public class Movie extends Entity {

	public final Property<String> Title = new Property<String>();
	public final ReferenceProperty<Genre> Genre = new ReferenceProperty<Genre>();
}
