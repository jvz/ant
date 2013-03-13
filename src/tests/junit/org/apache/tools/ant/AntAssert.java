package org.apache.tools.ant;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Provides common assert functions for use across multiple tests, similar to the <tt>Assert</tt>s
 * within JUnit.
 */
public class AntAssert {

    /**
     * Assert that a string contains the given substring.
     * @param message the message to fail with if the substring is not present in the tagret string.
     * @param needle the string to search for.
     * @param haystack the string to search in.
     */
	public static void assertContains(String message, String needle, String haystack) {
		String formattedMessage = (message == null ? "" : message + " ");
		assertTrue(formattedMessage + String.format("expected message containing: <%s> but got: <%s>", needle, haystack), haystack.contains(needle));
	}

    /**
     * Assert that a string contains the given substring. A default failure message will be used if the target string
     * is not found.
     * @param needle the target string to search for.
     * @param haystack the string to search in.
     */
	public static void assertContains(String needle, String haystack) {
		assertContains("", needle, haystack);
	}

    /**
     * Assert that a string does not contain the given substring.
     * @param message the message to fail with if the substring is present in the tagret string.
     * @param needle the string to search for.
     * @param haystack the string to search in.
     */
    public static void assertNotContains(String message, String needle, String haystack) {
    	String formattedMessage = (message == null ? "" : message + " ");
    	assertFalse(formattedMessage + String.format("expected message not to contain: <%s> but got: <%s>", needle, haystack), haystack.contains(needle));
    }

    /**
     * Assert that a string does not contain the given substring. A default failure message will be used if the target
     * string is found.
     * @param needle the target string to search for.
     * @param haystack the string to search in.
     */
    public static void assertNotContains(String needle, String haystack) {
        assertNotContains("", needle, haystack);
    }
	

}
