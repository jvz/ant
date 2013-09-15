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

 package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.AntAssert;
import org.apache.tools.ant.BuildFileRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 */
public class AptTest {

    @Rule
    public BuildFileRule buildRule = new BuildFileRule();

    @Before
    public void setUp() {
        buildRule.configureProject("src/etc/testcases/taskdefs/apt.xml");
    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    @After
    public void tearDown() throws Exception {
        buildRule.executeTarget("clean");
    }

    @Test
    public void testApt() {
        buildRule.executeTarget("testApt");
    }

    @Test
    public void testAptFork() {
        buildRule.executeTarget("testAptFork");
    }

    @Test
    public void testAptForkFalse() {
        buildRule.executeTarget("testAptForkFalse");
        AntAssert.assertContains(Apt.WARNING_IGNORING_FORK, buildRule.getLog());
    }

    @Test
    public void testListAnnotationTypes() {
        buildRule.executeTarget("testListAnnotationTypes");

        AntAssert.assertContains("Set of annotations found:", buildRule.getLog());
        AntAssert.assertContains("Distributed", buildRule.getLog());
    }

    @Test
    public void testAptNewFactory() {
        buildRule.executeTarget("testAptNewFactory");
        assertProcessed();
    }

    @Test
    public void testAptNewFactoryFork() {
        buildRule.executeTarget("testAptNewFactoryFork");
        assertProcessed();
    }
    
    private void assertProcessed() {
        AntAssert.assertContains("DistributedAnnotationProcessor-is-go", buildRule.getLog());
        AntAssert.assertContains("[-Abuild.dir=", buildRule.getLog());
        AntAssert.assertContains("visiting DistributedAnnotationFactory", buildRule.getLog());
    }
}

