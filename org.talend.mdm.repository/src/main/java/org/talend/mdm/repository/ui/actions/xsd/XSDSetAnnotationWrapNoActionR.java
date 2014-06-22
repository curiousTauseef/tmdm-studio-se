// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.mdm.repository.ui.actions.xsd;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.talend.mdm.repository.i18n.Messages;
import org.talend.mdm.repository.ui.dialogs.xsd.AnnotationOrderedListsDialogR;

import com.amalto.workbench.actions.XSDSetAnnotationWrapNoAction;
import com.amalto.workbench.dialogs.AnnotationOrderedListsDialog;
import com.amalto.workbench.editors.DataModelMainPage;

/**
 * DOC hbhong class global comment. Detailled comment
 */
public class XSDSetAnnotationWrapNoActionR extends XSDSetAnnotationWrapNoAction {

    /**
     * DOC hbhong XSDSetAnnotationWrapNoActionR constructor comment.
     * 
     * @param page
     * @param dataModelName
     */
    public XSDSetAnnotationWrapNoActionR(DataModelMainPage page, String dataModelName) {
        super(page, dataModelName);
    }

    @Override
    protected AnnotationOrderedListsDialog getNewAnnotaionOrderedListsDialog(Collection<String> values) {
        return new AnnotationOrderedListsDialogR(new ArrayList(values), new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                dlg.close();
            }
        }, page.getSite().getShell(), Messages.XSDSetAnnotationNoActionR_title, Messages.XSDSetAnnotationRoles, page,
                AnnotationOrderedListsDialog.AnnotationHidden_ActionType, dataModelName);
    }
}