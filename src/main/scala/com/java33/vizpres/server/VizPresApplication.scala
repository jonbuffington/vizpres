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

package com.java33.vizpres.server

import javax.ws.rs.core.Application
import rv.DataSetResource
import scala.collection._
import scala.collection.JavaConversions._


/**
 * Specifies the ReSTful resource views for the server application.
 *
 * @author Jon Buffington
 */
class VizPresApplication extends Application {
  override def getClasses = mutable.Set[java.lang.Class[_]](classOf[DataSetResource])
}
