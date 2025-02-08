package ch.nolix.core.reflection;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.structurecontrol.reflectiontool.FieldTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.matrixapi.IMatrix;

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
    final var result = testUnit.hasGivenTypeOrSuperType(studentsField, Container.class);

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
