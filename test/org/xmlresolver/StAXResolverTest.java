/*
 * StAXResolverTest.java
 * JUnit based test
 *
 * Created on January 2, 2007, 9:14 AM
 */

package org.xmlresolver;

import java.io.FileInputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import junit.framework.*;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author ndw
 */
public class StAXResolverTest extends TestCase {
    public StAXResolverTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of resolve method, of class org.xmlresolver.Resolver.
     */
    public void testResolver() throws Exception {
        System.out.println("testResolver");
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        Catalog catalog = new Catalog("documents/catalog.xml");
        StAXResolver resolver = new StAXResolver(catalog);
        factory.setXMLResolver(new SResolver(resolver));
        
        String xmlFile = "documents/dtdtest.xml";
        XMLStreamReader reader = factory.createXMLStreamReader(xmlFile, new FileInputStream(xmlFile));

        while (reader.hasNext()) {
            int event = reader.next();
        }
        reader.close();
        
        // If we didn't get an exception, we passed!
    }
    
    class SResolver implements XMLResolver {
        private StAXResolver resolver = null;

        public SResolver(StAXResolver resolver) {
            this.resolver = resolver;
        }

        public Object resolveEntity(String publicID, String systemID, String baseURI, String namespace) throws XMLStreamException {
            System.out.println("resolveEntity: " + publicID + "," + systemID + "," + baseURI + ": " + namespace);
            return resolver.resolveEntity(publicID, systemID, baseURI, namespace);
        }
        
    }
}
