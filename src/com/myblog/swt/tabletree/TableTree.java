package com.myblog.swt.tabletree;
//package com.myblog.components.tabletree;
//
//import java.io.File;
//
//import org.eclipse.jface.viewers.ILabelProviderListener;
//import org.eclipse.jface.viewers.ITableLabelProvider;
//import org.eclipse.jface.viewers.ITreeContentProvider;
//import org.eclipse.jface.viewers.TreeViewer;
//import org.eclipse.jface.viewers.Viewer;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.TreeColumn;
//
//public class TableTree {
//
//	public void run() {
//		final Display display = new Display();
//		final Shell shell = new Shell(display);
//		shell.setLayout(new FillLayout());
//
//		final TreeViewer viewer = new TreeViewer(shell, SWT.FULL_SELECTION);
//		viewer.getTree().setHeaderVisible(true);
//		TreeColumn column = new TreeColumn(viewer.getTree(), SWT.LEFT);
//		column.setText("Name");
//		column.setWidth(200);
//		column = new TreeColumn(viewer.getTree(), SWT.LEFT);
//		column.setText("Size");
//		column.setWidth(100);
//		column = new TreeColumn(viewer.getTree(), SWT.LEFT);
//		column.setText("Hidden");
//		column.setWidth(100);
//		viewer.setContentProvider(new MyTreeContenetProvider());
//		viewer.setLabelProvider(new MyTableLableProvider());
//		viewer.setInput(new File("/"));
//
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//		display.dispose();
//	}
//
//	public static void main(String[] args) {
//		new TableTree().run();
//	}
//
//	class MyTreeContenetProvider implements ITreeContentProvider {
//
//		public Object[] getChildren(Object parentElement) {
//			File file = (File) parentElement;
//			if (file.isDirectory())
//				return file.listFiles();
//			else
//				return null;
//		}
//
//		public Object getParent(Object element) {
//			File file = (File) element;
//			return file.getParentFile();
//		}
//
//		public boolean hasChildren(Object element) {
//			File file = (File) element;
//			return file.isDirectory()/* &&file.list().length>0 */;
//		}
//
//		public Object[] getElements(Object inputElement) {
//			File file = (File) inputElement;
//			return file.isDirectory() ? file.listFiles()
//					: new Object[] { file };
//		}
//
//		public void dispose() {
//
//		}
//
//		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//
//		}
//
//	}
//
//	class MyTableLableProvider implements ITableLabelProvider {
//
//		public Image getColumnImage(Object element, int columnIndex) {
//			return null;
//		}
//
//		public String getColumnText(Object element, int columnIndex) {
//			File file = (File) element;
//			switch (columnIndex) {
//			case 0:
//				return file.getName();
//			case 1:
//				return "" + file.length();
//			case 2:
//				return "" + file.isHidden();
//			default:
//				return "";
//			}
//		}
//
//		public void addListener(ILabelProviderListener listener) {
//
//		}
//
//		public void dispose() {
//
//		}
//
//		public boolean isLabelProperty(Object element, String property) {
//			return false;
//		}
//
//		public void removeListener(ILabelProviderListener listener) {
//
//		}
//
//	}
//}