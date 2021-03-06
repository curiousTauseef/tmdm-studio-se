/*****************************************************************************
 * This file is part of Rinzo
 * 
 * Author: Claudio Cancinos WWW: https://sourceforge.net/projects/editorxml Copyright (C): 2008, Claudio Cancinos
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this program; If not, see
 * <http://www.gnu.org/licenses/>
 ****************************************************************************/
package com.amalto.workbench.widgets.xmlviewer.annotation;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ICharacterPairMatcher;

import com.amalto.workbench.widgets.xmlviewer.model.XMLNode;
import com.amalto.workbench.widgets.xmlviewer.utils.XMLTreeModelUtilities;

/**
 * Returns the region for the name of the corresponding tag to the one selected
 * 
 */
public class TagPairMatcher implements ICharacterPairMatcher {

	public IRegion match(IDocument document, int offset) {
		XMLNode activeNode = XMLTreeModelUtilities.getActiveNode(document, offset);
		if (activeNode != null) {
			XMLNode correspondingNode = activeNode.getCorrespondingNode();
			if (correspondingNode != null) {
				if (activeNode.isTag() || activeNode.isDeclarationTag() || activeNode.isEndTag()) {
					return new Region(correspondingNode.getSelectionOffset(), correspondingNode.getTagName().length());
				}
			}
		}
		return null;
	}

	public int getAnchor() {
		return 0;
	}

	public void clear() {
	}

	public void dispose() {
	}

}
