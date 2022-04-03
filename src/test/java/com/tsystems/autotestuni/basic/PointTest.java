package com.tsystems.autotestuni.basic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PointTest {

    @Test
    void checkFields() {
        Field[] fields = Point.class.getDeclaredFields();
        assertEquals(2, fields.length,
                "There should be 2 fields in Point class");
        assertTrue(Arrays.stream(fields)
                        .anyMatch(field -> field.getName().equals("x")),
                "There is no field \"x\" in Point class");
        assertTrue(Arrays.stream(fields)
                        .filter(field -> field.getName().equals("x"))
                        .anyMatch(field -> field.getType().equals(Double.TYPE)),
                "Field \"x\" has an improper type. Should be double");
        assertTrue(Arrays.stream(fields)
                        .filter(field -> field.getName().equals("x") && field.getType().equals(Double.TYPE))
                        .anyMatch(field -> Modifier.isPrivate(field.getModifiers())),
                "Field \"x\" has an improper access modifier. Should be private");

        assertTrue(Arrays.stream(fields)
                        .anyMatch(field -> field.getName().equals("y")),
                "There is no field \"y\" in Point class");
        assertTrue(Arrays.stream(fields)
                        .filter(field -> field.getName().equals("y"))
                        .anyMatch(field -> field.getType().equals(Double.TYPE)),
                "Field \"y\" has an improper type. Should be double");
        assertTrue(Arrays.stream(fields)
                        .filter(field -> field.getName().equals("y") && field.getType().equals(Double.TYPE))
                        .anyMatch(field -> Modifier.isPrivate(field.getModifiers())),
                "Field \"y\" has an improper access modifier. Should be private");
    }

    @Test
    void checkGettersAndSetters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method[] methods = Point.class.getDeclaredMethods();
        assertEquals(4, methods.length,
                "There should be 4 methods in Point class");

        assertTrue(Arrays.stream(methods).anyMatch(method -> method.getName().equals("getY")),
                "There is no method \"getY\" in Point class");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("getY"))
                        .anyMatch(method -> method.getParameterCount() == 0),
                "Wrong parameter count in the method \"getY\". Should be 0");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("getY"))
                        .filter(method -> method.getParameterCount() == 0)
                        .anyMatch(method -> method.getReturnType().equals(Double.TYPE)),
                "Wrong result type in the method \"getY\". Should be double");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("getY"))
                        .filter(method -> method.getParameterCount() == 0)
                        .filter(method -> method.getReturnType().equals(Double.TYPE))
                        .anyMatch(method -> Modifier.isPublic(method.getModifiers())),
                "Method \"getY\" has an improper access modifier. Should be public");

        assertTrue(Arrays.stream(methods).anyMatch(method -> method.getName().equals("setY")),
                "There is no method \"setY\" in Point class");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("setY"))
                        .anyMatch(method -> method.getParameterCount() == 1),
                "Wrong parameter count in the method \"setY\". Should be 1");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("setY"))
                        .filter(method -> method.getParameterCount() == 1)
                        .anyMatch(method -> method.getParameterTypes()[0].equals(Double.TYPE)),
                "Wrong parameter type in the method \"setY\". Should be double");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("setY"))
                        .filter(method -> method.getParameterCount() == 1)
                        .filter(method -> method.getParameterTypes()[0].equals(Double.TYPE))
                        .anyMatch(method -> method.getReturnType().equals(Void.TYPE)),
                "Wrong result type in the method \"setY\". Should be void");
        assertTrue(Arrays.stream(methods)
                        .filter(method -> method.getName().equals("setY"))
                        .filter(method -> method.getParameterCount() == 1)
                        .filter(method -> method.getParameterTypes()[0].equals(Double.TYPE))
                        .filter(method -> method.getReturnType().equals(Void.TYPE))
                        .anyMatch(method -> Modifier.isPublic(method.getModifiers())),
                "Method \"setY\" has an improper access modifier. Should be public");

        Point point = new Point();
        Method setY = Point.class.getDeclaredMethod("setY", Double.TYPE);
        Method getY = Point.class.getDeclaredMethod("getY");
        setY.invoke(point, Math.PI);
        Double result = (Double) getY.invoke(point);
        assertEquals(Math.PI, result, "Methods \"getY\" and \"setY\" work incorrectly");
    }

    @Test
    void checkConstructors() throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<?>[] constructors = Point.class.getDeclaredConstructors();

        assertEquals(2, constructors.length,
                "There should be 2 constructors in Point class");
        assertTrue(Arrays.stream(constructors)
                        .anyMatch(constructor -> constructor.getParameterCount() == 2),
                "Wrong parameter count in the constructor. Should be 2");
        assertTrue(Arrays.stream(constructors)
                        .filter(constructor -> constructor.getParameterCount() == 2)
                        .anyMatch(method -> method.getParameterTypes()[0].equals(Double.TYPE)
                                && method.getParameterTypes()[1].equals(Double.TYPE)),
                "Wrong parameter types in the constructor. Should be double on both parameters");

        Class<?> cl = Class.forName("com.tsystems.autotestuni.basic.Point");
        Class<?>[] types = {Double.TYPE , Double.TYPE};
        Constructor<?> constructor = cl.getConstructor(types);
        Object[] args = {Math.PI, Math.E};
        Point point = (Point) constructor.newInstance(args);
        Method getY = Point.class.getDeclaredMethod("getY");

        assertEquals(Math.PI, point.getX(),
                "The first parameter in the constructor should be assigned to \"x\" field");
        Double result = (Double) getY.invoke(point);
        assertEquals(Math.E, result,
                "The second parameter in the constructor should be assigned to \"y\" field");
    }
}
