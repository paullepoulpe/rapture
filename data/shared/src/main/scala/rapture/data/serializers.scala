/*
  Rapture, version 2.0.0. Copyright 2010-2016 Jon Pretty, Propensive Ltd.

  The primary distribution site is
  
    http://rapture.io/

  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  
    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software distributed under the License is
  distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and limitations under the License.
 */

package rapture.data

import rapture.core._
import scala.annotation._

@implicitNotFound(
    "Cannot serialize type ${T} to ${D}. Please provide an implicit Serializer " +
      "of type ${T}.")
trait Serializer[T, -D] { ser =>
  def serialize(t: T): Any

  def contramap[T2](fn: T2 => T) = new Serializer[T2, D] {
    def serialize(t: T2): Any = ser.serialize(fn(t))
  }

}
