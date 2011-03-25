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

package com.java33.vizpres.server.rv

import javax.ws.rs.{GET, Path, Produces}
import com.java33.vizpres.shared.model.PercentValue


/**
 * Is the JAX-RS resource view into the data set hosted by the application server.
 *
 * @author Jon Buffington
 */
@Path("/dataset")
class DataSetResource {
  @GET
  @Produces(Array("text/html", "application/vnd.vizpres+xml", "application/xml"))
  def asHtml(): String = {
    <html>
      <head>
        <title>Test</title>
      </head> <body>
      <p>Hello.</p>
    </body>
    </html>.toString
  }

  /**
   * @return Returns an awe-inspiring list of random integral values from 0 to 100 inclusive.
   */
  @GET
  @Produces(Array("application/vnd.vizpres+json", "application/json"))
  def asJson(): java.util.List[PercentValue] = {
    import collection.JavaConversions._
    for (_ <- 1 to 10) yield randomPercent
  }

  private def randomPercent: PercentValue = new PercentValue(math.round((math.random * 100.0).asInstanceOf[Float]))
}
