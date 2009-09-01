package com.amalto.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.security.Principal;
import java.security.acl.Group;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.jxpath.AbstractFactory;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;
import org.apache.commons.lang.StringEscapeUtils;
import org.talend.mdm.commmon.util.core.EUUIDCustomType;
import org.talend.mdm.commmon.util.core.ICoreConstants;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.amalto.core.ejb.ItemPOJO;
import com.amalto.core.ejb.ItemPOJOPK;
import com.amalto.core.ejb.ObjectPOJO;
import com.amalto.core.ejb.local.DroppedItemCtrlLocal;
import com.amalto.core.ejb.local.DroppedItemCtrlLocalHome;
import com.amalto.core.ejb.local.ItemCtrl2Local;
import com.amalto.core.ejb.local.ItemCtrl2LocalHome;
import com.amalto.core.ejb.local.ServiceLocalHome;
import com.amalto.core.ejb.local.TransformerCtrlLocal;
import com.amalto.core.ejb.local.TransformerCtrlLocalHome;
import com.amalto.core.ejb.local.XmlServerSLWrapperLocal;
import com.amalto.core.ejb.local.XmlServerSLWrapperLocalHome;
import com.amalto.core.objects.backgroundjob.ejb.local.BackgroundJobCtrlLocal;
import com.amalto.core.objects.backgroundjob.ejb.local.BackgroundJobCtrlLocalHome;
import com.amalto.core.objects.configurationinfo.ejb.local.ConfigurationInfoCtrlLocal;
import com.amalto.core.objects.configurationinfo.ejb.local.ConfigurationInfoCtrlLocalHome;
import com.amalto.core.objects.datacluster.ejb.DataClusterPOJOPK;
import com.amalto.core.objects.datacluster.ejb.local.DataClusterCtrlLocal;
import com.amalto.core.objects.datacluster.ejb.local.DataClusterCtrlLocalHome;
import com.amalto.core.objects.datamodel.ejb.DataModelPOJO;
import com.amalto.core.objects.datamodel.ejb.DataModelPOJOPK;
import com.amalto.core.objects.datamodel.ejb.local.DataModelCtrlLocal;
import com.amalto.core.objects.datamodel.ejb.local.DataModelCtrlLocalHome;
import com.amalto.core.objects.menu.ejb.local.MenuCtrlLocal;
import com.amalto.core.objects.menu.ejb.local.MenuCtrlLocalHome;
import com.amalto.core.objects.role.ejb.local.RoleCtrlLocal;
import com.amalto.core.objects.role.ejb.local.RoleCtrlLocalHome;
import com.amalto.core.objects.routing.v2.ejb.local.RoutingEngineV2CtrlLocal;
import com.amalto.core.objects.routing.v2.ejb.local.RoutingEngineV2CtrlLocalHome;
import com.amalto.core.objects.routing.v2.ejb.local.RoutingOrderV2CtrlLocal;
import com.amalto.core.objects.routing.v2.ejb.local.RoutingOrderV2CtrlLocalHome;
import com.amalto.core.objects.routing.v2.ejb.local.RoutingRuleCtrlLocal;
import com.amalto.core.objects.routing.v2.ejb.local.RoutingRuleCtrlLocalHome;
import com.amalto.core.objects.storedprocedure.ejb.local.StoredProcedureCtrlLocal;
import com.amalto.core.objects.storedprocedure.ejb.local.StoredProcedureCtrlLocalHome;
import com.amalto.core.objects.synchronization.ejb.local.SynchronizationItemCtrlLocal;
import com.amalto.core.objects.synchronization.ejb.local.SynchronizationItemCtrlLocalHome;
import com.amalto.core.objects.synchronization.ejb.local.SynchronizationObjectCtrlLocal;
import com.amalto.core.objects.synchronization.ejb.local.SynchronizationObjectCtrlLocalHome;
import com.amalto.core.objects.synchronization.ejb.local.SynchronizationPlanCtrlLocal;
import com.amalto.core.objects.synchronization.ejb.local.SynchronizationPlanCtrlLocalHome;
import com.amalto.core.objects.transformers.v2.ejb.TransformerV2POJOPK;
import com.amalto.core.objects.transformers.v2.ejb.local.TransformerV2CtrlLocal;
import com.amalto.core.objects.transformers.v2.ejb.local.TransformerV2CtrlLocalHome;
import com.amalto.core.objects.transformers.v2.util.TransformerCallBack;
import com.amalto.core.objects.transformers.v2.util.TransformerContext;
import com.amalto.core.objects.transformers.v2.util.TypedContent;
import com.amalto.core.objects.universe.ejb.UniversePOJO;
import com.amalto.core.objects.universe.ejb.local.UniverseCtrlLocal;
import com.amalto.core.objects.universe.ejb.local.UniverseCtrlLocalHome;
import com.amalto.core.objects.versioning.ejb.local.VersioningSystemCtrlLocal;
import com.amalto.core.objects.versioning.ejb.local.VersioningSystemCtrlLocalHome;
import com.amalto.core.objects.view.ejb.local.ViewCtrlLocal;
import com.amalto.core.objects.view.ejb.local.ViewCtrlLocalHome;
import com.amalto.core.schematron.validation.Schema;
import com.amalto.core.schematron.validation.SchemaFactory;
import com.amalto.core.schematron.validation.Validator;
import com.amalto.core.schematron.validation.Violation;
import com.sun.org.apache.xpath.internal.XPathAPI;
import com.sun.org.apache.xpath.internal.objects.XObject;

/**
 * @author Bruno GRieder
 *
 */
@SuppressWarnings("deprecation")
public final class Util {


	/*********************************************************************
	 *     System wide properties
	 *********************************************************************/
	/**
	 * Home Caches spped up execution but make deployment debugging
	 */
	public final static boolean USE_HOME_CACHES = "true".equals(System.getProperty("com.amalto.use.home.caches"));
	
	/**
	 * helper class
	 */
	public static List<String> getRuntimeServiceJndiList(boolean withPrefix) {
		List<String> serviceJndiList = new ArrayList<String>();
		String serviceJndiPrefix="amalto/local/service";
		try {
		InitialContext ctx = new InitialContext();
		NamingEnumeration<NameClassPair> list = ctx.list(serviceJndiPrefix);
			while (list.hasMore()) {
			    NameClassPair nc;
				
				nc = list.next();
				
			    if(withPrefix){
			    	serviceJndiList.add(serviceJndiPrefix+"/"+nc.getName());
			    }else{
			    	serviceJndiList.add(nc.getName());
			    }
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return serviceJndiList;
	}
	
	public static List<String> getRuntimeServiceJndiList(){
		return getRuntimeServiceJndiList(true);
	}
	/*********************************************************************
	 *     Xml server Access Stuff
	 *********************************************************************/
	
	/*
    public static  XmlServerSFWrapperLocal getXmlServerSFWrapperLocal() throws EJBException{
       	InitialContext initialContext = null;
       	String jndiName = "java:comp/env/ejb/XmlServerSFWrapperLocal";
        try {
        	initialContext= new InitialContext();
        	XmlServerSFWrapperLocalHome home = (XmlServerSFWrapperLocalHome)initialContext.lookup(jndiName);
        	return home.create();
        } catch(Exception e) {
    		String err = "Unable to lookup \""+jndiName+"\""
			+ ": " + e.getClass().getName() + ": "+ e.getLocalizedMessage();
    		org.apache.log4j.Category.getInstance(Util.class).error(err);
    		throw new EJBException(err);        	
        } finally {
           try {initialContext.close();} catch(Exception e){};
        }
    }
    */
    
	/**
	 * Retrieves the XML Server wrapper<br>
	 * Deprecated: use a direct lookup a shown below.
	 * <code>
	 * XmlServerSLWrapperLocal server = null;
			try {
				server  =  ((XmlServerSLWrapperLocalHome)new InitialContext().lookup(XmlServerSLWrapperLocalHome.JNDI_NAME)).create();
			} catch (Exception e) {
				String err = "Unable to access the XML Server wrapper";
				org.apache.log4j.Logger.getLogger(this.getClass()).error(err);
				throw new RuntimeException(err);
			}
	 *</code>
	 *@deprecated - use a direct look up in your class. Not a call to this static method
	 */
//    public static  XmlServerSLWrapperLocal getXmlServerSLWrapperLocal() throws EJBException{
//       	InitialContext initialContext = null;
//       	String jndiName = "amalto/local/xmldb/xmlserverslwrapper";
//        try {
//        	initialContext= new InitialContext();
//        	XmlServerSLWrapperLocalHome home = (XmlServerSLWrapperLocalHome)initialContext.lookup(jndiName);
//        	return home.create();
//        } catch(Exception e) {
//    		String err = "Unable to lookup \""+jndiName+"\""
//			+ ": " + e.getClass().getName() + ": "+ e.getLocalizedMessage();
//    		throw new EJBException(err);        	
//        } finally {
//           try {initialContext.close();} catch(Exception e){};
//        }
//    }
    

	/*********************************************************************
	 *    Parsing Stuff
	 *********************************************************************/

    
    
    public static Document parse(String xmlString) throws ParserConfigurationException,IOException, SAXException{
    	return parse(xmlString,null);
    }
    	
    public static Document parse(String xmlString, String schema) throws ParserConfigurationException,IOException, SAXException{

		//parse
		Document d=null;
		SAXErrorHandler seh = new SAXErrorHandler();

        //initialize the sax parser which uses Xerces
		System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
				"org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//Schema validation based on schemaURL
		factory.setNamespaceAware(true);
		factory.setValidating((schema!=null));
		factory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");
		if (schema != null) {
		    factory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaSource",
				new InputSource(new StringReader(schema))
				);
		}
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		builder.setErrorHandler(seh);
		d = builder.parse(new InputSource(new StringReader(xmlString)));

		//check if dcument parsed correctly against the schema
		if (schema != null) {
			String errors = seh.getErrors();
			if (!errors.equals("")) {
				String err = "Document did not parse against schema: \n" + errors+"\n"
					+xmlString.substring(0, Math.min(100, xmlString.length()));
				throw new SAXException(err);
			}
		}
		return d;
    }
    public static void schematronValidate(Element element, String schematron, String concept)throws Exception{
        
    	InputSource is = new InputSource ( new ByteArrayInputStream(schematron.getBytes("utf-8")) );
        SchemaFactory schf = SchemaFactory.lookup( SchemaFactory.NAMESPACE_SCHEMATRON );
        Schema sch = schf.compileSchema( is );
        Validator validator = sch.newValidator();

        // set preprocessor parameters 
         //if (args.length > 1)
           // validator.setProperty(Validator.PROPERTY_PHASE, "New");
         
         List violations = null;

         //violations = validator.validate( tbean );
         violations=validator.validate(element);
 
         // everything ok?
         if (violations == null || violations.size()==0) 
         {
           System.out.println("\nValidation ok, no messages generated");
         }
         else {
           System.out.println("Validation encountered errors. Messages :");
           Iterator viter = violations.iterator();
           StringBuffer sb =new StringBuffer();
           while (viter.hasNext ())
           {
             Violation v = (Violation) viter.next();
             //sb.append("Violation path: " + v.getPath() + ", message: " + v.getMessage() );
             sb.append( v.getMessage()+"\n" );
           }
           if(sb.toString().length()>0)
           throw new XtentisException(sb.toString());
         }

    }
    public static Document validate(Element element, String schema) 
    	throws Exception{

    	org.apache.log4j.Logger.getLogger(Util.class).trace("validate() "+element.getLocalName());
    	    	
		//parse
		Document d=null;
		SAXErrorHandler seh = new SAXErrorHandler();

        //initialize the sax parser which uses Xerces
		System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
				"org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//Schema validation based on schemaURL
		factory.setNamespaceAware(true);
		factory.setValidating((schema!=null));
		factory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");
		if (schema != null) {
		    factory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaSource",
				new InputSource(new StringReader(schema))
				);
		}
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		builder.setErrorHandler(seh);
		String xmlstr=Util.nodeToString(element);
       	//if element is null, remove it aiming added 
       	//see 7828
		xmlstr=xmlstr.replaceAll("<\\w+?/>", "");		
		d = builder.parse(new InputSource(new StringReader(xmlstr)));
		
		//check if dcument parsed correctly against the schema
		if (schema != null) {
			String errors = seh.getErrors();
			if (!errors.equals("")) {
				String xmlString = Util.nodeToString(element); 
				String err = "The item "+element.getLocalName()+" did not validate against the model: \n" + errors+"\n"
					+xmlString;	//.substring(0, Math.min(100, xmlString.length()));
				throw new SAXException(err);
			}
		}
		//schematron validate see 0008753: Implement Schematron
		String concept=element.getLocalName();
		Node schemaRoot=parse(schema).getDocumentElement();
	   	Element rootNS=Util.getRootElement("nsholder",schemaRoot.getNamespaceURI(),"xsd");	
	   	String xpath="//xsd:element[@name='" + concept + "']//xsd:appinfo[@source='"+ ICoreConstants.X_Schematron+"']/text()";
		NodeList tsList=Util.getNodeList(schemaRoot,xpath,rootNS.getNamespaceURI(),"xsd");
		
		//String SCHEMATRON=ICoreConstants.SCHEMATRON_TAG;//"<schema xmlns=\"http://www.ascc.net/xml/schematron\" ns=\"http://xml.apache.cocoon/xmlform\">";
		for(int i=0; i<tsList.getLength();i++){
			String sch= nodeToString(tsList.item(i), true);
			sch=StringEscapeUtils.unescapeXml(sch);
			//String sch= tsList.item(i).getNodeValue();
			//sch=sch.replaceAll("<"+ICoreConstants.SCHEMATRON_RULE+">", SCHEMATRON);
			//sch=sch.replaceAll("</"+ICoreConstants.SCHEMATRON_RULE+">", "</schema>");
			if(sch ==null || sch.trim().length()==0) continue;
			StringBuffer sb=new StringBuffer();
			sb.append("<schema xmlns=\"http://www.ascc.net/xml/schematron\" >");
			sb.append(sch);
			sb.append("</schema>");
			schematronValidate(element, sb.toString(), concept);
		}
		//end
		
		return d;
    }
    
    

    public static String[] getTextNodes(Node contextNode, String xPath) throws TransformerException{
    	return getTextNodes(contextNode,xPath,contextNode);
    }
    
    public static String[] getTextNodes(Node contextNode, String xPath,Node namespaceNode) throws TransformerException{
        String[] results=null;;
        
        //test for hard-coded values
        if (xPath.startsWith("\"") && xPath.endsWith("\""))
            return new String[] { xPath.substring(1, xPath.length()-1)};
            
        //test for incomplete path (elements missing /text())
        if (!xPath.matches(".*@[^/\\]]+"))  //attribute
        	if (! xPath.endsWith(")")) //function
        		xPath+="/text()";
               
        try {
	        XObject xo = XPathAPI.eval(contextNode, xPath,namespaceNode);
	        if (xo.getType() == XObject.CLASS_NODESET) {
	            NodeList l = xo.nodelist();
	            int len = l.getLength();
	            results = new String[len];
	            for (int i = 0; i < len; i++) {
	                Node n = l.item(i);
	                results[i] = n.getNodeValue();
	            }
	        } else {
	            results = new String[]{xo.toString()};
	        }
		} catch (TransformerException e) {
			String err = "Unable to get the text node(s) of "+xPath
					+": " + e.getClass().getName() + ": "
					+ e.getLocalizedMessage();
			throw new TransformerException(err);
		}
		return results;

    }    
    
    
    public static String getFirstTextNodeNotNull(Node contextNode, String xPath) throws TransformerException {
    	String val=  getFirstTextNode(contextNode,xPath,contextNode);
    	return val == null ? "" : val;
    }
    
    public static String getFirstTextNode(Node contextNode, String xPath,Node namespaceNode) throws TransformerException{
    	String[] res = getTextNodes(contextNode,xPath,namespaceNode);
    	if (res.length == 0) 
    		return null;
    	return res[0]; 
    }

    public static String getFirstTextNode(Node contextNode, String xPath) throws TransformerException{
    	return getFirstTextNode(contextNode,xPath,contextNode);
    }

    
    public static String[] getAttributeNodeValue(Node contextNode, String xPath, Node namespaceNode) throws TransformerException{
        String[] results;
        
        //test for hard-coded values
        if (xPath.startsWith("\"") && xPath.endsWith("\""))
            return new String[] { xPath.substring(1, xPath.length()-1)};
            
        //test for incomplete path
        //if (! xPath.endsWith(")")) xPath+="/text()";
        
        try {
	        XObject xo = XPathAPI.eval(contextNode, xPath,namespaceNode);
	        if (xo.getType() == XObject.CLASS_NODESET) {
	            NodeList l = xo.nodelist();
	            int len = l.getLength();
	            results = new String[len];
	            for (int i = 0; i < len; i++) {
	                Node n = l.item(i);
	                results[i] = n.getNodeValue();
	            }
	        } else {
	            results = new String[]{xo.toString()};
	        }
		} catch (TransformerException e) {
			String err = "Unable to get the text node(s) of "+xPath
					+": " + e.getClass().getName() + ": "
					+ e.getLocalizedMessage();
			throw new TransformerException(err);
		}
		return results;

    }    
    
    
    /**
     * Check if a local or remote
     * component for the JNDI Name exists 
     * @param RMIProviderURL
     * @param jndiName
     */
    public static boolean existsComponent(String RMIProviderURL,String jndiName) throws XtentisException{
    	
        if ((RMIProviderURL == null) || (RMIProviderURL.equals("")))
        	RMIProviderURL = "LOCAL";
                
    	Hashtable<String,String> env = null;
    	if (!"LOCAL".equals(RMIProviderURL)) {	    	
	    	//FIXME: JBoss specific
	        env = new Hashtable<String,String>(3);
	        env.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
	        env.put("java.naming.factory.url.pkgs","org.jboss.naming:org.jnp.interfaces");
	        env.put(Context.PROVIDER_URL, RMIProviderURL);      
    	}

    	Object home = null;
    	InitialContext initialContext = null;
        try {
        	initialContext= new InitialContext(env);
        	home = initialContext.lookup(jndiName);
        } catch(NamingException e) {} finally {
           try {initialContext.close();} catch(Exception e){};
        }
        
        return (home!=null);		
    }
    
    /**
     * Retrieve the local (if RMIProvideURL is null) or remote
     * component for the particular local JNDI Name
     * (c) bgrieder - lend to Amalto
     * @param RMIProviderURL 
     * @param jndiName
     */
    public static Object retrieveComponent(String RMIProviderURL,String jndiName) throws XtentisException{
    	
        if ((RMIProviderURL == null) || (RMIProviderURL.equals("")))
        	RMIProviderURL = "LOCAL";
                
    	Hashtable<String,String> env = null;
    	if (!"LOCAL".equals(RMIProviderURL)) {	    	
	    	//FIXME: JBoss specific
	        env = new Hashtable<String,String>(3);
	        env.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
	        env.put("java.naming.factory.url.pkgs","org.jboss.naming:org.jnp.interfaces");
	        env.put(Context.PROVIDER_URL, RMIProviderURL);
	        
    	}

    	Object home = null;
    	InitialContext initialContext = null;
        try {
        	initialContext= new InitialContext(env);
        	home = initialContext.lookup(jndiName);
        } catch(NamingException e) {
    		String err = "Unable to lookup \""+jndiName+"\""
			+ ": " + e.getClass().getName() + ": "+ e.getLocalizedMessage();
    		throw new XtentisException(err);        	
        } finally {
           try {initialContext.close();} catch(Exception e){};
        }
    	
        //find create and call it
        Method[] m = home.getClass().getMethods();
        Method create = null;
        for (int i = 0; i < m.length; i++) {
			if ("create".equals(m[i].getName())) {
				create = m[i];
				break;
			}
		}
        if (create == null) {
        	String err = "Unable to find create method on home of component \""+jndiName+"\"";
    		throw new XtentisException(err);        	
        }
        Object component = null;
		try {
			component = create.invoke(home,(Object[])null);
        } catch(Exception e) {
    		String err = "Unable to call the create method of remote component \""+jndiName+"\""
			+": "+e.getClass().getName() + ": "+ e.getLocalizedMessage();
    		throw new XtentisException(err);
        }
        
        return component;

    }
    
    
    /**
     * Get the method of a component by its name
     * (c) bgrieder - lend to Amalto
     * @param component
     * @param methodName
     * @return the Method - niull if not found
     * @throws EJBException
     */
    public static Method getMethod(Object component, String methodName) throws EJBException{
		Method[] methods = component.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methodName.equals(methods[i].getName())) {
				return methods[i];
			}
		}
		return null;
    }
    
    
    /**
     * Dumps info on a class
     * @param clazz
     */
    public static void dumpClass(Class<Object> clazz) {
    	org.apache.log4j.Logger.getLogger(Util.class).debug("dumpClass() CLASS "+clazz.getName());
		Class<Object>[] interfaces = clazz.getInterfaces();
		if (interfaces!=null) {
			for (int i = 0; i < interfaces.length; i++) {
				org.apache.log4j.Logger.getLogger(Util.class).debug("()  Class Interface "+i+": "+interfaces[i].getName());		
			}
		}
		Class<Object>[] classes = clazz.getClasses();
		if (classes!=null) {
			for (int i = 0; i < classes.length; i++) {
				org.apache.log4j.Logger.getLogger(Util.class).debug("()  Classes "+i+": "+classes[i].getName());		
			}
		}	    		
		org.apache.log4j.Logger.getLogger(Util.class).debug("()  Super Class "+clazz.getSuperclass().getName());
		org.apache.log4j.Logger.getLogger(Util.class).debug("()  Generic Super Class "+clazz.getGenericSuperclass());
		Type[] types = clazz.getGenericInterfaces();
		if (types!=null) {
			for (int i = 0; i < types.length; i++) {
				org.apache.log4j.Logger.getLogger(Util.class).debug("()  Generic Interface"+i+": "+types[i]);		
			}
		}
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < types.length; i++) {
			System.out.println("Types "+types[i]);
		}
		for (int i = 0; i < methods.length; i++) {
			org.apache.log4j.Logger.getLogger(Util.class).debug("Method "+methods[i].getName());
		}
	}

    
    
    
    /**
     * XSLT Utils
     * @param xslt
     * @return teh Top Business Concept
     */
    public static String getTopBusinessConceptName(Document xslt) throws TransformerException{
    	String prefix = xslt.getDocumentElement().getPrefix();
    	//String uri = xslt.getDocumentElement().getNamespaceURI();
    	String xpath = prefix+":template[@match='/']/"+prefix+":apply-templates/@mode";
    	return Util.getFirstTextNode(xslt.getDocumentElement(),xpath);
    	/*
    	String s = getAttributeNodeValue(
    			xslt.getDocumentElement(),
				"xs:template[@match='/']/xs:apply-templates/@mode",
				getRootElement("nsholder","http://www.w3.org/1999/XSL/Transform","xs")
				)[0]; 
    	return s;
    	*/
    }
    public static String getTopBusinessConceptPath(Document xslt) throws TransformerException{
    	String prefix = xslt.getDocumentElement().getPrefix();
    	//String uri = xslt.getDocumentElement().getNamespaceURI();
    	String xpath = prefix+":template[@match='/']/"+prefix+":apply-templates/@select";
    	return Util.getFirstTextNode(xslt.getDocumentElement(),xpath);
    	/*
    	String s = getAttributeNodeValue(
    			xslt.getDocumentElement(),
				"xs:template[@match='/']/xs:apply-templates/@select",
				getRootElement("nsholder","http://www.w3.org/1999/XSL/Transform","xs")
				)[0]; 
    	return s;
    	*/
    }
    
    
    public static Document setTopBusinessConceptPath(Document xslt) throws XtentisException{
    	try {
	    	String prefix = xslt.getDocumentElement().getPrefix();
	    	//String uri = xslt.getDocumentElement().getNamespaceURI();
	    	String xpath = prefix+":template[@match='/']/"+prefix+":apply-templates";
	    	NodeList l = Util.getNodeList(xslt.getDocumentElement(),xpath);    	
	    	if (l==null)
	    		throw new XtentisException("The root template match (match='/') of the transform cannot be found");
	    	int len = l.getLength();
	    	if (len!=1)
	    		throw new XtentisException("The root template match (match='/') of the transform is not univoque");
	    	((Element)l.item(0)).setAttribute("select",".");
	    	return xslt;
    	} catch (Exception e) {
      	    String err = "Unable to process the transform"
    		+": "+e.getClass().getName()+": "+e.getLocalizedMessage();
      	    org.apache.log4j.Logger.getLogger(Util.class).error(err,e);
      	    throw new XtentisException(err);
    	}
    	
    }
    
    public static XSDKey getBusinessConceptKey(Document xsd, String businessConceptName) throws TransformerException{
    	try {
        	String selector = null;
        	String[] fields = null;
    		selector = Util.getTextNodes(
    			xsd.getDocumentElement(),
    			"xsd:element/xsd:unique[@name='"+businessConceptName+"']/xsd:selector/@xpath",
				getRootElement("nsholder",xsd.getDocumentElement().getNamespaceURI(),"xsd")
				)[0];
    	
    		fields =  Util.getTextNodes(
    			xsd.getDocumentElement(),
    			"xsd:element/xsd:unique[@name='"+businessConceptName+"']/xsd:field/@xpath",
				getRootElement("nsholder",xsd.getDocumentElement().getNamespaceURI(),"xsd")
				);
        	return new XSDKey(selector,fields);
    	} catch(TransformerException e) {
    	    String err = "Unable to get the keys for the Business Concept "+businessConceptName+": "+e.getLocalizedMessage();
    		throw new TransformerException(err);
    	}
    
    }
    
    public static boolean isUUIDType(String type){
    	return EUUIDCustomType.allTypes().contains(type);
    	
    }
    
    public static UUIDItemContent processUUID(Element root, Document schema, String dataCluster, String concept,XSDKey conceptKey, String[] itemKeyValues) throws Exception{
		//generate uuid 
		Element conceptRoot = (Element)root.cloneNode(true);			
		Util.generateUUIDForElement(schema, dataCluster,concept, conceptRoot);			
		//get concept key values
		for(int j=0; j<conceptKey.getFields().length; j++){
			for(int i=0; i<conceptRoot.getChildNodes().getLength(); i++){
				Node node= conceptRoot.getChildNodes().item(i);
				String name=node.getLocalName();
				if(node.getNodeType() != Node.ELEMENT_NODE) continue;
				String key=conceptKey.getFields()[j];
				if(name.equals(key) && itemKeyValues[j]==null){
					itemKeyValues[j]=node.getTextContent();
					break;
				}
			}										
		}			
		if(itemKeyValues[0]==null){
			throw(new RemoteException("putItem()  itemKeyValues is null"));
		}
		String projection=Util.getXMLStringFromNode(conceptRoot);
		projection = projection.replaceAll("<\\?xml.*?\\?>","");			
		UUIDItemContent  content =new UUIDItemContent();
		content.setItemContent(projection);
		content.setItemKeyValues(itemKeyValues);
		return content;
    }
    
    public static NodeList getUUIDNodes(Document schema, String concept) throws Exception{
		Element rootNS=Util.getRootElement("nsholder",schema.getDocumentElement().getNamespaceURI(),"xsd");	
		String xpath="//xsd:element[@name='"+concept+"']//xsd:element[@type='"+EUUIDCustomType.AUTO_INCREMENT+"' or @type='"+EUUIDCustomType.UUID+"']";
		NodeList uuidLists=Util.getNodeList(schema.getDocumentElement(),xpath,rootNS.getNamespaceURI(),"xsd");
		return uuidLists;
    }
    
    public static String[] getTargetSystemsFromSchema(Document schema, String concept) throws Exception{
    	String[] targetSystems=null;
    	
    	Element rootNS=Util.getRootElement("nsholder",schema.getDocumentElement().getNamespaceURI(),"xsd");	
		String xpath="//xsd:element[@name='"+concept+"']//xsd:appinfo[@source='X_TargetSystem']";
		NodeList tsList=Util.getNodeList(schema.getDocumentElement(),xpath,rootNS.getNamespaceURI(),"xsd");
		
		if(tsList!=null){
			targetSystems=new String[tsList.getLength()];
			for (int i = 0; i < tsList.getLength(); i++) {
				Node tsNode=tsList.item(i);
				targetSystems[i]=tsNode.getTextContent();
			}
		}
		
		return targetSystems;
	}
    
    private static void generateUUIDForElement(Document schema,String dataCluster,String concept, Element conceptRoot) throws Exception{
    	NodeList uuidLists=getUUIDNodes(schema, concept);
		//Element conceptRoot = (Element)root.cloneNode(true);	
		for(int i=0; i<uuidLists.getLength(); i++){
			Element element=(Element)uuidLists.item(i);
			String name=Util.getFirstTextNode(element, "@name");
			String type=Util.getFirstTextNode(element, "@type");
			String value=null;
			for(int j=0; j<conceptRoot.getChildNodes().getLength(); j++){
				Node node= conceptRoot.getChildNodes().item(j);
				if(node.getNodeType() != Node.ELEMENT_NODE) continue;	
				if(node.getChildNodes().getLength()>1){
					generateUUIDForElement(schema, dataCluster,node.getNodeName(),(Element)node);
				}else{
					if(node.getNodeName().equalsIgnoreCase(name)){
						if(node.getTextContent()==null ||node.getTextContent().length()==0 ){							
							if(EUUIDCustomType.AUTO_INCREMENT.getName().equalsIgnoreCase(type)){								
								//value=String.valueOf(new UID().getID());
								String id=String.valueOf(AutoIncrementGenerator.generateNum(LocalUser.getLocalUser().getUniverse().getName(), dataCluster,concept+"."+name));
								//check id exists
								ItemPOJOPK pk=new ItemPOJOPK(new DataClusterPOJOPK(dataCluster),concept,new String[]{id});
								ItemPOJO pojo=ItemPOJO.load(pk);
								while(pojo!=null){
									id=String.valueOf(AutoIncrementGenerator.generateNum(LocalUser.getLocalUser().getUniverse().getName(), dataCluster,concept+"."+name));
									pk=new ItemPOJOPK(new DataClusterPOJOPK(dataCluster),concept,new String[]{id});
									pojo=ItemPOJO.load(pk);
									if(pojo==null){ //if don't exist, select the id 
										value=id;
										break;
									}
								}
								if(pojo==null){
									value=id;
								}
								
							}
							if(EUUIDCustomType.UUID.getName().equalsIgnoreCase(type)){
								value=String.valueOf(UUID.randomUUID().toString());
							}							
							node.setTextContent(value);
						}
						break;
					}					
				}
			}	
		}
		
    }
 	 
	public static String getXMLStringFromNode(Node d) throws TransformerException{
		StringWriter writer = new StringWriter();
		TransformerFactory.newInstance().newTransformer()
		.transform(new DOMSource(d), new StreamResult(writer));
		
		return writer.toString();
	}
     
    /**
     * @author achen
     * @param xsd
     * @param businessConceptName
     * @param keyName
     * @return
     * @throws TransformerException
     */
    public static String getBusinessConceptKeyType(Document xsd, String businessConceptName,String keyName) throws TransformerException{
    	try {
    		String type;
    		type = Util.getTextNodes(
    			xsd.getDocumentElement(),
    			"xsd:element[@name='"+businessConceptName+"']/xsd:complexType//xsd:element[@name='"+keyName+"']/@type",
				getRootElement("nsholder",xsd.getDocumentElement().getNamespaceURI(),"xsd")
				)[0];
    	
        	return type;
    	} catch(TransformerException e) {
    	    String err = "Unable to get the keys for the Business Concept "+businessConceptName+": "+e.getLocalizedMessage();
    		throw new TransformerException(err);
    	}    
    }
    
    /**
     * @deprecated use {@link #getItemKeyValues(Element, XSDKey)}
     * @param item
     * @param key
     * @return the key ids
     * @throws XtentisException
     */
    public static String[] getKeyValuesFromItem(Element item, XSDKey key) throws XtentisException{
    	try {
    		String[] vals = new String[key.getFields().length];
    		
      		Node root = Util.getNodeList(
        			item,
        			key.getSelector()
    				).item(0);
    		
    		String[] fields=key.getFields();
    		for (int i = 0; i < fields.length; i++) {    			
    			vals[i] = Util.getFirstTextNode(root,fields[i]);
			}
        	return vals;
    	} catch (XtentisException e) {
    		throw(e);
    	} catch (Exception e) {
    	    String err = "Unable to get the key value for the item "+item.getLocalName()
    			+": "+e.getClass().getName()+": "+e.getLocalizedMessage();
    	    org.apache.log4j.Category.getInstance(Util.class).error(err);
    		throw new XtentisException(err);
    	}    	    	
    }
    
    /**
     * Return the Item Primary key values
     * @param item
     * @param xsdKey
     * @return the Item Primary Key values
     * @throws TransformerException
     * @see #getBusinessConceptKey(Document, String)
     */
    public static String[] getItemKeyValues(Element item, XSDKey xsdKey) throws TransformerException{
    	try {
    		String[] vals = new String[xsdKey.getFields().length];
    		for (int i = 0; i < xsdKey.getFields().length; i++) {    			
    			vals[i] = Util.getFirstTextNode(item,xsdKey.getSelector()+"/"+xsdKey.getFields()[i]);
    			if (vals[i]!=null) vals[i] = vals[i].trim(); //FIXME: Due to eXist trimming values @see ItemPOJO
			}
        	return vals;
    	} catch (TransformerException e) {
    	    String err = "Unable to get the key value for the item "+item.getLocalName()
    			+": "+e.getClass().getName()+": "+e.getLocalizedMessage();
    		throw new TransformerException(err);
    	}    	    	
    }
    
    
    /**
     * Extracts the item PK by reading it and its model<br>
     * The less costly of all methods since all parsing is already done
     * @param item
     * @param dataModel
     * @return the Item PK
     */
    public static ItemPOJOPK getItemPOJOPK(DataClusterPOJOPK dataClusterPOJOPK, Element item, Document dataModel)
    	throws TransformerException
    {	
		String conceptName = item.getLocalName();
		String[] itemKeyValues = null;
		try {    
			XSDKey conceptKey = Util.getBusinessConceptKey(
	    		dataModel,
				conceptName
			);
			//	get key values
			itemKeyValues = Util.getItemKeyValues(
				item,
				conceptKey
			);
		}catch (TransformerException e) {
    	    String err = "Unable to get item PK for the item "+item.getLocalName()
			+": "+e.getClass().getName()+": "+e.getLocalizedMessage();
    	    throw new TransformerException(err);
		}    
		return new ItemPOJOPK(dataClusterPOJOPK, conceptName,itemKeyValues);
    }

    
    /*
    public static Document getExtractorXSLT(Document original) throws XtentisException{
    	
    	String prefix = original.getDocumentElement().getPrefix();
    	String topXpath = Util.getTopBusinessConceptPath(original);
    	
    	if (topXpath == null) {
    		throw new XtentisException("Unable to build Extractor XSLT: the Concept cannot be found");
    	}
    	
    	String rootAttrs = "";
    	NamedNodeMap attsMap = original.getDocumentElement().getAttributes();
    	int len = attsMap.getLength();
    	for (int i =0; i<len; i++) {
    		Attr att = (Attr)attsMap.item(i);
    		rootAttrs +=" "+att.getName()+"='"+att.getValue()+"'"; 
    	}
    	

    	topXpath = topXpath.replace('"','\'');
    	String xsl = 
			"<?xml version='1.0' encoding='UTF-8'?>"+
			"<xs:stylesheet "+rootAttrs+">"+
			"    <xs:output method='xml' indent='yes' omit-xml-declaration='yes' standalone='yes'/>"+
			"    <xs:template mode='extract' match='*'><xs:copy-of select='.'/></xs:template>"+
			"   <xs:template match='/'><xs:apply-templates mode='extract' select=\""+topXpath+"\"/></xs:template>"+    
			"</xs:stylesheet>";
    	
    	xsl = xsl.replaceAll("xs:",prefix+":");
    	
    	return Util.parse(xsl);
    	
    }
    */
    
    
    
    /**
	 * Returns a namespaced root element of a document
	 * Useful to create a namespace holder element
	 * @param namespace
	 * @return the root Element
	 */
	public static Element getRootElement(String elementName, String namespace, String prefix) throws TransformerException{
	 	Element rootNS=null;
    	try {
	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        factory.setNamespaceAware(true);   
	        DocumentBuilder builder = factory.newDocumentBuilder();
	    	DOMImplementation impl = builder.getDOMImplementation();
	    	Document namespaceHolder = impl.createDocument(namespace,(prefix==null?"":prefix+":")+elementName, null);    
	    	rootNS = namespaceHolder.getDocumentElement();
	    	rootNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:"+prefix, namespace);
    	} catch (Exception e) {
    	    String err="Error creating a namespace holder document: "+e.getLocalizedMessage();
    	    throw new TransformerException(err);    
    	}
    	return rootNS;
	}
	
	/**
	 * Generates an xml string from a node 
	 * (not pretty formatted)
	 * @param n the node
	 * @return the xml string
	 * @throws TransformerException
	 */
	public static String nodeToString(Node n) throws TransformerException{
		return nodeToString(n,true);
	}
	/**
	 * Generates an xml string from a node with or without the xml declaration 
	 * (not pretty formatted)
	 * @param n the node
	 * @return the xml string
	 * @throws TransformerException
	 */
	public static String nodeToString(Node n, boolean omitXMLDeclaration) throws TransformerException{
       	StringWriter sw = new StringWriter();
       	Transformer transformer = TransformerFactory.newInstance().newTransformer();       	
       	if (omitXMLDeclaration)
       		transformer.setOutputProperty("omit-xml-declaration","yes");
       	else
       		transformer.setOutputProperty("omit-xml-declaration","no");
       	transformer.setOutputProperty("indent","yes");
       	transformer.transform(
				new DOMSource(n),
				new StreamResult(sw)
				);
       	if (sw==null) return null;       	
		return sw.toString();
	}
	
	/**
	 * Get a nodelist from an xPath
	 * @throws XtentisException
	 */
	public static NodeList getNodeList(Document d, String xPath) throws XtentisException{
		return getNodeList(d.getDocumentElement(),xPath,null,null);
	}
	/**
	 * Get a nodelist from an xPath
	 * @throws XtentisException
	 */
	public static NodeList getNodeList(Node contextNode, String xPath) throws XtentisException{
		return getNodeList(contextNode,xPath,null,null);
	}
	
	/**
	 * Get a nodelist from an xPath
	 * @throws XtentisException
	 */
	public static NodeList getNodeList(Node contextNode, String xPath, String namespace, String prefix) throws XtentisException{
		try {
		    XObject xo = XPathAPI.eval(
	    		contextNode,
				xPath,
				(namespace == null) ? contextNode : Util.getRootElement("nsholder",namespace,prefix)
		    );
		    if (xo.getType() != XObject.CLASS_NODESET) return null;
		    return xo.nodelist();
    	} catch (TransformerException e) {
    	    String err = "Unable to get the Nodes List for xpath '"+xPath+"'"
    	    	+((contextNode==null) ? "" : " for Node "+contextNode.getLocalName())
    			+": "+e.getLocalizedMessage();
    		throw new XtentisException(err);
    	}    	    	
	}
	
	
	/**
	 * Join an array of strings into a single string using a separator
	 * @param strings
	 * @param separator
	 * @return a single string or null
	 */
	public static String joinStrings(String[] strings, String separator) {
		if (strings == null) return null;
		String res = "";
		for (int i = 0; i < strings.length; i++) {
			res+= (i>0) ? separator : "";
			res+=(strings[i] == null ? "" : strings[i]);
		}
		return res;
	}
	
	private static Pattern conceptFromPathPattern = Pattern.compile("^/*(.*?)[\\[|/].*");
	/**
	 * Returns the first part - eg. the concept - from the path
	 * @param path
	 * @return
	 * 		The concept extracted from the path
	 */
    public static String getConceptFromPath(String path) {
    	if (!path.endsWith("/")) path+="/";
    	Matcher m = conceptFromPathPattern.matcher(path);
    	if (m.matches()) {
    		return m.group(1);
    	} else {
    		return null;
    	}
    }
    
	/**
	 * Returns the list of items that look like parts numbers
	 * @param value
	 * 		The value to match
	 * @return
	 * 		The list
	 */
    public static Collection<String> getAllPartNumbers(String value) {
    	ArrayList<String> l = new ArrayList<String>();
    	l.add(value);
    	Pattern p = Pattern.compile("([0-9]+[\\*]?)[\\p{Punct}]");  //-_'#~`\\\\\\/
    	Matcher m = p.matcher(value);
    	try {
    		String s = m.replaceAll("$1 ");
    		if (!s.equals(value)) {
    			l.add(s.trim());
    			l.add(s.trim().replaceAll(" ", ""));
    		}
    	} catch (Exception e) {}
    	return l;
    }

	/*********************************************************************
	 *     PROFILING
	 *********************************************************************/
	
    private static ArrayList<long[]> timeMarkers = new ArrayList<long[]>(); // [totalTops,lastTime,totalPeriod]
    
    /**
     * Mark and record the time 
     * @param marker
     * @return
     * 		the period of time
     * @throws Exception
     */
	public static long topTime(int marker) throws Exception{
		long time = System.currentTimeMillis();
		if (timeMarkers.size()<=marker) {
			for (int i = timeMarkers.size(); i <= marker; i++) {
				if (i==0) 
					timeMarkers.add(new long[]{0L,System.currentTimeMillis(),0L});
				else
					timeMarkers.add(new long[]{0L,0L,0L});
			}
		}
		long period = 0;
		if (marker == 0)
			period = time - (timeMarkers.get(marker))[1];
		else
			period = time - (timeMarkers.get(marker-1))[1];
		long totalPeriod = (timeMarkers.get(marker))[2] + period;
		long totalTops = (timeMarkers.get(marker))[0] + 1;
		timeMarkers.set(marker, new long[]{totalTops,time,totalPeriod});
		return period;
    }    
	
	public static void resetTimeMarkers() {
		timeMarkers = new ArrayList<long[]>();
	}
	
	public static ArrayList<long[]> getTimeMarkers() {
		return timeMarkers;
	}
	
	/**
	 * Dumps the time markers to the console
	 * @param msg
	 */
	public static void dumpTimeMarkers(String msg) {
		System.out.println("TIME MARKERS: "+msg);
		int i = 0;
		long totalProcessing = 0;
		for (Iterator<long[]> iter = timeMarkers.iterator(); iter.hasNext(); ) {
			long[] values = iter.next();
			if (i>0) totalProcessing+=values[2];
			System.out.println(
					"Marker "+(i++)+":"
					+"tops: "+values[0]+" -- "
					+"totalPeriods: "+values[2]+" -- "
					+"average: "+(values[2]/values[0])
			);
		}
		if (i>0)
			System.out.println("Total Processing: "+totalProcessing+" -- average: "+totalProcessing/(timeMarkers.get(0))[0]);
	}
	
	
    
	/*********************************************************************
	 *     SUBJECT - AUTHENTICATION
	 *********************************************************************/

	public static Subject getActiveSubject() throws XtentisException{
		//Get active Subject
		try {
			String SUBJECT_CONTEXT_KEY = "javax.security.auth.Subject.container";
			return (Subject) PolicyContext.getContext(SUBJECT_CONTEXT_KEY);
		} catch (Exception e) {
			throw new XtentisException(e.getMessage());
		}
		
		/*
		InitialContext ictx = new InitialContext();
		JaasSecurityManager jsm = (JaasSecurityManager) ictx.lookup("java:jaas/xtentisSecurity");		
		return jsm.getActiveSubject();
		*/
	}
	
	/**
	 * Extracts the username of the logged user from the {@link Subject}
	 * @param subject
	 * @return
	 * 		The username
	 * @throws XtentisException
	 */
	public static String getUsernameFromSubject(Subject subject) throws XtentisException{
		Set<Principal> set = subject.getPrincipals();
		for (Iterator<Principal> iter = set.iterator(); iter.hasNext(); ) {
			Principal principal = iter.next();
			if (! (principal instanceof Group)) {
				return principal.getName();
			}
		}
		return null;
	}

	/**
	 * Extract the role names of the logged user from the Subject
	 * @param subject
	 * @return
	 * 		The collection of roles
	 * @throws XtentisException
	 */
	public static Collection<String> getRoleNamesFromSubject(Subject subject) throws XtentisException{
		ArrayList<String> roleNames = new ArrayList<String>();
		Set<Principal> set = subject.getPrincipals();
		for (Iterator<Principal> iter = set.iterator(); iter.hasNext(); ) {
			Principal principal = iter.next();
			if (principal instanceof Group) {
				Group group = (Group) principal;
				//@see XtentisMDMLoginModule
				if ("Roles".equals(group.getName())) {
					Enumeration<? extends Principal> principals = group.members();
					while (principals.hasMoreElements()) {
						roleNames.add(((Principal)principals.nextElement()).getName());
					}
				}
			}
		}
		return roleNames;
	}
	
	
	/*********************************************************************
	 *     DATES
	 *********************************************************************/
	
	public static String getTimestamp() {
		return getTimestamp(new Date());
	}
	
	public static String getTimestamp(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss.SSS' 'z");
		return sdf.format(d);
	}

	

    
	/*********************************************************************
	 *      XML STUFF
	 *********************************************************************/
	private static Pattern regexLT = Pattern.compile("&lt;");
	private static Pattern regexGT = Pattern.compile("&gt;");
	private static Pattern regexAMP = Pattern.compile("&amp;");
    
	public static String xmlDecode(String string) {
		if (string == null) return null;
    	string = regexLT.matcher(string).replaceAll("<");
    	string = regexGT.matcher(string).replaceAll(">");
    	string = regexAMP.matcher(string).replaceAll("&");
    	return string;
    }

	private static Pattern regexSup = Pattern.compile(">");
	private static Pattern regexInf = Pattern.compile("<");
	private static Pattern regexEt = Pattern.compile("&");
	
	public static String xmlEncode(String string) {
    	string = regexSup.matcher(string).replaceAll("&gt;");
    	string = regexInf.matcher(string).replaceAll("&lt;");
    	string = regexEt.matcher(string).replaceAll("&amp;");
    	return string;
    }


	/*********************************************************************
	 * 
	 *      LOCAL HOME GETTERS 
	 *      This cache system requires a JBoss restart on every core deployment 
	 *      
	 *********************************************************************/
	
	//The only Static HashMap around (hopefully)
	private static HashMap<String,EJBLocalHome> localHomes = new HashMap<String, javax.ejb.EJBLocalHome>();

	public static void flushLocalHomes() throws NamingException{
		localHomes = new HashMap<String, javax.ejb.EJBLocalHome>();
	}
	
	public static EJBLocalHome getLocalHome(String jndi) throws NamingException{
		EJBLocalHome localHome = null;
		if (USE_HOME_CACHES) {
			localHome = localHomes.get(jndi);
			if (localHome == null) {
				localHome = (EJBLocalHome)new InitialContext().lookup(jndi);
				localHomes.put(jndi, localHome);
			}
		} else {
			localHome = (EJBLocalHome)new InitialContext().lookup(jndi);
		}
//		dumpClass(localHome.getClass());
		return localHome;
	}
    
	
	
	public static ServiceLocalHome getServiceLocalHome() throws NamingException {
		return (ServiceLocalHome) getLocalHome(ServiceLocalHome.JNDI_NAME);
	}
	
	public static ItemCtrl2LocalHome getItemCtrl2LocalHome() throws NamingException {
		return (ItemCtrl2LocalHome) getLocalHome(ItemCtrl2LocalHome.JNDI_NAME);
	}
	public static ItemCtrl2Local getItemCtrl2Local() throws NamingException,CreateException {
		return getItemCtrl2LocalHome().create();
	}
	
	public static DroppedItemCtrlLocalHome getDroppedItemCtrlLocalHome() throws NamingException {
		return (DroppedItemCtrlLocalHome) getLocalHome(DroppedItemCtrlLocalHome.JNDI_NAME);
	}
	public static DroppedItemCtrlLocal getDroppedItemCtrlLocal() throws NamingException,CreateException {
		return getDroppedItemCtrlLocalHome().create();
	}

	public static DataModelCtrlLocalHome getDataModelCtrlLocalHome() throws NamingException {
		return (DataModelCtrlLocalHome) getLocalHome(DataModelCtrlLocalHome.JNDI_NAME);
	}
	public static DataModelCtrlLocal getDataModelCtrlLocal() throws NamingException,CreateException {
		return getDataModelCtrlLocalHome().create();
	}

	public static XmlServerSLWrapperLocal getXmlServerCtrlLocal() throws XtentisException {
        XmlServerSLWrapperLocal server = null;
		try {
			server  =  ((XmlServerSLWrapperLocalHome)new InitialContext().lookup(XmlServerSLWrapperLocalHome.JNDI_NAME)).create();        				
		} catch (Exception e) {
			String err = "Error : unable to access the XML Server wrapper";
			org.apache.log4j.Logger.getLogger(ObjectPOJO.class).error(err,e);
			throw new XtentisException(err);
		}
		return server;
	}
	public static DataClusterCtrlLocalHome getDataClusterCtrlLocalHome() throws NamingException {
		return (DataClusterCtrlLocalHome) getLocalHome(DataClusterCtrlLocalHome.JNDI_NAME);
	}
	public static DataClusterCtrlLocal getDataClusterCtrlLocal() throws NamingException,CreateException {
		return getDataClusterCtrlLocalHome().create();
	}	

	public static ViewCtrlLocalHome getViewCtrlLocalHome() throws NamingException {
		return (ViewCtrlLocalHome) getLocalHome(ViewCtrlLocalHome.JNDI_NAME);
	}
	public static ViewCtrlLocal getViewCtrlLocal() throws NamingException,CreateException {
		return getViewCtrlLocalHome().create();
	}		

	public static RoutingRuleCtrlLocalHome getRoutingRuleCtrlLocalHome() throws NamingException {
		return (RoutingRuleCtrlLocalHome) getLocalHome(RoutingRuleCtrlLocalHome.JNDI_NAME);
	}
	public static RoutingRuleCtrlLocal getRoutingRuleCtrlLocal() throws NamingException,CreateException {
		return getRoutingRuleCtrlLocalHome().create();
	}		

	public static StoredProcedureCtrlLocalHome getStoredProcedureCtrlLocalHome() throws NamingException {
		return (StoredProcedureCtrlLocalHome) getLocalHome(StoredProcedureCtrlLocalHome.JNDI_NAME);
	}
	public static StoredProcedureCtrlLocal getStoredProcedureCtrlLocal() throws NamingException,CreateException {
		return getStoredProcedureCtrlLocalHome().create();
	}

	@Deprecated
	public static TransformerCtrlLocalHome getTransformerCtrlLocalHome() throws NamingException {
		return (TransformerCtrlLocalHome) getLocalHome(TransformerCtrlLocalHome.JNDI_NAME);
	}
	@Deprecated
	public static TransformerCtrlLocal getTransformerCtrlLocal() throws NamingException,CreateException {
		return getTransformerCtrlLocalHome().create();
	}	
	
	public static TransformerV2CtrlLocalHome getTransformerV2CtrlLocalHome() throws NamingException {
		return (TransformerV2CtrlLocalHome) getLocalHome(TransformerV2CtrlLocalHome.JNDI_NAME);
	}
	public static TransformerV2CtrlLocal getTransformerV2CtrlLocal() throws NamingException,CreateException {
		return getTransformerV2CtrlLocalHome().create();
	}		
	
	public static RoleCtrlLocalHome getRoleCtrlLocalHome() throws NamingException {
		return (RoleCtrlLocalHome) getLocalHome(RoleCtrlLocalHome.JNDI_NAME);
	}
	public static RoleCtrlLocal getRoleCtrlLocal() throws NamingException,CreateException {
		return getRoleCtrlLocalHome().create();
	}
	
	public static MenuCtrlLocalHome getMenuCtrlLocalHome() throws NamingException {
		return (MenuCtrlLocalHome) getLocalHome(MenuCtrlLocalHome.JNDI_NAME);
	}
	public static MenuCtrlLocal getMenuCtrlLocal() throws NamingException,CreateException {
		return getMenuCtrlLocalHome().create();
	}		
	
	public static BackgroundJobCtrlLocalHome getBackgroundJobCtrlLocalHome() throws NamingException {
		return (BackgroundJobCtrlLocalHome) getLocalHome(BackgroundJobCtrlLocalHome.JNDI_NAME);
	}
	public static BackgroundJobCtrlLocal getBackgroundJobCtrlLocal() throws NamingException,CreateException {
		return getBackgroundJobCtrlLocalHome().create();
	}		
	
	public static ConfigurationInfoCtrlLocalHome getConfigurationInfoCtrlLocalHome() throws NamingException {
		return (ConfigurationInfoCtrlLocalHome) getLocalHome(ConfigurationInfoCtrlLocalHome.JNDI_NAME);
	}
	public static ConfigurationInfoCtrlLocal getConfigurationInfoCtrlLocal() throws NamingException,CreateException {
		return getConfigurationInfoCtrlLocalHome().create();
	}		
	
	public static VersioningSystemCtrlLocalHome getVersioningSystemCtrlLocalHome() throws NamingException {
		return (VersioningSystemCtrlLocalHome) getLocalHome(VersioningSystemCtrlLocalHome.JNDI_NAME);
	}
	public static VersioningSystemCtrlLocal getVersioningSystemCtrlLocal() throws NamingException,CreateException {
		return getVersioningSystemCtrlLocalHome().create();
	}	
	
	public static RoutingOrderV2CtrlLocalHome getRoutingOrderV2CtrlLocalHome() throws NamingException {
		return (RoutingOrderV2CtrlLocalHome) getLocalHome(RoutingOrderV2CtrlLocalHome.JNDI_NAME);
	}
	public static RoutingOrderV2CtrlLocal getRoutingOrderV2CtrlLocal() throws NamingException,CreateException {
		return getRoutingOrderV2CtrlLocalHome().create();
	}		

	public static RoutingEngineV2CtrlLocalHome getRoutingEngineV2CtrlLocalHome() throws NamingException {
		return (RoutingEngineV2CtrlLocalHome) getLocalHome(RoutingEngineV2CtrlLocalHome.JNDI_NAME);
	}
	public static RoutingEngineV2CtrlLocal getRoutingEngineV2CtrlLocal() throws NamingException,CreateException {
		return getRoutingEngineV2CtrlLocalHome().create();
	}	
	
	public static UniverseCtrlLocalHome getUniverseCtrlLocalHome() throws NamingException {
		return (UniverseCtrlLocalHome) getLocalHome(UniverseCtrlLocalHome.JNDI_NAME);
	}
	public static UniverseCtrlLocal getUniverseCtrlLocal() throws NamingException,CreateException {
		return getUniverseCtrlLocalHome().create();
	}
	
	public static SynchronizationPlanCtrlLocalHome getSynchronizationPlanCtrlLocalHome() throws NamingException {
		return (SynchronizationPlanCtrlLocalHome) getLocalHome(SynchronizationPlanCtrlLocalHome.JNDI_NAME);
	}
	public static SynchronizationPlanCtrlLocal getSynchronizationPlanCtrlLocal() throws NamingException,CreateException {
		return getSynchronizationPlanCtrlLocalHome().create();
	}

	public static SynchronizationObjectCtrlLocalHome getSynchronizationObjectCtrlLocalHome() throws NamingException {
		return (SynchronizationObjectCtrlLocalHome) getLocalHome(SynchronizationObjectCtrlLocalHome.JNDI_NAME);
	}
	public static SynchronizationObjectCtrlLocal getSynchronizationObjectCtrlLocal() throws NamingException,CreateException {
		return getSynchronizationObjectCtrlLocalHome().create();
	}

	public static SynchronizationItemCtrlLocalHome getSynchronizationItemCtrlLocalHome() throws NamingException {
		return (SynchronizationItemCtrlLocalHome) getLocalHome(SynchronizationItemCtrlLocalHome.JNDI_NAME);
	}
	public static SynchronizationItemCtrlLocal getSynchronizationItemCtrlLocal() throws NamingException,CreateException {
		return getSynchronizationItemCtrlLocalHome().create();
	}
	
//	private static ConnectionFactory cachedConnectionFactory = null;
    public static Connection getConnection(String JNDIName) throws XtentisException {
    	try {
//    		if (cachedConnectionFactory == null) - Removed - no more caching of connections
//    			cachedConnectionFactory = (ConnectionFactory)(new InitialContext()).lookup(JNDIName);
//    		return cachedConnectionFactory.getConnection();
    		return ((ConnectionFactory)(new InitialContext()).lookup(JNDIName)).getConnection();
    	} catch (Exception e) {
    		String err= "JNDI lookup error: "+e.getClass().getName() + ": " + e.getLocalizedMessage();
			org.apache.log4j.Logger.getLogger(Util.class).error(err);
			throw new XtentisException(err);
    	}
    }
    


//	@Deprecated
//	public static TransformerCtrlLocalHome getTransformerCtrlLocalHome() throws NamingException {
//		return (TransformerCtrlLocalHome) getLocalHome(TransformerCtrlLocalHome.JNDI_NAME);
//	}
//	@Deprecated
//	public static TransformerCtrlLocal getTransformerCtrlLocal() throws NamingException,CreateException {
//		return getTransformerCtrlLocalHome().create();
//	}


	
	/***********************************************************************************************************
	 * Typed Content Manipulation 
	 ***********************************************************************************************************/
	
	private static Pattern extractCharsetPattern=Pattern.compile(".*charset\\s*=[\"|']?(.+)[\"|']([\\s|;].*)?");
	/**
	 * Extract the charset of a content type<br>
	 * e.g 'utf-8' in 'text/xml; charset="utf-8"'
	 * @param contentType
	 * @return the charset
	 */
	public static String extractCharset(String contentType) {
		String charset="UTF8";
		Matcher m = extractCharsetPattern.matcher(contentType);
		if (m.matches()) {
			charset = m.group(1).trim().toUpperCase();
		}
		//if ("UTF-8".equals(charset)) charset = "UTF8";
		return charset;
	}

	/**
	 * Extract the MIME type and sub type of a content type<br>
	 * e.g 'text/xml' in 'text/xml; charset="utf-8"'
	 * @param contentType
	 * @return the MIME Type and SubType
	 */
	public static String extractTypeAndSubType(String contentType) {
		if (contentType == null ) return null;
		return contentType.split(";")[0].trim().toLowerCase();
	}

	/**
	 * Extracts a byte array from an InputStream
	 * @param is
	 * @return the byte array
	 * @throws IOException
	 */
	public static byte[] getBytesFromStream(InputStream is) throws IOException{
		if (is==null) return null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int b;
		while ((b=is.read())!=-1) bos.write(b);
		return bos.toByteArray();
	}

	
	/*********************************************************************
	 * 
	 *      GUID Generator
	 *      
	 *********************************************************************/

	
	/** Cached per JVM server IP. */
	private static String hexServerIP = null;

	// initialise the secure random instance
	private static final java.security.SecureRandom seeder = new java.security.SecureRandom();

	/**
	 * A 32 byte GUID generator (Globally Unique ID). These artificial keys SHOULD <strong>NOT </strong> be seen by the user,
	 * not even touched by the DBA but with very rare exceptions, just manipulated by the database and the programs.
	 *
	 * Usage: Add an id field (type java.lang.String) to your EJB, and add setId(XXXUtil.generateGUID(this)); to the ejbCreate method.
	 */
	public static final String generateGUID(Object o) {
		StringBuffer tmpBuffer = new StringBuffer(16);
		if (hexServerIP == null) {
			java.net.InetAddress localInetAddress = null;
			try {
				// get the inet address

				localInetAddress = java.net.InetAddress.getLocalHost();
			}
			catch (java.net.UnknownHostException uhe) {
				System.err.println("JobUtil: Could not get the local IP address using InetAddress.getLocalHost()!");
				// todo: find better way to get around this...
				uhe.printStackTrace();
				return null;
			}
			byte serverIP[] = localInetAddress.getAddress();
			hexServerIP = hexFormat(getInt(serverIP), 8);
		}

		String hashcode = hexFormat(System.identityHashCode(o), 8);
		tmpBuffer.append(hexServerIP);
		tmpBuffer.append(hashcode);

		long timeNow= System.currentTimeMillis();
		int timeLow = (int)timeNow & 0xFFFFFFFF;
		int node = seeder.nextInt();

		StringBuffer guid = new StringBuffer(32);
		guid.append(hexFormat(timeLow, 8));
		guid.append(tmpBuffer.toString());
		guid.append(hexFormat(node, 8));
		return guid.toString();
	}

	private static int getInt(byte bytes[]) {
		int i = 0;
		int j = 24;
		for (int k = 0; j >= 0; k++) {
			int l = bytes[k] & 0xff;
			i += l << j;
			j -= 8;
		}
		return i;
	}

	private static String hexFormat(int i, int j) {
		String s = Integer.toHexString(i);
		return padHex(s, j) + s;
	}

	private static String padHex(String s, int i) {
		StringBuffer tmpBuffer = new StringBuffer();
		if (s.length() < i) {
			for (int j = 0; j < i - s.length(); j++) {
				tmpBuffer.append('0');
			}
		}
		return tmpBuffer.toString();
	}
	
	public static ItemPOJO getItem(String datacluster, String xml)throws Exception{
		String projection = xml;
		Element root = Util.parse(projection).getDocumentElement();
		
		String concept = root.getLocalName();

		DataModelPOJO dataModel  = Util.getDataModelCtrlLocal().getDataModel(
					new DataModelPOJOPK(datacluster)
			);
		Document schema=Util.parse(dataModel.getSchema());
        XSDKey conceptKey = com.amalto.core.util.Util.getBusinessConceptKey(
        		schema,
				concept					
		);
       
		//get key values
		String[] ids = com.amalto.core.util.Util.getKeyValuesFromItem(
   			root,
				conceptKey
		);				
		DataClusterPOJOPK dcpk = new DataClusterPOJOPK(datacluster);
		ItemPOJOPK itemPOJOPK=new ItemPOJOPK(dcpk,concept, ids);
		return ItemPOJO.load(itemPOJOPK);
	}
	
	public static HashMap<String, UpdateReportItem> compareElement(String parentPath,Node newElement, Node oldElement )throws Exception{
		HashMap<String, UpdateReportItem> map =new HashMap<String, UpdateReportItem>();
		Set<String> xpaths=getXpaths(parentPath, newElement);
	    JXPathContext jxpContextOld = JXPathContext.newContext ( oldElement );
	    jxpContextOld.setLenient(true);
	    JXPathContext jxpContextNew = JXPathContext.newContext ( newElement );
	    jxpContextNew.setLenient(true);
	    String concept=newElement.getLocalName();
		for(String xpath: xpaths){			
			NodeList listnew=getNodeList(newElement, xpath);
			NodeList listold=getNodeList(oldElement, xpath);
			int num=Math.max(listnew.getLength(), listold.getLength());
			xpath=xpath.replaceFirst("/"+concept, "");
			if(num>1){//list
				for(int i=1; i<=num; i++){
					String xpath1=xpath+"["+i+"]";
					String oldvalue=(String)jxpContextOld.getValue(xpath1,String.class);
					String newvalue=(String)jxpContextNew.getValue(xpath1,String.class);
					if(newvalue!=null && !newvalue.equals(oldvalue)|| oldvalue!=null && !oldvalue.equals(newvalue)){
						UpdateReportItem item =new UpdateReportItem(xpath1, oldvalue, newvalue);
						map.put(xpath1, item);
					}				
				}
			}else{
				String oldvalue=(String)jxpContextOld.getValue(xpath,String.class);
				String newvalue=(String)jxpContextNew.getValue(xpath,String.class);
				if(newvalue!=null && !newvalue.equals(oldvalue)|| oldvalue!=null && !oldvalue.equals(newvalue)){
					UpdateReportItem item =new UpdateReportItem(xpath, oldvalue, newvalue);
					map.put(xpath, item);
				}
			}
		}
		return map;
	}
	
	private static Set<String > getXpaths(String parentPath,Node node)throws Exception{
		Set<String> set=new HashSet<String>();
		NodeList list=node.getChildNodes();
		for(int i=0; i<list.getLength(); i++){
			Node n=list.item(i);
			
			if(n.getNodeType() == Node.ELEMENT_NODE){
				String nName=n.getNodeName();
				String xPath=parentPath+"/"+nName;		
				NodeList list1=getNodeList(node, xPath);
				int j=1;
				for(int ii=0; ii<list1.getLength(); ii++){
					Node node1=list1.item(ii);
					if(node1.getNodeType() == Node.ELEMENT_NODE){
						if(getElementNum(list1)>1){
							j++;
						}
					}
				}
				if( !hasChildren(n)){
					set.add(xPath);
				}else{
					//if list
					if(j>1){
						for(int ii=1; ii<=j;ii++){
							set.addAll(getXpaths(xPath+"["+ii+"]", n));
						}
					}else{
						set.addAll(getXpaths(xPath, n));
					}
				}
			}
		}
		return set;
	}
	
	//TODO check
	public static Node updateElement(String parentPath,Node old, HashMap<String, UpdateReportItem> updatedpath)throws Exception{

		//use JXPathContext to update the old element
	    JXPathContext jxpContext = JXPathContext.newContext ( old );
	    jxpContext.setLenient(true);

		AbstractFactory factory = new AbstractFactory(){
			public boolean createObject(JXPathContext context, Pointer pointer, Object parent, String name, int index)
			 {
				 if (parent instanceof Node){
						 try{
						 Node node = (Node) parent;
						 Document doc1 = node.getOwnerDocument();
						 Element e = doc1.createElement(name);
						 node.appendChild(e);
						 return true;}
						 catch(Exception e){
						 return false;
						 }
				 }else 
				 		return false;
			 }
			 public boolean declareVariable(JXPathContext context, String name) {return false; }
		};
			 
		jxpContext.setFactory(factory);
		String concept=old.getLocalName();
		for( Map.Entry<String, UpdateReportItem> entry:updatedpath.entrySet()){
			String xpath= entry.getValue().getPath();
			xpath=xpath.replaceFirst("/"+concept, "");
			jxpContext.createPathAndSetValue(xpath, entry.getValue().newValue);
		}		
		return (Node)jxpContext.getContextBean();
	}
	
	//key is the xpath, value is the xpath value
	public static HashMap<String, String> getElementValueMap(String parentPath,Node n)throws Exception{
		HashMap<String, String> map =new HashMap<String, String>();
		NodeList list=n.getChildNodes();
		for(int i=0; i<list.getLength(); i++){
			Node node=list.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				String nName=node.getNodeName();
				String xPath=parentPath+"/"+nName;
				String nValue=getFirstTextNode(node, ".");
				if( !hasChildren(node)){
					map.put(xPath, nValue);
				}else{					
					map.putAll(getElementValueMap(xPath, node));
				}
			}
		}
		return map;
	}
	private static UpdateReportItem getUpdatedItem(HashMap<String, UpdateReportItem> updatedpath, String xpath){
		for( Map.Entry<String, UpdateReportItem> entry:updatedpath.entrySet()){
			if(entry.getKey().startsWith(xpath)){
				return entry.getValue();
			}
		}
		return null;
	}
	private static boolean hasChildren(Node node){
		NodeList list=node.getChildNodes();
		for(int i=0; i<list.getLength(); i++){
			if(list.item(i).getNodeType() == Node.ELEMENT_NODE){
				return true;
			}
		}
		return false;
	}
	private static int getElementNum(NodeList list){
		int j=0;
		for(int i=0; i<list.getLength(); i++){
			if(list.item(i).getNodeType() == Node.ELEMENT_NODE){
				j++;
			}
		}
		return j;
	}
	private static String mergeExchangeData(String xml,
			String resultUpdateReport) {
		String exchangeData="<exchange>\n";
		exchangeData+="<report>"+resultUpdateReport+"</report>";
		exchangeData+="\n";
		exchangeData+="<item>"+xml+"</item>";
		exchangeData+="\n</exchange>";
		return exchangeData;
	}	
	
	public static String createUpdateReport(String[] ids, String concept,
			String operationType, HashMap<String, UpdateReportItem> updatedPath,String dataModelPK, String dataClusterPK )
			throws Exception {
		String username="";
		String revisionId="";
		

		try {
			
	    	
			username=LocalUser.getLocalUser().getUsername();
	    	UniversePOJO pojo=LocalUser.getLocalUser().getUniverse();
	    	if(pojo!=null)revisionId=pojo.getConceptRevisionID(concept);
	    	
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}
		
		String key = "";
		if(ids!=null){
			for (int i = 0; i < ids.length; i++) {
				key+=ids[i];
				if(i!=ids.length-1) key+=".";
			}
		}
		String xml2 = "" +
			"<Update>"+
			"<UserName>"+username+"</UserName>"+
            "<Source>genericUI</Source>"+
            "<TimeInMillis>"+System.currentTimeMillis()+"</TimeInMillis>"+
            "<OperationType>"+StringEscapeUtils.escapeXml(operationType)+"</OperationType>"+
            "<RevisionID>"+revisionId+"</RevisionID>"+
            "<DataCluster>"+dataClusterPK+"</DataCluster>"+
            "<DataModel>"+dataModelPK+"</DataModel>"+
            "<Concept>"+StringEscapeUtils.escapeXml(concept)+"</Concept>"+
            "<Key>"+StringEscapeUtils.escapeXml(key)+"</Key>";
		if("UPDATE".equals(operationType)){
			Collection<UpdateReportItem> list = updatedPath.values();
			for (Iterator<UpdateReportItem> iter = list.iterator(); iter.hasNext();) {
				UpdateReportItem item = iter.next();
		            xml2 += 
		            "<Item>"+
		            "   <path>"+StringEscapeUtils.escapeXml(item.getPath())+"</path>"+
		            "   <oldValue>"+StringEscapeUtils.escapeXml(item.getOldValue())+"</oldValue>"+
		            "   <newValue>"+StringEscapeUtils.escapeXml(item.getNewValue())+"</newValue>"+
		           "</Item>"; 		
				}     
		}
        xml2 += "</Update>";
		return xml2;
	}

	public static String beforeSaving(String concept,String xml, String resultUpdateReport)throws Exception{
		//check before saving transformer
		boolean isBeforeSavingTransformerExist=false;
		Collection<TransformerV2POJOPK> wst = getTransformerV2CtrlLocal().getTransformerPKs("*");
		for(TransformerV2POJOPK id: wst){
			if(id.getIds()[0].equals("beforeSaving_"+concept)){
				isBeforeSavingTransformerExist=true;
				break;
			}
		}
		//call before saving transformer
		if(isBeforeSavingTransformerExist){
			
			try {
				final String RUNNING = "XtentisWSBean.executeTransformerV2.running";
				TransformerContext context = new TransformerContext(
						new TransformerV2POJOPK("beforeSaving_" + concept));
				String exchangeData = mergeExchangeData(xml,resultUpdateReport);
				//String exchangeData = resultUpdateReport;
				context.put(RUNNING, Boolean.TRUE);
				TransformerV2CtrlLocal ctrl = Util.getTransformerV2CtrlLocal();
				TypedContent wsTypedContent = new TypedContent(
						exchangeData
								.getBytes("UTF-8"),
						"text/xml; charset=utf-8");
				ctrl.execute(
						context, 
						wsTypedContent,
						new TransformerCallBack() {
							public void contentIsReady(TransformerContext context) throws XtentisException {
								org.apache.log4j.Logger.getLogger(this.getClass()).debug("XtentisWSBean.executeTransformerV2.contentIsReady() ");
							}
							public void done(TransformerContext context) throws XtentisException {
								org.apache.log4j.Logger.getLogger(this.getClass()).debug("XtentisWSBean.executeTransformerV2.done() ");
								context.put(RUNNING, Boolean.FALSE);
							}
						}
				);
				while (((Boolean)context.get(RUNNING)).booleanValue()) {
					Thread.sleep(100);
				}				
				//TODO process no plug-in issue
				String outputErrorMessage = "";
				//Scan the entries - in priority, taka the content of the 'output_error_message' entry, 
				for(Entry<String, TypedContent> entry: context.getPipelineClone().entrySet()){

					if ("output_error_message".equals(entry.getKey()	)) {
						outputErrorMessage = new String(entry.getValue().getContentBytes(), "UTF-8");
						break;
					}
				}
				//handle error message
				if (outputErrorMessage.length() > 0) {

					String errorCode = "";
					String errorMessage = "";
					Pattern pattern = Pattern
							.compile("<error code=['\042](.*)['\042]>(.*)</error>");
					Matcher matcher = pattern.matcher(outputErrorMessage);
					while (matcher.find())

					{
						errorCode = matcher.group(1);
						errorMessage = matcher.group(2);

					}
					if (!errorCode.equals("") && !errorCode.equals("0")) {
						errorMessage = "ERROR_3:" + errorMessage;
						return errorMessage;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/*********************************************************************
	 *  TESTS
	 *********************************************************************/
 
 
	public static void testSpellCheck() throws Exception{
// 	String xml = getXML("/home/bgrieder/workspace/com.amalto.total.poc/src/com/amalto/total/poc/data/Amalto2.xml");
//		DataClusterBMP dcb = new DataClusterBMP();
// 	dcb.addToVocabulary(xml);
// 	System.out.println("VOCABULARY:\n"+dcb.getVocabulary());
// 	
// 	System.setProperty("jazzy.config", "com.amalto.core.util.JazzyConfiguration");
// 	
//  SpellDictionary dictionary = new SpellDictionaryHashMap(
//  		new StringReader(dcb.getVocabulary()), 
//				SpellCheckHandler.getPhonetsReader("fr")
//		);
//
//  SpellChecker spellCheck = new SpellChecker(dictionary);
//  SpellCheckHandler handler = new SpellCheckHandler();
//  spellCheck.addSpellCheckListener(handler);
//  
//  String toCheck = "Aalto dNs boneur boner bonh pascl pcheri 123-456 stars crstal";
//  int errors = spellCheck.checkSpelling(new StringWordTokenizer(toCheck.toLowerCase()));
//  if (errors ==0) {
//  	System.out.println("Nothing I can do");
//  	return;
//  }
//  
//  boolean IGNORE_NON_EXISTENT_WORDS = false;
//  //int depth = 4;
//  
//  ArrayList suggestions = new ArrayList();
//		Pattern p = Pattern.compile("\\p{Space}*([^\\p{Space}]{3,}?)\\p{Space}+");
//		Matcher m = p.matcher(" "+toCheck+" ");
//		int pos = 0;
//		while (m.find(pos)) {
//			pos = m.end()-1;
//			String word = m.group(1).trim().toLowerCase();
//			if (IGNORE_NON_EXISTENT_WORDS) {
//				if (handler.getSuggestions().containsKey(word)) {
//					//the spell hcecker di not ignore the word
//					Collection results = (Collection)handler.getSuggestions().get(word);
//					if (results.size()>0) {
//						suggestions.add(new ArrayList(results));
//					} // else we ignore the word because no suggestion
//				} else {
//					//the word exists (or has been  ignored on purpose by the spell checker)
//					//we suggest the original word
//					ArrayList results = new ArrayList();
//					results.add(new Word(word,0));
//					suggestions.add(new ArrayList(results));
//				}
//			} else {		
//				Collection results = (Collection)handler.getSuggestions().get(word);
//				if ((results==null) || (results.size()==0)) { 
//					results = new ArrayList();
//					results.add(new Word(word,0));
//				}
//				suggestions.add(new ArrayList(results));
//			}
//		}
//
//		System.out.println("TO CHECK: "+toCheck);
//		
//		ArrayList proposals = new ArrayList();
//		for (Iterator iter = suggestions.iterator(); iter.hasNext();) {
//			ArrayList sug = (ArrayList) iter.next();
//			ArrayList newProposals = new ArrayList();
//			for (Iterator iterator = sug.iterator(); iterator.hasNext(); ) {
//				Word suggestion = (Word) iterator.next();
//				if (proposals.size()==0) { // first run
//					newProposals.add(suggestion.getWord());
//				} else {
//					for (Iterator iterator2 = proposals.iterator(); iterator2.hasNext(); ) {
//						String proposal = (String) iterator2.next();
//						newProposals.add(proposal+" "+suggestion.getWord());
//					}
//				}
//			} //for suggestions
//			proposals = newProposals;
//		}//for words
//		
//		int i=0;
//		for (Iterator iter = proposals.iterator(); iter.hasNext(); ) {
//			String proposal = (String) iter.next();
//			System.out.println("P"+(++i)+": "+proposal);
//		}

		
		/*
		//iterate to build possibilities
		int proposalNum = 0;
		int firstWord = 0;
		int endWord = 0;
		while (true) {
			
			//build a proposal
		 String proposal = "";
			for (int i = 0; i < words.size(); i++) {
				proposal+="".equals(proposal) ?  "" : " ";
				int currentSugg =((Integer)currentSuggestion.get(i)).intValue(); 
				proposal+=((ArrayList)suggestions.get(i)).get(currentSugg);
			}
			System.out.println("PROPOSAL: "+proposal);
			proposalNum++;
			
			if (proposalNum == depth) break; 
			
			boolean noPossibility = true;
			boolean goOn = true;
			while(goOn) {
			 //try incrementing the first word
			 int currSugg = ((Integer)currentSuggestion.get(firstWord)).intValue();
			 int currMax = ((Integer)maxSuggestion.get(firstWord)).intValue();
			 if (currSugg<currMax) {
			  currentSuggestion.set(firstWord, new Integer(currSugg+1));
			 	noPossibility = false;
			 	goOn=false;
			 	break;
			 }

			 //reset the words
			 for (int i = 0; i < endWord; i++) {
			  currentSuggestion.set(i, new Integer(0));			  
 }
		  firstWord = 0;
		  //try incrementing second word
		  while (goOn) {
		currSugg = ((Integer)currentSuggestion.get(endWord)).intValue();
		currMax = ((Integer)maxSuggestion.get(endWord)).intValue();
		if (currSugg<currMax) {
		 currentSuggestion.set(endWord, new Integer(currSugg+1));
		 noPossibility = false;
		 goOn=false;
		 break;
		}
	 if(++endWord==words.size()) {
		 noPossibility = true; //no more possibility
		 goOn=false;
		 break;			  
	 }
		  }//incrementing the second word
			}//while find next 
			
			if (noPossibility) break;
			
		}//while next proposal
  */
 } 
 
 
//	private static String getXML(String filename) throws Exception{
//	 BufferedReader in = null;
//  in = new BufferedReader(
//  			new InputStreamReader(
//  			  new FileInputStream(filename),
//							"utf-8"
//					)
//		);
//	 String xsl="";
//  String line;
//  while ((line=in.readLine())!=null) xsl+=line+"\n";
//  return xsl;
// }
	
	/*********************************************************************
	 *MAIN
	 *********************************************************************/	
 
	 public static void main(String args[]) throws Exception {
	 	//testSpellCheck();
	// 	System.out.println(getTimestamp());
	 }
	
}