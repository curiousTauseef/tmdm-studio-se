// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.mdm.repository.utils;

import org.talend.core.GlobalServiceRegister;

/**
 * created by HHB on 2013-8-21 Detailled comment
 * 
 */
public class ServiceUtil {

    public static <T> T getService(Class<T> t) {
        T service = (T) GlobalServiceRegister.getDefault().getService(t);
        return service;
    }
}