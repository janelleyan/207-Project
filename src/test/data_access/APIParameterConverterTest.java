package test.data_access;

import main.data_access.TriviaDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.TestingHelperFunctions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static test.TestingHelperFunctions.callPrivateMethod;
import static test.TestingHelperFunctions.instantiatePrivateClass;


class APIParameterConverterTest {

    private Object test;

    @BeforeEach
    void setup() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        test = instantiatePrivateClass(TriviaDB.class, "APIParameterConverter", new Class[]{}, new Object[]{});
    }

    @Test
    void constructorTest() throws NoSuchFieldException, IllegalAccessException {
        HashMap categories = (HashMap) TestingHelperFunctions.getPrivateVariableHelper(test, "categoryMap");
        HashMap difficulties = TestingHelperFunctions.getPrivateVariableHelper(test, "difficultyMap");
        HashMap types = TestingHelperFunctions.getPrivateVariableHelper(test, "typeMap");
        assertEquals(25, categories.size());
        assertEquals(4, difficulties.size());
        assertEquals(3, types.size());
        System.out.println(categories);
    }

    @Test
    void convertCategoryTestAllCategories() throws Exception {
        HashMap categories = (HashMap) TestingHelperFunctions.getPrivateVariableHelper(test, "categoryMap");
        for (Object key: categories.keySet()) {
            assertEquals(categories.get(key), callPrivateMethod(test, "convertCategory", new Class[]{String.class}, new Object[]{key.toString()}));
        }
    }

    @Test
    void convertCategoryTestInvalidCategory() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            try {
                callPrivateMethod(test, "convertCategory", new Class[]{String.class}, new Object[]{"%#*!*"});
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
        assertEquals("Category option \"%#*!*\" not recognized.", thrown.getMessage());
    }

    @Test
    void convertDifficultyTestAllDifficulties() throws Exception {
        HashMap categories = TestingHelperFunctions.getPrivateVariableHelper(test, "difficultyMap");
        for (Object key: categories.keySet()) {
//            assertEquals(categories.get(key), test.convertDifficulty(key.toString()));
            assertEquals(categories.get(key), callPrivateMethod(test, "convertDifficulty", new Class[]{String.class}, new Object[]{key.toString()}));
        }
    }

    @Test
    void convertDifficultyTestInvalidDifficulty() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            try {
                callPrivateMethod(test, "convertDifficulty", new Class[]{String.class}, new Object[]{"%#*!*"});
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
        assertEquals("Difficulty option \"%#*!*\" not recognized.", thrown.getMessage());
    }

    @Test
    void convertTypeTestAllTypes() throws Exception {
        HashMap categories = TestingHelperFunctions.getPrivateVariableHelper(test, "typeMap");
        for (Object key: categories.keySet()) {
            assertEquals(categories.get(key), callPrivateMethod(test, "convertType", new Class[]{String.class}, new Object[]{key.toString()}));
        }
    }

    @Test
    void convertTypeTestInvalidType() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            try {
                callPrivateMethod(test, "convertType", new Class[]{String.class}, new Object[]{"%#*!*"});
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
       assertEquals("Type option \"%#*!*\" not recognized.", thrown.getMessage());
    }
}