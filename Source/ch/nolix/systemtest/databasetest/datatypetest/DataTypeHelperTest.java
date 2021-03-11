//package declaration
package ch.nolix.systemtest.databasetest.datatypetest;

import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.system.database.entity.BackReference;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.EntityAccessor;
import ch.nolix.system.database.entity.MultiBackReference;
import ch.nolix.system.database.entity.MultiReference;
import ch.nolix.system.database.entity.MultiValue;
import ch.nolix.system.database.entity.OptionalBackReference;
import ch.nolix.system.database.entity.OptionalReference;
import ch.nolix.system.database.entity.OptionalValue;
import ch.nolix.system.database.entity.Reference;
import ch.nolix.system.database.entity.Value;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedBackReferenceType;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedDataTypeFactory;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedMultiBackReferenceType;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedMultiReferenceType;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedMultiValueType;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedOptionalBackReferenceType;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedOptionalReferenceType;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedOptionalValueType;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedReferenceType;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedValueType;

//class
public final class DataTypeHelperTest extends Test {
		
	//static class
	private static final class Entity1A extends Entity {
		
		//attribute
		public final Value<String> valueProperty = new Value<>(v ->  {});
	}
	
	//static class
	private static final class Entity1B extends Entity {
		
		//attribute
		public final OptionalValue<String> optionalValueProperty = new OptionalValue<>();
	}
	
	//static class
	private static final class Entity1C extends Entity {
		
		//attribute
		public final MultiValue<String> multiValueProperty = new MultiValue<>();
	}
	
	//static class
	private static final class Entity2A extends Entity {
		
		//attribute
		public final Reference<Entity2D> reference = new Reference<>();
	}
	
	//static class
	private static final class Entity2B extends Entity {
		
		//attribute
		public final OptionalReference<Entity2D> optionalReference= new OptionalReference<>();
	}
	
	//static class
	private static final class Entity2C extends Entity {
		
		//attribute
		public final MultiReference<Entity2D> multiReference = new MultiReference<>();
	}
	
	//static class
	private static final class Entity2D extends Entity {}
	
	//static class
	private static final class Entity3A extends Entity {
		
		//attribute
		public final BackReference<Entity3D> backReference = new BackReference<>("reference");
	}
	
	//static class
	private static final class Entity3B extends Entity {
		
		//attribute
		public final OptionalBackReference<Entity3E> optionalBackReference =
		new OptionalBackReference<>("optionalReference");
	}
	
	//static class
	private static final class Entity3C extends Entity {
		
		//attribute
		public final MultiBackReference<Entity3F> multiBackReference = new MultiBackReference<>("multiReference");
	}
	
	//static class
	private static final class Entity3D extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		public final Reference<Entity3A> reference = new Reference<>();
	}
	
	//static class
	private static final class Entity3E extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		public final OptionalReference<Entity3B> optionalReference = new OptionalReference<>();
	}
	
	//static class
	private static final class Entity3F extends Entity {
		
		//attribute
		@SuppressWarnings("unused")
		public final MultiReference<Entity3C> multiReference = new MultiReference<>();
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForValueProperty() {
		
		//setup
		final var entity = new Entity1A();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = ParametrizedDataTypeFactory.createDatatypeFor(entity.valueProperty);
		
		//verification
		expect(result.getClass()).isSameAs(ParametrizedValueType.class);
		expect(result.getRefContentClass()).isSameAs(String.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForOptionalValueProperty() {
		
		//setup
		final var entity = new Entity1B();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = ParametrizedDataTypeFactory.createDatatypeFor(entity.optionalValueProperty);
		
		//verification
		expect(result.getClass()).isSameAs(ParametrizedOptionalValueType.class);
		expect(result.getRefContentClass()).isSameAs(String.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForMultiValueProperty() {
		
		//setup
		final var entity = new Entity1C();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = ParametrizedDataTypeFactory.createDatatypeFor(entity.multiValueProperty);
		
		//verification
		expect(result.getClass()).isSameAs(ParametrizedMultiValueType.class);
		expect(result.getRefContentClass()).isSameAs(String.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForReference() {
		
		//setup
		final var entity = new Entity2A();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = ParametrizedDataTypeFactory.createDatatypeFor(entity.reference);
		
		//verification
		expect(result.getClass()).isSameAs(ParametrizedReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity2D.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForOptionalReference() {
		
		//setup
		final var entity = new Entity2B();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = ParametrizedDataTypeFactory.createDatatypeFor(entity.optionalReference);
		
		//verification
		expect(result.getClass()).isSameAs(ParametrizedOptionalReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity2D.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForMultiReference() {
		
		//setup
		final var entity = new Entity2C();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = ParametrizedDataTypeFactory.createDatatypeFor(entity.multiReference);
		
		//verification
		expect(result.getClass()).isSameAs(ParametrizedMultiReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity2D.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForBackReference() {
		
		//setup
		final var entity = new Entity3A();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = ParametrizedDataTypeFactory.createDatatypeFor(entity.backReference);
		
		//verification
		expect(result.getClass()).isSameAs(ParametrizedBackReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity3D.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForOptionalBackReference() {
		
		//setup
		final var entity = new Entity3B();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = ParametrizedDataTypeFactory.createDatatypeFor(entity.optionalBackReference);
		
		//verification
		expect(result.getClass()).isSameAs(ParametrizedOptionalBackReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity3E.class);
	}
	
	//method
	@TestCase
	public void testCase_createDatatypeForMultiBackReference() {
		
		//setup
		final var entity = new Entity3C();
		EntityAccessor.extractProperties(entity);
		
		//execution
		final var result = ParametrizedDataTypeFactory.createDatatypeFor(entity.multiBackReference);
		
		//verification
		expect(result.getClass()).isSameAs(ParametrizedMultiBackReferenceType.class);
		expect(result.getRefContentClass()).isSameAs(Entity3F.class);
	}
}
