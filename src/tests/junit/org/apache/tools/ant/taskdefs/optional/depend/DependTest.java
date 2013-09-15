/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.tools.ant.taskdefs.optional.depend;

import java.util.Hashtable;

import org.apache.tools.ant.AntAssert;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.FileSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Testcase for the Depend optional task.
 *
 */
public class DependTest {
    public static final String RESULT_FILESET = "result";

    public static final String TEST_BUILD_FILE
        = "src/etc/testcases/taskdefs/optional/depend/depend.xml";

    @Rule
    public BuildFileRule buildRule = new BuildFileRule();

    @Before
    public void setUp() {
        buildRule.configureProject(TEST_BUILD_FILE);
    }

    @After
    public void tearDown() {
        buildRule.executeTarget("clean");
    }

    /**
     * Test direct dependency removal
     */
    @Test
    public void testDirect() {
        buildRule.executeTarget("testdirect");
        Hashtable files = getResultFiles();
        assertEquals("Depend did not leave correct number of files", 3,
            files.size());
        assertTrue("Result did not contain A.class",
            files.containsKey("A.class"));
        assertTrue("Result did not contain D.class",
            files.containsKey("D.class"));
    }

    /**
     * Test dependency traversal (closure)
     */
    @Test
    public void testClosure() {
        buildRule.executeTarget("testclosure");
        Hashtable files = getResultFiles();
        assertTrue("Depend did not leave correct number of files", 
            files.size() <= 2);
        assertTrue("Result did not contain D.class",
            files.containsKey("D.class"));
    }

    /**
     * Test that inner class dependencies trigger deletion of the outer class
     */
    @Test
    public void testInner() {
        buildRule.executeTarget("testinner");
        assertEquals("Depend did not leave correct number of files", 0,
            getResultFiles().size());
    }

    /**
     * Test that multi-leve inner class dependencies trigger deletion of
     * the outer class
     */
    @Test
    public void testInnerInner() {
        buildRule.executeTarget("testinnerinner");
        assertEquals("Depend did not leave correct number of files", 0,
            getResultFiles().size());
    }

    /**
     * Test that an exception is thrown when there is no source
     */
    @Test
    public void testNoSource() {
        try {
            buildRule.executeTarget("testnosource");
            fail("Build exception expected: No source specified");
        } catch(BuildException ex) {
            AntAssert.assertContains("srcdir attribute must be set", ex.getMessage());
        }
    }

    /**
     * Test that an exception is thrown when the source attribute is empty
     */
    @Test
    public void testEmptySource() {
        try {
            buildRule.executeTarget("testemptysource");
            fail("Build exception expected: No source specified");
        } catch(BuildException ex) {
            AntAssert.assertContains("srcdir attribute must be non-empty", ex.getMessage());
        }
    }

    /**
     * Read the result fileset into a Hashtable
     *
     * @return a Hashtable containing the names of the files in the result
     * fileset
     */
    private Hashtable getResultFiles() {
        FileSet resultFileSet = (FileSet) buildRule.getProject().getReference(RESULT_FILESET);
        DirectoryScanner scanner = resultFileSet.getDirectoryScanner(buildRule.getProject());
        String[] scannedFiles = scanner.getIncludedFiles();
        Hashtable files = new Hashtable();
        for (int i = 0; i < scannedFiles.length; ++i) {
            files.put(scannedFiles[i], scannedFiles[i]);
        }
        return files;
    }


    /**
     * Test mutual dependency between inner and outer do not cause both to be
     * deleted
     */
    @Test
    public void testInnerClosure() {
        buildRule.executeTarget("testinnerclosure");
        assertEquals("Depend did not leave correct number of files", 4,
            getResultFiles().size());
    }

    /**
     * Test the operation of the cache
     */
    @Test
    public void testCache() {
        buildRule.executeTarget("testcache");
    }

    /**
     * Test the detection and warning of non public classes
     */
    @Test
    public void testNonPublic() {
        buildRule.executeTarget("testnonpublic");
        String log = buildRule.getLog();
        assertTrue("Expected warning about APrivate",
            log.indexOf("The class APrivate in file") != -1);
        assertTrue("but has not been deleted because its source file "
            + "could not be determined",
            log.indexOf("The class APrivate in file") != -1);
    }

}
