//package com.myblog.swt;
//
////import org.eclipse.wb.swt.SWTResourceManager;
// 
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.custom.TableEditor;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.graphics.Color;
//import org.eclipse.swt.graphics.Point;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.TableColumn;
//import org.eclipse.swt.widgets.TableItem;
//
//
//public class testCheck {
//
//	public static final Display display = new Display();
//
//	public static Shell shell = display.getActiveShell();
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		final Display display = Display.getDefault();
//		final Shell shell = new Shell();
//		shell.setSize(500, 375);
//
//		shell.setText("FORM");
//		shell.setBackground(new Color(Display.getCurrent(), 254, 250, 232));
//		shell.setSize(214, 237);
//		Point pt = new Point(214, 237);
//		shell.setSize(new Point(214, 237));
//		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
//		shell.setLayout(null);
//
//		final Table table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
//		table.setBounds(0, 0, 206, 129);
//		table.setHeaderVisible(true);
////		table.setFont(SWTResourceManager.getFont("��Բ", 9, SWT.NORMAL));
//
//		table.setLinesVisible(true);
//
//		TableColumn tableColumn0 = new TableColumn(table, SWT.CENTER);
//		tableColumn0.setWidth(46);
//		tableColumn0.setText("1");
//
//		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
//		tableColumn1.setWidth(52);
//		tableColumn1.setText("2");
//
//		TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
//		tableColumn2.setWidth(57);
//		tableColumn2.setText("3");
//
//		TableColumn tableColumn3 = new TableColumn(table, SWT.NONE);
//		tableColumn3.setWidth(47);
//		tableColumn3.setText("4");
//
//		Button buttonBottom = new Button(shell, SWT.NONE);
//		buttonBottom.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//
//				TableItem ti = new TableItem(table, SWT.NONE);
//				ti.setText(0, "aaa");
//				ti.setText(1, "bbb");
//				TableEditor tableEditor = new TableEditor(table);
//				tableEditor.grabHorizontal = true;
//
//				Button chkboxApp = new Button(table, SWT.CHECK | SWT.CENTER);
////				chkboxApp.setBackground(SWTResourceManager
////						.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
//				tableEditor.setEditor(chkboxApp, ti, 2);
//
//				TableEditor tableEditor2 = new TableEditor(table);
//				tableEditor2.grabHorizontal = true;
//
//				Button chkboxApp2 = new Button(table, SWT.CHECK | SWT.CENTER);
////				chkboxApp2.setBackground(SWTResourceManager
////						.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
//				tableEditor2.setEditor(chkboxApp2, ti, 3);
//
//			}
//		});
//		buttonBottom.setBounds(132, 163, 61, 22);
//		buttonBottom.setText("AddItem");
//		shell.open();
//
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//
//		display.dispose();
//	}
//}