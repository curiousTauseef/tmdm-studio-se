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
package org.talend.mdm.repository.core.validate.datamodel.validator.impl;

import java.util.List;

import org.talend.mdm.repository.core.validate.datamodel.validator.visitor.IComponentValidateVisitor;
import org.talend.mdm.repository.core.validate.datamodel.validator.visitor.ValidateVistorRegistry;

/**
 * created by HHB on 2013-1-29 Detailled comment
 * 
 */
public class StudioElementValidator extends ElementValidator {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.mdm.repository.core.validate.datamodel.validator.IDataModelValidator#getVisitors()
     */
    @Override
    public List<IComponentValidateVisitor> getVisitors() {
        if (visitors == null) {
            visitors = ValidateVistorRegistry.getInstance().getVisitors("Element"); //$NON-NLS-1$
        }
        return visitors;
    }
}
