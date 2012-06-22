package com.niranjan.grihasthi;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.niranjan.grihasthi.data.GrihasthiDBO;
import com.niranjan.grihasthi.data.LocationDBO;
import com.niranjan.grihasthi.data.OrientDal;

public class NavigationView extends ViewPart {
	public static final String ID = "com.niranjan.grihasthi.navigationView";
	private TreeViewer viewer;

	class TreeObject {
		private final String name;
		private TreeParent parent;
		private GrihasthiDBO dataObject;

		public TreeObject(final String name) {
			this.name = name;

		}

		public TreeObject(final GrihasthiDBO obj) {
			this.dataObject = obj;
			this.name = dataObject.getTreeName();
		}

		public String getName() {
			return name;
		}

		public void setParent(final TreeParent parent) {
			this.parent = parent;
		}

		public TreeParent getParent() {
			return parent;
		}

		@Override
		public String toString() {
			return getName();
		}
	}

	class TreeParent extends TreeObject {
		private final ArrayList<TreeObject> children;

		public TreeParent(final GrihasthiDBO obj) {
			super(obj);
			children = new ArrayList<TreeObject>();
		}

		public TreeParent(final String name) {
			super(name);
			children = new ArrayList<TreeObject>();
		}

		public void addChild(final TreeObject child) {
			children.add(child);
			child.setParent(this);
		}

		public void removeChild(final TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren() {
			return children.toArray(new TreeObject[children.size()]);
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {

		@Override
		public void inputChanged(final Viewer v, final Object oldInput,
				final Object newInput) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(final Object parent) {
			return getChildren(parent);
		}

		@Override
		public Object getParent(final Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		@Override
		public Object[] getChildren(final Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		@Override
		public boolean hasChildren(final Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).hasChildren();
			}
			return false;
		}
	}

	class ViewLabelProvider extends LabelProvider {

		@Override
		public String getText(final Object obj) {
			return obj.toString();
		}

		@Override
		public Image getImage(final Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent) {
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			}
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(imageKey);
		}
	}

	/**
	 * We will set up a dummy model to initialize tree heararchy. In real code,
	 * you will connect to a real model and expose its hierarchy.
	 */
	TreeObject createDummyModel() {
		final TreeObject to1 = new TreeObject("Inbox");
		final TreeObject to2 = new TreeObject("Drafts");
		final TreeObject to3 = new TreeObject("Sent");
		final TreeParent p1 = new TreeParent("me@this.com");
		p1.addChild(to1);
		p1.addChild(to2);
		p1.addChild(to3);

		final TreeObject to4 = new TreeObject("Inbox");
		final TreeParent p2 = new TreeParent("other@aol.com");
		p2.addChild(to4);

		final TreeParent root = new TreeParent("");
		root.addChild(p1);
		root.addChild(p2);
		return root;
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(final Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.BORDER);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(createTreeModel());
	}

	private Object createTreeModel() {
		final Collection<LocationDBO> locations = OrientDal.readData(
				"select from " + LocationDBO.RECORD_TYPE,
				OrientDal.LOCATION_INSTANCE_CREATOR);
		final TreeParent root = new TreeParent("");
		for (final LocationDBO locationDBO : locations) {
			root.addChild(new TreeParent(locationDBO));
		}
		return root;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}