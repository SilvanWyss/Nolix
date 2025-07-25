package ch.nolix.coretest.structurecontrol.reflectiontool;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.base.AbstractContainer;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.structurecontrol.reflectiontool.FieldTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.IArrayList;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.container.matrix.IMatrix;

final class FieldToolTest extends StandardTest {

  private static final class Lecture {

    @SuppressWarnings("unused")
    public LinkedList<String> students;
  }

  @Test
  void testCase_hasGivenTypeOrSuperType_1A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldTool();

    //execution
    final var result = testUnit.hasGivenTypeOrSuperType(studentsField, LinkedList.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasGivenTypeOrSuperType_1B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldTool();

    //execution
    final var result = testUnit.hasGivenTypeOrSuperType(studentsField, AbstractContainer.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasGivenTypeOrSuperType_2A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldTool();

    //execution
    final var result = testUnit.hasGivenTypeOrSuperType(studentsField, ILinkedList.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasGivenTypeOrSuperType_2B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldTool();

    //execution
    final var result = testUnit.hasGivenTypeOrSuperType(studentsField, IContainer.class);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_hasGivenTypeOrSuperType_3A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldTool();

    //execution
    final var result = testUnit.hasGivenTypeOrSuperType(studentsField, Matrix.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasGivenTypeOrSuperType_3B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldTool();

    //execution
    final var result = testUnit.hasGivenTypeOrSuperType(studentsField, ArrayList.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasGivenTypeOrSuperType_4A() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldTool();

    //execution
    final var result = testUnit.hasGivenTypeOrSuperType(studentsField, IMatrix.class);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_hasGivenTypeOrSuperType_4B() throws NoSuchFieldException {

    //setup
    final var studentsField = Lecture.class.getField("students");
    final var testUnit = new FieldTool();

    //execution
    final var result = testUnit.hasGivenTypeOrSuperType(studentsField, IArrayList.class);

    //verification
    expect(result).isFalse();
  }
}
