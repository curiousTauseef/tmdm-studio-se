/*
 * Generated by XDoclet - Do not edit!
 */
package com.amalto.core.objects.configurationinfo.ejb.local;

/**
 * Local interface for ConfigurationInfoCtrl.
 * @xdoclet-generated at 31-08-09
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ConfigurationInfoCtrlLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * Creates or updates a configurationinfo
    * @throws XtentisException
    */
   public com.amalto.core.objects.configurationinfo.ejb.ConfigurationInfoPOJOPK putConfigurationInfo( com.amalto.core.objects.configurationinfo.ejb.ConfigurationInfoPOJO configurationInfo ) throws com.amalto.core.util.XtentisException;

   /**
    * Get configurationinfo
    * @throws XtentisException
    */
   public com.amalto.core.objects.configurationinfo.ejb.ConfigurationInfoPOJO getConfigurationInfo( com.amalto.core.objects.configurationinfo.ejb.ConfigurationInfoPOJOPK pk ) throws com.amalto.core.util.XtentisException;

   /**
    * Get a ConfigurationInfo - no exception is thrown: returns null if not found
    * @throws XtentisException
    */
   public com.amalto.core.objects.configurationinfo.ejb.ConfigurationInfoPOJO existsConfigurationInfo( com.amalto.core.objects.configurationinfo.ejb.ConfigurationInfoPOJOPK pk ) throws com.amalto.core.util.XtentisException;

   /**
    * Remove a Configuration Info
    * @throws XtentisException
    */
   public com.amalto.core.objects.configurationinfo.ejb.ConfigurationInfoPOJOPK removeConfigurationInfo( com.amalto.core.objects.configurationinfo.ejb.ConfigurationInfoPOJOPK pk ) throws com.amalto.core.util.XtentisException;

   /**
    * Retrieve all ConfigurationInfo PKs
    * @throws XtentisException
    */
   public java.util.Collection getConfigurationInfoPKs( java.lang.String regex ) throws com.amalto.core.util.XtentisException;

   /**
    * Auto Upgrades the core
    * @throws XtentisException
    */
   public void autoUpgrade(  ) throws com.amalto.core.util.XtentisException;

   /**
    * Auto Upgrades the core in the background- called on startup
    * @throws XtentisException
    */
   public void autoUpgradeInBackground(  ) throws com.amalto.core.util.XtentisException;

}