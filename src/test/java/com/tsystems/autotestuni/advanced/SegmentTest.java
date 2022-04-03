package com.tsystems.autotestuni.advanced;

import com.tsystems.autotestuni.basic.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Create a class {@code Segment} with two fields of the type {@link Point}. They're ends of the segment.
 * The fields should be final, their values should be assigned in a constructor.
 *
 * Implement a public method {@code calculateLength()} without any parameters. The method shoudl return
 * a length of the segment as a {@code double} value.
 */
public class SegmentTest {

    @Test
    void checkClass() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Assertions.assertDoesNotThrow(() -> Class.forName("com.tsystems.autotestuni.advanced.Segment"),
                "Class Segment does not exist");
        Class<?> cl = Class.forName("com.tsystems.autotestuni.advanced.Segment");
        Class<?>[] types = {Point.class , Point.class};
        Assertions.assertDoesNotThrow(() -> cl.getDeclaredConstructor(types),
                "Class Segment does not have an appropriate constructor");
        Constructor<?> constructor = cl.getDeclaredConstructor(types);

        Object[] args = {createPoint(0, 0), createPoint(1, 1)};
        Object segment = constructor.newInstance(args);

        Field[] fields = segment.getClass().getDeclaredFields();
        assertEquals(2, fields.length,
                "There should be 2 fields in Segment class");
        assertTrue(Arrays.stream(fields)
                        .allMatch(field -> field.getType().equals(Point.class)),
                "All fields in Segment class should have Point type");
        assertTrue(Arrays.stream(fields)
                        .allMatch(field -> Modifier.isFinal(field.getModifiers())),
                "All fields in Segment class should be final");

        Method[] methods = segment.getClass().getDeclaredMethods();
        assertEquals(1, methods.length,
                "There should be 1 method in Segment class");
        assertTrue(Arrays.stream(methods).allMatch(method -> method.getName().equals("calculateLength")),
                "There is no method \"calculateLength\" in Segment class");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("calculateLength"))
                        .allMatch(method -> method.getParameterCount() == 0),
                "Method \"calculateLength\" should have no parameters");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("calculateLength"))
                        .filter(method -> method.getParameterCount() == 0)
                        .allMatch(method -> method.getReturnType().equals(Double.TYPE)),
                "Method \"calculateLength\" should return a double value");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("calculateLength"))
                        .filter(method -> method.getParameterCount() == 0)
                        .allMatch(method -> Modifier.isPublic(method.getModifiers())),
                "Method \"calculateLength\" should be public");

        Method calculateLength = segment.getClass().getDeclaredMethod("calculateLength");
        Double result = (Double) calculateLength.invoke(segment);
        assertEquals(Math.sqrt(2.0), result);

        Point point1_1 = createPoint(1, 1);
        args = new Object[] {point1_1, point1_1};
        segment = constructor.newInstance(args);
        calculateLength = segment.getClass().getDeclaredMethod("calculateLength");
        result = (Double) calculateLength.invoke(segment);
        assertEquals(0.0, result);

        Point pointN5_N5 = createPoint(-5.0, -5.0);
        Point point5_5 = createPoint(5.0, 5.0);
        args = new Object[] {pointN5_N5, point5_5};
        segment = constructor.newInstance(args);
        calculateLength = segment.getClass().getDeclaredMethod("calculateLength");
        result = (Double) calculateLength.invoke(segment);
        assertEquals(Math.sqrt(200.0), result);
    }

    private Point createPoint(double x, double y) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> cl = Class.forName("com.tsystems.autotestuni.basic.Point");
        Class<?>[] types = {Double.TYPE , Double.TYPE};
        Constructor<?> constructor = cl.getConstructor(types);
        Object[] args = {x, y};
        return (Point) constructor.newInstance(args);
    }
}
