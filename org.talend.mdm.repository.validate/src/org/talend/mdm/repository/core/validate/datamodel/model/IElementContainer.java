// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.mdm.repository.core.validate.datamodel.model;

import java.util.List;

/**
 * created by HHB on 2013-1-6 Detailled comment
 * 
 */
public interface IElementContainer {

    public List<MElement> getElements();

    public void addElement(MElement element);

    public MRoot getRoot();

    public void setRoot(MRoot root);
}
