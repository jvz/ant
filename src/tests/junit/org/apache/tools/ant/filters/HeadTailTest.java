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

package org.apache.tools.ant.filters;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.util.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** JUnit Testcases for TailFilter and HeadFilter
 */
/* I wrote the testcases in one java file because I want also to test the
 * combined behaviour (see end of the class).
*/
public class HeadTailTest {

    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    
    public BuildFileRule buildRule = new BuildFileRule();
    
    @Before
    public void setUp() {
        buildRule.configureProject("src/etc/testcases/filters/head-tail.xml");
    }

    @After
    public void tearDown() {
        buildRule.executeTarget("cleanup");
    }

    @Test
    public void testHead() throws IOException {
        buildRule.executeTarget("testHead");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(), "expected/head-tail.head.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(), "result/head-tail.head.test");
        assertTrue("testHead: Result not like expected", FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testHeadLines() throws IOException {
        buildRule.executeTarget("testHeadLines");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(), "expected/head-tail.headLines.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(), "result/head-tail.headLines.test");
        assertTrue("testHeadLines: Result not like expected", FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testHeadSkip() throws IOException {
        buildRule.executeTarget("testHeadSkip");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"expected/head-tail.headSkip.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"result/head-tail.headSkip.test");
        assertTrue("testHeadSkip: Result not like expected", FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testHeadLinesSkip() throws IOException {
        buildRule.executeTarget("testHeadLinesSkip");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"expected/head-tail.headLinesSkip.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"result/head-tail.headLinesSkip.test");
        assertTrue("testHeadLinesSkip: Result not like expected", FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testFilterReaderHeadLinesSkip() throws IOException {
        buildRule.executeTarget("testFilterReaderHeadLinesSkip");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),
            "expected/head-tail.headLinesSkip.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),
            "result/head-tail.filterReaderHeadLinesSkip.test");
        assertTrue("testFilterReaderHeadLinesSkip: Result not like expected",
                   FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testTail() throws IOException {
        buildRule.executeTarget("testTail");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"expected/head-tail.tail.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"result/head-tail.tail.test");
        assertTrue("testTail: Result not like expected", FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testTailLines() throws IOException {
        buildRule.executeTarget("testTailLines");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"expected/head-tail.tailLines.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"result/head-tail.tailLines.test");
        assertTrue("testTailLines: Result not like expected", FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testTailSkip() throws IOException {
        buildRule.executeTarget("testTailSkip");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"expected/head-tail.tailSkip.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"result/head-tail.tailSkip.test");
        assertTrue("testTailSkip: Result not like expected", FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testTailLinesSkip() throws IOException {
        buildRule.executeTarget("testTailLinesSkip");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"expected/head-tail.tailLinesSkip.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"result/head-tail.tailLinesSkip.test");
        assertTrue("testTailLinesSkip: Result not like expected", FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testFilterReaderTailLinesSkip() throws IOException {
        buildRule.executeTarget("testFilterReaderTailLinesSkip");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),
            "expected/head-tail.tailLinesSkip.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),
            "result/head-tail.filterReaderTailLinesSkip.test");
        assertTrue("testFilterReaderTailLinesSkip: Result not like expected",
                   FILE_UTILS.contentEquals(expected, result));
    }

    @Test
    public void testHeadTail() throws IOException {
        buildRule.executeTarget("testHeadTail");
        File expected = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"expected/head-tail.headtail.test");
        File result = FILE_UTILS.resolveFile(buildRule.getProject().getBaseDir(),"result/head-tail.headtail.test");
        assertTrue("testHeadTail: Result not like expected", FILE_UTILS.contentEquals(expected, result));
    }

}
