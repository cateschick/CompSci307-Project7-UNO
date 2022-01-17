package ooga.ReflectionHandlerTests;

import ooga.controller.ReflectionMethodHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;


public class ReflectionHandlerTest {

    private ReflectionMethodHandler reflectionHandler;
    private String CORRECT_METHOD_KEY = "correctMethodCall";
    private String INCORRECT_METHOD_KEY = "incorrectMethodCall";
    private String INCORRECT_PATH_KEY = "incorrectClassPath";
    private String CORRECT_PATH_KEY = "correctClassPath";
    private ResourceBundle testResourceBundle;

    @BeforeEach
    void start(){
        reflectionHandler = new ReflectionMethodHandler();
        testResourceBundle = ResourceBundle.getBundle("resources/reflection/reflectionHandlerTestMethods");
    }

    @Test
    void testCorrectMethodCallWithCorrectPathDoesNotThrowException() {
        Method m = reflectionHandler.handleMethod(testResourceBundle.getString(CORRECT_METHOD_KEY),
                testResourceBundle.getString(CORRECT_PATH_KEY));
        Assertions.assertDoesNotThrow(()-> m.invoke(ReflectionHandlerTest.this));
    }

    @Test
    void testCorrectMethodCallWithCorrectPath() throws InvocationTargetException, IllegalAccessException {
        Method m = reflectionHandler.handleMethod(testResourceBundle.getString(CORRECT_METHOD_KEY),
                testResourceBundle.getString(CORRECT_PATH_KEY));
        String testReturnString = (String) m.invoke(ReflectionHandlerTest.this);
        Assertions.assertEquals(testReturnString, testResourceBundle.getString(CORRECT_METHOD_KEY));
    }


    private String callThisCorrectMethod(){
        return testResourceBundle.getString(CORRECT_METHOD_KEY);
    }

    @Test
    void testIncorrectMethodCallWithCorrectPathCatchesNoSuchMethod(){
        reflectionHandler.handleMethod(testResourceBundle.getString(INCORRECT_METHOD_KEY),
                        testResourceBundle.getString(CORRECT_PATH_KEY));
        String errorMessage = reflectionHandler.getErrorMessage();
        Assertions.assertEquals(errorMessage, String.format("The method: %s could not be generated. Double check method you are trying to call's name"
                ,testResourceBundle.getString(INCORRECT_METHOD_KEY)));
    }

    @Test
    void testCorrectMethodCallWithIncorrectPathCatchesClassNotFound(){
        reflectionHandler.handleMethod(testResourceBundle.getString(CORRECT_METHOD_KEY),
                testResourceBundle.getString(INCORRECT_PATH_KEY));
        String errorMessage = reflectionHandler.getErrorMessage();
        Assertions.assertEquals(errorMessage, String.format("The class: %s could not be generated. Double check class you are trying to call's name",
                testResourceBundle.getString(INCORRECT_PATH_KEY)));
    }


}
