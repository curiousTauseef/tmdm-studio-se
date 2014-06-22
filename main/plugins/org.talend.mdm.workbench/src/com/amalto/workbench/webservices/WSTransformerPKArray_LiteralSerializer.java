// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation （1.1.2_01，编译版 R40）
// Generated source version: 1.1.2

package com.amalto.workbench.webservices;

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.encoding.xsd.XSDConstants;
import com.sun.xml.rpc.encoding.literal.*;
import com.sun.xml.rpc.encoding.literal.DetailFragmentDeserializer;
import com.sun.xml.rpc.encoding.simpletype.*;
import com.sun.xml.rpc.encoding.soap.SOAPConstants;
import com.sun.xml.rpc.encoding.soap.SOAP12Constants;
import com.sun.xml.rpc.streaming.*;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.ArrayList;

public class WSTransformerPKArray_LiteralSerializer extends LiteralObjectSerializerBase implements Initializable  {
    private static final QName ns1_wsTransformerPK_QNAME = new QName("", "wsTransformerPK");
    private static final QName ns2_WSTransformerPK_TYPE_QNAME = new QName("urn-com-amalto-xtentis-webservice", "WSTransformerPK");
    private CombinedSerializer ns2_myWSTransformerPK_LiteralSerializer;
    
    public WSTransformerPKArray_LiteralSerializer(QName type, String encodingStyle) {
        this(type, encodingStyle, false);
    }
    
    public WSTransformerPKArray_LiteralSerializer(QName type, String encodingStyle, boolean encodeType) {
        super(type, true, encodingStyle, encodeType);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        ns2_myWSTransformerPK_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", com.amalto.workbench.webservices.WSTransformerPK.class, ns2_WSTransformerPK_TYPE_QNAME);
    }
    
    public Object doDeserialize(XMLReader reader,
        SOAPDeserializationContext context) throws Exception {
        com.amalto.workbench.webservices.WSTransformerPKArray instance = new com.amalto.workbench.webservices.WSTransformerPKArray();
        Object member=null;
        QName elementName;
        List values;
        Object value;
        
        reader.nextElementContent();
        elementName = reader.getName();
        if ((reader.getState() == XMLReader.START) && (elementName.equals(ns1_wsTransformerPK_QNAME))) {
            values = new ArrayList();
            for(;;) {
                elementName = reader.getName();
                if ((reader.getState() == XMLReader.START) && (elementName.equals(ns1_wsTransformerPK_QNAME))) {
                    value = ns2_myWSTransformerPK_LiteralSerializer.deserialize(ns1_wsTransformerPK_QNAME, reader, context);
                    if (value == null) {
                        throw new DeserializationException("literal.unexpectedNull");
                    }
                    values.add(value);
                    reader.nextElementContent();
                } else {
                    break;
                }
            }
            member = new com.amalto.workbench.webservices.WSTransformerPK[values.size()];
            member = values.toArray((Object[]) member);
            instance.setWsTransformerPK((com.amalto.workbench.webservices.WSTransformerPK[])member);
        }
        else if(!(reader.getState() == XMLReader.END)) {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (Object)instance;
    }
    
    public void doSerializeAttributes(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        com.amalto.workbench.webservices.WSTransformerPKArray instance = (com.amalto.workbench.webservices.WSTransformerPKArray)obj;
        
    }
    public void doSerialize(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        com.amalto.workbench.webservices.WSTransformerPKArray instance = (com.amalto.workbench.webservices.WSTransformerPKArray)obj;
        
        if (instance.getWsTransformerPK() != null) {
            for (int i = 0; i < instance.getWsTransformerPK().length; ++i) {
                ns2_myWSTransformerPK_LiteralSerializer.serialize(instance.getWsTransformerPK()[i], ns1_wsTransformerPK_QNAME, null, writer, context);
            }
        }
    }
}