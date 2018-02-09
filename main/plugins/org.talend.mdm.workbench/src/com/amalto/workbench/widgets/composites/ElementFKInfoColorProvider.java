// ============================================================================
//
// Copyright (C) 2006-2018 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package com.amalto.workbench.widgets.composites;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ElementFKInfoColorProvider {

    private Map<RGB, Color> colorTable = new HashMap<RGB, Color>(10);

    private Map<String, IToken> tokenTable = new HashMap<String, IToken>(10);

    IPreferenceStore store;

    public ElementFKInfoColorProvider(IPreferenceStore store) {
        this.store = store;
    }

    public IToken getToken(String prefKey) {
        Token token = (Token) tokenTable.get(prefKey);
        if (token == null) {
            String colorName = store.getString(prefKey);
            if (colorName == null || colorName.isEmpty()) {
                if (prefKey.equals(ElementFKInfoConfiguration.PREF_COLOR_DEFAULT)) {
                    colorName = "0,128,0"; //$NON-NLS-1$
                } else if (prefKey.equals(ElementFKInfoConfiguration.PREF_COLOR_STRING)) {
                    colorName = "0,0,255"; //$NON-NLS-1$
                } else if (prefKey.equals(ElementFKInfoConfiguration.PREF_COLOR_KEYWORD)) {
                    colorName = "0,0,128"; //$NON-NLS-1$
                }

            }
            RGB rgb = StringConverter.asRGB(colorName);
            token = new Token(new TextAttribute(getColor(rgb)));
            tokenTable.put(prefKey, token);
        }
        return token;
    }

    public void dispose() {
        Iterator<Color> e = colorTable.values().iterator();
        while (e.hasNext()) {
            e.next().dispose();
        }
    }

    public Color getColor(String prefKey) {
        String colorName = store.getString(prefKey);
        RGB rgb = StringConverter.asRGB(colorName);
        return getColor(rgb);
    }

    private Color getColor(RGB rgb) {
        Color color = colorTable.get(rgb);
        if (color == null) {
            color = new Color(Display.getCurrent(), rgb);
            colorTable.put(rgb, color);
        }
        return color;
    }

    public boolean affectsTextPresentation(PropertyChangeEvent event) {
        Token token = (Token) tokenTable.get(event.getProperty());
        return (token != null);
    }

    public void handlePreferenceStoreChanged(PropertyChangeEvent event) {
        String prefKey = event.getProperty();
        Token token = (Token) tokenTable.get(prefKey);
        if (token != null) {
            String colorName = store.getString(prefKey);
            RGB rgb = StringConverter.asRGB(colorName);
            token.setData(new TextAttribute(getColor(rgb)));
        }
    }
}
