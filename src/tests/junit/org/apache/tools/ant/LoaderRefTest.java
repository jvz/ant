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

package org.apache.tools.ant;

import static org.apache.tools.ant.AntAssert.assertContains;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 */
public class LoaderRefTest {

	@Rule
	public BuildFileRule buildRule = new BuildFileRule();
	
	@Before
    public void setUp() {
        buildRule.configureProject("src/etc/testcases/core/loaderref/loaderref.xml");
    }

	@After
    public void tearDown() {
        buildRule.executeTarget("clean");
    }

    // override allowed on <available>
    @Test
	public void testBadRef() {
    	try {
    		buildRule.executeTarget("testbadref");
    		fail("BuildRule should have thrown an exception due to a bad classloader being specified");
    	} catch (BuildException ex) {
    		assertContains("Should fail due to ref not being a class loader", "does not reference a class loader", ex.getMessage());
    	}
    }
}

