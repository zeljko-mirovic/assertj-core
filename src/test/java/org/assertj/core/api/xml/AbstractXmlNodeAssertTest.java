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
package org.assertj.core.api.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public abstract class AbstractXmlNodeAssertTest {

  @Test
  public void should_support_fluent_chaining() throws Exception {

    XmlNodeAssert originalAssertion = create_original_xml_assertion();
    
    XmlNodeAssert assertionToChain = invoke_successfully_method_under_test(originalAssertion);
    
    verify_chained_assertion(originalAssertion, assertionToChain);
    
  }

  protected abstract XmlNodeAssert create_original_xml_assertion();
  
  protected void verify_chained_assertion(XmlNodeAssert originalAssertion, XmlNodeAssert assertionToChain) {
    assertThat(originalAssertion).isSameAs(assertionToChain);
  }

  protected abstract XmlNodeAssert invoke_successfully_method_under_test(XmlNodeAssert originalAssertion);
  
}
