/*
 * Copyright (c) 2011 Jon Buffington. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.java33.vizpres.gwtsuite;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.java33.vizpres.client.ExampleGwtTC;
import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * From "Use GWTTestSuite padawan":
 * <p/>
 * GWTTestCase derived tests are slow. This is because the JUnitShell has to load the
 * module for each test (create the shell, hook into it, etc). GWTTestSuite mitigates
 * this by grouping all the tests that are for the same module
 * (those that return the same value for getModuleName) together and running them
 * via the same shell instance.
 * <p/>
 * We recommend to name your test suite GwtTestSuite.java so that the test filter picks it up,
 * but name the actual tests with a convention that Surefire will ignore by default - something
 * that does not start with GwtTest, and does not start or end with Test. For example MyClassTestGwt.java.
 * This way, gwt-maven-plugin picks up the Suite, and runs it, but does not also run individual
 * tests (and Surefire does not pick it up either)
 * <p/>
 * http://mojo.codehaus.org/gwt-maven-plugin/user-guide/testing.html
 */
public class GwtTestSuite extends GWTTestSuite {
  public static Test suite() {
    final TestSuite suite = new TestSuite("GWT application integration tests");
    suite.addTestSuite(ExampleGwtTC.class);
    return suite;
  }

}
