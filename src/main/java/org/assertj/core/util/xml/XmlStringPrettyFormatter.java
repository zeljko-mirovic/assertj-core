/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.core.util.xml;

import java.io.StringWriter;
import java.io.Writer;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

/**
 * Format an XML String with indent = 2 space.
 * <p>
 * Very much inspired by http://stackoverflow.com/questions/139076/how-to-pretty-print-xml-from-java and
 * http://pastebin.com/XL7932aC
 * </p>
 */
public class XmlStringPrettyFormatter {

  private static final String FORMAT_ERROR = "Unable to format XML string";
  static final String PARSE_ERROR = "Unable to parse XML";

  public static String xmlPrettyFormat(String xmlStringToFormat) {
    if (xmlStringToFormat == null)
      throw new IllegalArgumentException("Expecting XML String not to be null");
    // convert String to an XML Document and then back to String but prettily formatted.
    return prettyFormat(XmlUtil.toXml(xmlStringToFormat), xmlStringToFormat.startsWith("<?xml"));
  }

  private static String prettyFormat(Node node, boolean keepXmlDeclaration) {

    try {
      DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
      DOMImplementationLS domImplementation = (DOMImplementationLS) registry.getDOMImplementation("LS");
      Writer stringWriter = new StringWriter();
      LSOutput formattedOutput = domImplementation.createLSOutput();
      formattedOutput.setCharacterStream(stringWriter);
      LSSerializer domSerializer = domImplementation.createLSSerializer();
      domSerializer.getDomConfig().setParameter("format-pretty-print", true);
      // Set this to true if the declaration is needed to be in the output.
      domSerializer.getDomConfig().setParameter("xml-declaration", keepXmlDeclaration);
      domSerializer.write(node, formattedOutput);
      return stringWriter.toString();
    } catch (Exception e) {
      throw new RuntimeException(FORMAT_ERROR, e);
    }
  }
  
  public static String prettyFormat(Node node){
      return prettyFormat(node, false);
  }

  private XmlStringPrettyFormatter() {
    // utility class
  }
}
