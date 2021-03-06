// ============================================================================
//
// Copyright (C) 2006-2019 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.mdm.repository.core.service;

import org.talend.core.IService;
import org.talend.core.model.repository.IRepositoryViewObject;

/**
 * DOC hbhong  class global comment. Detailled comment
 */
public interface IMDMSVNProviderService extends IService {

    public String getCurrentSVNRevision(IRepositoryViewObject viewObj);

    public boolean isProjectInSvnMode();

    public String getLockInfo(IRepositoryViewObject viewObj);
}
