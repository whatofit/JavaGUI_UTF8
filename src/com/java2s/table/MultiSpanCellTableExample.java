package com.java2s.table;

//http://www.apihome.cn/api/java/JTable.html

//重写TableUI，来达到实现表格的合并和分解。

//真正实现表格多行多列合并的是在MultiSpanCellTableUI这个类里实现，这个类完全重新绘制表格从而达到合并的目的。
//代码很精练，不到100行，就搞定了表格的绘制。
//
//MultiSpanCellTable 继承于JTable，主要是重载了和表格定位相关的方法，
//比如rowColumnAtPoint，getCellRect，rowAtPoint这几个方法，
//根据Model里的数据来重新计算，道理也很简单,这样才能正确的判断用户选择的单元格，
//因为被合并的单元格是无法选择的和无法显示的，所以必须要在这里进行。代码还是百来行。
//
//AttributiveCellTableModel继续于DefaultTableModel，
//最关键的东西在这里，CellAttribute，他的实现是交给DefaultCellAttribute这个对象的，这个是存储单元格合并信息的地方，
//而且也是MultiSpanCellTable在计算的重要数据，和MultiSpanCellTableUI绘制的核心数据之一。
//DefaultCellAttribute有三个接口分别对应表格的功能。
//
//CellAttribute保存的是真正的视野上的表格信息，包括表格有多少行多少列（这是视觉上的不是逻辑上），
//JTable逻辑数据任然是在传统的model，逻辑数据每一次改变都将会映射到这里，会刷新视觉数据。
//
//CellSpan 保存的是单元格的合并属性，可视行，行跨度，列跨度。这些是UI绘制必需的东西。
//还有就是合并和拆分的功能也在这里，非常值得注意的是，在拆分合并表格的时候，他没有处理任何TableModel和Table的东西，就是在这里处理数据，
//处理完毕JTable重刷，这种M和V隔离的是相当的清晰。

//Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
/* (swing1.1) (swing#1356,#1454) */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


/*   ----------------------------------------------
 *  |         SNo.        |
 *   ----------------------------------------------
 *  |          |     1    |
 *  |   Name   |-----------------------------------
 *  |          |     2    |
 *   ----------------------------------------------
 *  |          |     1    |
 *  |          |-----------------------------------
 *  | Language |     2    |
 *  |          |-----------------------------------
 *  |          |     3    |
 *   ----------------------------------------------
 */

/**
 * @version 1.0 11/26/98
 */
public class MultiSpanCellTableExample extends JFrame {

	MultiSpanCellTableExample() {
		super("Multi-Span Cell Example");

		final AttributiveCellTableModel tableModel = new AttributiveCellTableModel(10, 6);//10行,6列
		/*
		 * AttributiveCellTableModel ml = new AttributiveCellTableModel(10,6) {
		 * public Object getValueAt(int row, int col) { return "" + row + ","+
		 * col; } };
		 */
		final MultiSpanCellTable table = new MultiSpanCellTable(tableModel);
		JScrollPane scroll = new JScrollPane(table);

		final CellSpan cellAtt = (CellSpan) tableModel.getCellAttribute();
		
		JButton b_one = new JButton("Combine");
		b_one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] columns = table.getSelectedColumns();
				int[] rows = table.getSelectedRows();
				System.out.println("getSelectedColumns=" + Arrays.toString(columns));
				System.out.println("getSelectedRows=" + Arrays.toString(rows));
				cellAtt.combine(rows, columns);
				tableModel.changeAllCellAttribute();
				table.clearSelection();
				table.revalidate();
				table.repaint();
			}
		});
		JButton b_split = new JButton("Split");
		b_split.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = table.getSelectedColumn();
				int row = table.getSelectedRow();
				cellAtt.split(row, column);
				table.clearSelection();
				//revalidate()是把布局管理器对应的容器内的子组件重新计算大小,布局并绘制。
				//revalidate()是Jcompnent的方法。它并不是立即改变组件的大小，而是标记该组件需要改变大小。
				//这样就可以避免了多个组件都要改变大小时带来的重复计算。
				//但是，如果想重新计算一个Jframe中的所有组件，就需要调用repaint()方法
				//validate方法是告诉父容器，“我更新了，你要重绘！”。
				//容器自身的重绘，轻量级的方法一般调用repain()。
				table.revalidate();
				table.repaint();
			}
		});
		JPanel p_buttons = new JPanel();
		p_buttons.setLayout(new GridLayout(2, 1));
		p_buttons.add(b_one);
		p_buttons.add(b_split);

		Box box = new Box(BoxLayout.X_AXIS);
		box.add(scroll);
		box.add(new JSeparator(SwingConstants.HORIZONTAL));
		box.add(p_buttons);
		getContentPane().add(box);
		setSize(600, 300);
	}

	public static void main(String[] args) {
		MultiSpanCellTableExample frame = new MultiSpanCellTableExample();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
}

/**
 * @version 1.0 11/22/98
 */
//带属性单元格的TableModel
class AttributiveCellTableModel extends DefaultTableModel {

	protected CellAttribute cellAtt;

//	public AttributiveCellTableModel() {
//		this((Vector) null, 0);
//	}

	public AttributiveCellTableModel(int numRows, int numColumns) {
		Vector names = new Vector(numColumns);
		//System.out.println("AttributiveCellTableModel,names.size()=" + names.size());
		names.setSize(numColumns);
		setColumnIdentifiers(names);
		//dataVector = new Vector();
		setNumRows(numRows);
		cellAtt = new DefaultCellAttribute(numRows, numColumns);
		changeAllCellAttribute();
	}

//	public AttributiveCellTableModel(Vector columnNames, int numRows) {
//		setColumnIdentifiers(columnNames);
//		dataVector = new Vector();
//		setNumRows(numRows);
//		cellAtt = new DefaultCellAttribute(numRows, columnNames.size());
//	}
//
//	public AttributiveCellTableModel(Object[] columnNames, int numRows) {
//		this(convertToVector(columnNames), numRows);
//	}
//
//	public AttributiveCellTableModel(Vector data, Vector columnNames) {
//		setDataVector(data, columnNames);
//	}
//
//	public AttributiveCellTableModel(Object[][] data, Object[] columnNames) {
//		setDataVector(data, columnNames);
//	}
//
//	public void setDataVector(Vector newData, Vector columnNames) {
//		if (newData == null)
//			throw new IllegalArgumentException(
//					"setDataVector() - Null parameter");
//		dataVector = new Vector(0);
//		setColumnIdentifiers(columnNames);
//		dataVector = newData;
//
//		//
//		cellAtt = new DefaultCellAttribute(dataVector.size(),
//				columnIdentifiers.size());
//
//		newRowsAdded(new TableModelEvent(this, 0, getRowCount() - 1,
//				TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
//	}

//	public void addColumn(Object columnName, Vector columnData) {
//		if (columnName == null)
//			throw new IllegalArgumentException("addColumn() - null parameter");
//		columnIdentifiers.addElement(columnName);
//		int index = 0;
//		Enumeration enumeration = dataVector.elements();
//		while (enumeration.hasMoreElements()) {
//			Object value;
//			if ((columnData != null) && (index < columnData.size()))
//				value = columnData.elementAt(index);
//			else
//				value = null;
//			((Vector) enumeration.nextElement()).addElement(value);
//			index++;
//		}
//
//		//
//		cellAtt.addColumn();
//
//		fireTableStructureChanged();
//	}

//	public void addRow(Vector rowData) {
//		Vector newData = null;
//		if (rowData == null) {
//			newData = new Vector(getColumnCount());
//		} else {
//			rowData.setSize(getColumnCount());
//		}
//		dataVector.addElement(newData);
//
//		//
//		cellAtt.addRow();
//
//		newRowsAdded(new TableModelEvent(this, getRowCount() - 1,
//				getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
//				TableModelEvent.INSERT));
//	}

//	public void insertRow(int row, Vector rowData) {
//		if (rowData == null) {
//			rowData = new Vector(getColumnCount());
//		} else {
//			rowData.setSize(getColumnCount());
//		}
//
//		dataVector.insertElementAt(rowData, row);
//
//		//
//		cellAtt.insertRow(row);
//
//		newRowsAdded(new TableModelEvent(this, row, row,
//				TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
//	}

	public CellAttribute getCellAttribute() {
		return cellAtt;
	}

//	public void setCellAttribute(CellAttribute newCellAtt) {
//		int numColumns = getColumnCount();
//		int numRows = getRowCount();
//		if ((newCellAtt.getSize().width != numColumns)
//				|| (newCellAtt.getSize().height != numRows)) {
//			newCellAtt.setSize(new Dimension(numRows, numColumns));
//		}
//		cellAtt = newCellAtt;
//		fireTableDataChanged();
//	}

	/*
	 * public void changeCellAttribute(int row, int column, Object command) {
	 * cellAtt.changeAttribute(row, column, command); }
	 * 
	 * public void changeCellAttribute(int[] rows, int[] columns, Object
	 * command) { cellAtt.changeAttribute(rows, columns, command); }
	 */

	public void changeAllCellAttribute() {
		dataVector = cellAtt.getAllCellValue();
	}
}

/**
 * @version 1.0 11/22/98
 */
//默认单元格属性，继承与CellAttribute与CellSpan
class DefaultCellAttribute
 implements CellAttribute ,CellSpan {
//		implements CellAttribute, CellSpan, ColoredCell, CellFont {

	//
	// !!!! CAUTION !!!!!
	// these values must be synchronized to Table data
	//
	protected int rowSize;  //表格总行数

	protected int columnSize;   //表格总列数

	//某个单元格的列与行的跨度值,1代表正常的单元格,行正整数代表合并了多少个行单元格，行负数代表被合并(即：小于1的单元格，都是不可见单元格),无0跨度
	protected int[][][] span; // CellSpan

//	protected Color[][] foreground; // ColoredCell
//
//	protected Color[][] background; //
//
//	protected Font[][] font; // CellFont

//	public DefaultCellAttribute() {
//		this(1, 1);
//	}

	public DefaultCellAttribute(int numRows, int numColumns) {
		setSize(new Dimension(numColumns, numRows));
	}

	//设置单元格行列方向上，跨度都为1，即单元格无跨度，原始状态
	protected void initValue() {
		for (int i = 0; i < span.length; i++) {
			for (int j = 0; j < span[i].length; j++) {
				span[i][j][CellSpan.COLUMN] = 1;
				span[i][j][CellSpan.ROW] = 1;
			}
		}
	}
	
	//设置单元格的值，起始x/y 宽/width高/height
	public Vector getAllCellValue() {
		Vector cellsVector = new Vector();
		for (int i = 0; i < span.length; i++) {
			Vector v = new Vector();
			for (int j = 0; j < span[i].length; j++) {
				v.add(span[i][j][CellSpan.ROW] + " X " + span[i][j][CellSpan.COLUMN]) ;
			}
			cellsVector.add(v);
		}
		return cellsVector;
	}
	
	//
	// CellSpan
	//
	public int[] getSpan(int row, int column) {
		if (isOutOfBounds(row, column)) {
			int[] ret_code = { 1, 1 };
			return ret_code;
		}
		return span[row][column];
	}

//	public void setSpan(int[] span, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		this.span[row][column] = span;
//	}

	public boolean isVisible(int row, int column) {
		if (isOutOfBounds(row, column))
			return false;
		if ((span[row][column][CellSpan.COLUMN] < 1)
				|| (span[row][column][CellSpan.ROW] < 1))
			return false;
		return true;
	}

	public void combine(int[] rows, int[] columns) {
		if (isOutOfBounds(rows, columns))
			return;
		int rowSpan = rows.length;
		int columnSpan = columns.length;
//		if (rowSpan == 0 || columnSpan == 0) {
//			return;
//		}
		int startRow = rows[0];
		int startColumn = columns[0];
		for (int i = 0; i < rowSpan; i++) {
			for (int j = 0; j < columnSpan; j++) {
				//合并单元格前，所有单元格都是原始单元格，没有合并其他单元格也没有被其他单元格合并
				if ((span[startRow + i][startColumn + j][CellSpan.COLUMN] != 1)
						|| (span[startRow + i][startColumn + j][CellSpan.ROW] != 1)) { 
					// System.out.println("can't combine");
					return;
				}
			}
		}
		for (int i = 0, ii = 0; i < rowSpan; i++, ii--) {
			for (int j = 0, jj = 0; j < columnSpan; j++, jj--) {
                //设置已被合并后的单元格离合并后的主单元格的列跨度/行跨度(负数),暂设置自身离自己是0跨度
				span[startRow + i][startColumn + j][CellSpan.COLUMN] = jj;
				span[startRow + i][startColumn + j][CellSpan.ROW] = ii;
				// System.out.println("r " +ii +" c " +jj);
			}
		}
        //设置已合并后的单元格的列跨度/行跨度(整数)
		span[startRow][startColumn][CellSpan.COLUMN] = columnSpan;
		span[startRow][startColumn][CellSpan.ROW] = rowSpan;
	}

	public void split(int row, int column) {
		if (isOutOfBounds(row, column))
			return;
		int columnSpan = span[row][column][CellSpan.COLUMN];
		int rowSpan = span[row][column][CellSpan.ROW];
		for (int i = 0; i < rowSpan; i++) {
			for (int j = 0; j < columnSpan; j++) {
				//重置单元格跨度
				span[row + i][column + j][CellSpan.COLUMN] = 1;
				span[row + i][column + j][CellSpan.ROW] = 1;
			}
		}
	}

//	//
//	// ColoredCell
//	//
//	public Color getForeground(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return null;
//		return foreground[row][column];
//	}
//
//	public void setForeground(Color color, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		foreground[row][column] = color;
//	}
//
//	public void setForeground(Color color, int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		setValues(foreground, color, rows, columns);
//	}
//
//	public Color getBackground(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return null;
//		return background[row][column];
//	}
//
//	public void setBackground(Color color, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		background[row][column] = color;
//	}
//
//	public void setBackground(Color color, int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		setValues(background, color, rows, columns);
//	}

//	//
//
//	//
//	// CellFont
//	//
//	public Font getFont(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return null;
//		return font[row][column];
//	}
//
//	public void setFont(Font font, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		this.font[row][column] = font;
//	}
//
//	public void setFont(Font font, int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		setValues(this.font, font, rows, columns);
//	}

	//

	//
	// CellAttribute
	//
//	public void addColumn() {
//		int[][][] oldSpan = span;
//		int numRows = oldSpan.length;
//		int numColumns = oldSpan[0].length;
//		span = new int[numRows][numColumns + 1][2]; //增加一列
//		System.arraycopy(oldSpan, 0, span, 0, numRows);
//		for (int i = 0; i < numRows; i++) {
//			span[i][numColumns][CellSpan.COLUMN] = 1;
//			span[i][numColumns][CellSpan.ROW] = 1;
//		}
//	}

//	public void addRow() {
//		int[][][] oldSpan = span;
//		int numRows = oldSpan.length;
//		int numColumns = oldSpan[0].length;
//		span = new int[numRows + 1][numColumns][2]; //增加一行
//		System.arraycopy(oldSpan, 0, span, 0, numRows);
//		for (int i = 0; i < numColumns; i++) {
//			span[numRows][i][CellSpan.COLUMN] = 1;
//			span[numRows][i][CellSpan.ROW] = 1;
//		}
//	}

//	public void insertRow(int row) {
//		int[][][] oldSpan = span;
//		int numRows = oldSpan.length;
//		int numColumns = oldSpan[0].length;
//		span = new int[numRows + 1][numColumns][2];//增加一样
//		if (0 < row) {
//			System.arraycopy(oldSpan, 0, span, 0, row - 1); //拷贝row行之前的数据，
//		}
//		System.arraycopy(oldSpan, 0, span, row, numRows - row);//拷贝row行之后的数据
//		for (int i = 0; i < numColumns; i++) {  //初始化插入行位置数据
//			span[row][i][CellSpan.COLUMN] = 1;
//			span[row][i][CellSpan.ROW] = 1;
//		}
//	}

	public Dimension getSize() {
		return new Dimension(rowSize, columnSize);
	}

	public void setSize(Dimension size) {
		columnSize = size.width;
		rowSize = size.height;
		span = new int[rowSize][columnSize][2]; // 2: COLUMN,ROW
		//foreground = new Color[rowSize][columnSize];
		//background = new Color[rowSize][columnSize];
		//font = new Font[rowSize][columnSize];
		initValue();
	}

	/*
	 * public void changeAttribute(int row, int column, Object command) { }
	 * 
	 * public void changeAttribute(int[] rows, int[] columns, Object command) {
	 * }
	 */

	//用于拆分单元格
	protected boolean isOutOfBounds(int row, int column) {
		if ((row < 0) || (rowSize <= row) || (column < 0)
				|| (columnSize <= column)) {
			return true;
		}
		return false;
	}

	//用于合并单元格
	protected boolean isOutOfBounds(int[] rows, int[] columns) {
		if (rows.length == 0 || columns.length == 0) {
			return true;
		}
		for (int i = 0; i < rows.length; i++) {
			if ((rows[i] < 0) || (rowSize <= rows[i]))
				return true;
		}
		for (int i = 0; i < columns.length; i++) {
			if ((columns[i] < 0) || (columnSize <= columns[i]))
				return true;
		}
		return false;
	}

//	protected void setValues(Object[][] target, Object value, int[] rows,
//			int[] columns) {
//		for (int i = 0; i < rows.length; i++) {
//			int row = rows[i];
//			for (int j = 0; j < columns.length; j++) {
//				int column = columns[j];
//				target[row][column] = value;
//			}
//		}
//	}
}

/*
 * (swing1.1beta3)
 */

/**
 * @version 1.0 11/22/98
 */

interface CellAttribute {

//	public void addColumn();

//	public void addRow();

//	public void insertRow(int row);

	public Dimension getSize();

	public void setSize(Dimension size);

	public Vector getAllCellValue();
}

/*
 * (swing1.1beta3)
 */

///**
// * @version 1.0 11/22/98
// */
//
//interface CellFont {
//
//	public Font getFont(int row, int column);
//
//	public void setFont(Font font, int row, int column);
//
//	public void setFont(Font font, int[] rows, int[] columns);
//
//}
//
//interface ColoredCell {
//
//	public Color getForeground(int row, int column);
//
//	public void setForeground(Color color, int row, int column);
//
//	public void setForeground(Color color, int[] rows, int[] columns);
//
//	public Color getBackground(int row, int column);
//
//	public void setBackground(Color color, int row, int column);
//
//	public void setBackground(Color color, int[] rows, int[] columns);
//
//}

interface CellSpan {
	public final int ROW = 0;

	public final int COLUMN = 1;

	public int[] getSpan(int row, int column);

//	public void setSpan(int[] span, int row, int column);

	public boolean isVisible(int row, int column);

	public void combine(int[] rows, int[] columns);

	public void split(int row, int column);

}

/*
 * (swing1.1beta3)
 */

/**
 * @version 1.0 11/26/98
 */

class MultiSpanCellTable extends JTable {

	public MultiSpanCellTable(TableModel model) {
		super(model);
		setUI(new MultiSpanCellTableUI());
		getTableHeader().setReorderingAllowed(false);
		setCellSelectionEnabled(true);
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//允许选择相邻的一系列行。
	}

	//返回位于 row 和 column 相交位置的单元格矩形。
	//如果 includeSpacing 为 true，则返回的值具有指定行和列的完整高度和宽度。
	//如果为 false，则返回的矩形为单元格空间减去单元格间的间隙，以便在呈现期间设置该属性后，返回呈现和编辑的组件的真实边界。
	//如果列索引有效但是行索引小于 0，则此方法返回一个矩形，此矩形的 y 和 height 设置为合适的值，其x 和width 值都设置为 0。通常，行索引或列索引指示适当区域外的单元格时，此方法都返回一个矩形，它描绘了表范围内最近单元格的最近边。当行索引和列索引都超出范围时，返回的矩形覆盖了最近单元格的最近点。
	//在所有的情形中，使用此方法沿一个轴的计算结果不会因为沿另一个轴的计算出现异常而失败。当单元格无效时，忽略 includeSpacing 参数。
	//参数：
	//row - 所需单元格所在的行索引
	//column - 所需单元格所在的列索引；不一定与表中数据模型的列索引相同；convertColumnIndexToView(int) 方法可以用来将数据模型的列索引转换为显示的列索引
	//includeSpacing - 如果为 false，则返回实际的单元格边界，计算方法是从列模型和行模型的高度和宽度中减去单元格间距
	//返回：
	//包含 row、column 处单元格的矩形
	public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
		Rectangle sRect = super.getCellRect(row, column, includeSpacing);
		if ((row < 0) || (column < 0) || (getRowCount() <= row)
				|| (getColumnCount() <= column)) {
			return sRect;
		}
		CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) getModel())
				.getCellAttribute();
		if (!cellAtt.isVisible(row, column)) {
			int temp_row = row;
			int temp_column = column;
			row += cellAtt.getSpan(temp_row, temp_column)[CellSpan.ROW];
			column += cellAtt.getSpan(temp_row, temp_column)[CellSpan.COLUMN];
		}
		int[] n = cellAtt.getSpan(row, column);

		int index = 0;
		int columnMargin = getColumnModel().getColumnMargin();
		Rectangle cellFrame = new Rectangle();
		int aCellHeight = rowHeight + rowMargin;
		cellFrame.y = row * aCellHeight;
		cellFrame.height = n[CellSpan.ROW] * aCellHeight;

		Enumeration enumeration = getColumnModel().getColumns();
		while (enumeration.hasMoreElements()) {
			TableColumn aColumn = (TableColumn) enumeration.nextElement();
			cellFrame.width = aColumn.getWidth() + columnMargin;
			if (index == column)
				break;
			cellFrame.x += cellFrame.width;
			index++;
		}
		for (int i = 0; i < n[CellSpan.COLUMN] - 1; i++) {
			TableColumn aColumn = (TableColumn) enumeration.nextElement();
			cellFrame.width += aColumn.getWidth() + columnMargin;
		}

		if (!includeSpacing) {
			Dimension spacing = getIntercellSpacing();
			cellFrame.setBounds(cellFrame.x + spacing.width / 2, cellFrame.y
					+ spacing.height / 2, cellFrame.width - spacing.width,
					cellFrame.height - spacing.height);
		}
		return cellFrame;
	}

	private int[] rowColumnAtPoint(Point point) {
		int[] retValue = { -1, -1 };
		int row = point.y / (rowHeight + rowMargin);
		if ((row < 0) || (getRowCount() <= row))
			return retValue;
		int column = getColumnModel().getColumnIndexAtX(point.x);

		CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) getModel())
				.getCellAttribute();

		if (cellAtt.isVisible(row, column)) {
			retValue[CellSpan.COLUMN] = column;
			retValue[CellSpan.ROW] = row;
			return retValue;
		}
		retValue[CellSpan.COLUMN] = column
				+ cellAtt.getSpan(row, column)[CellSpan.COLUMN];
		retValue[CellSpan.ROW] = row
				+ cellAtt.getSpan(row, column)[CellSpan.ROW];
		return retValue;
	}

	public int rowAtPoint(Point point) {
		return rowColumnAtPoint(point)[CellSpan.ROW];
	}

//	public int columnAtPoint(Point point) {
//		return rowColumnAtPoint(point)[CellSpan.COLUMN];
//	}

//	public void columnSelectionChanged(ListSelectionEvent e) {
//		repaint();
//	}

	public void valueChanged(ListSelectionEvent e) {
		int firstIndex = e.getFirstIndex();
		int lastIndex = e.getLastIndex();
		if (firstIndex == -1 && lastIndex == -1) { // Selection cleared.
			repaint();
		}
		Rectangle dirtyRegion = getCellRect(firstIndex, 0, false);
		int numCoumns = getColumnCount();
		int index = firstIndex;
		for (int i = 0; i < numCoumns; i++) {
			dirtyRegion.add(getCellRect(index, i, false));
		}
		index = lastIndex;
		for (int i = 0; i < numCoumns; i++) {
			dirtyRegion.add(getCellRect(index, i, false));
		}
		repaint(dirtyRegion.x, dirtyRegion.y, dirtyRegion.width,
				dirtyRegion.height);
	}

}

/**
 * @version 1.0 11/26/98
 */

class MultiSpanCellTableUI extends BasicTableUI {

	public void paint(Graphics g, JComponent c) {
		Rectangle oldClipBounds = g.getClipBounds();
		Rectangle clipBounds = new Rectangle(oldClipBounds);
		int tableWidth = table.getColumnModel().getTotalColumnWidth();
		clipBounds.width = Math.min(clipBounds.width, tableWidth);
		g.setClip(clipBounds);

		int firstIndex = table.rowAtPoint(new Point(0, clipBounds.y));
		int lastIndex = table.getRowCount() - 1;

		Rectangle rowRect = new Rectangle(0, 0, tableWidth,
				table.getRowHeight() + table.getRowMargin());
		rowRect.y = firstIndex * rowRect.height;

		for (int index = firstIndex; index <= lastIndex; index++) {
			if (rowRect.intersects(clipBounds)) {
				// System.out.println(); // debug
				// System.out.print("" + index +": "); // row
				paintRow(g, index);
			}
			rowRect.y += rowRect.height;
		}
		g.setClip(oldClipBounds);
	}

	private void paintRow(Graphics g, int row) {
		Rectangle rect = g.getClipBounds();
		boolean drawn = false;

		AttributiveCellTableModel tableModel = (AttributiveCellTableModel) table
				.getModel();
		CellSpan cellAtt = (CellSpan) tableModel.getCellAttribute();
		int numColumns = table.getColumnCount();

		for (int column = 0; column < numColumns; column++) {
			Rectangle cellRect = table.getCellRect(row, column, true);
			int cellRow, cellColumn;
			if (cellAtt.isVisible(row, column)) {
				cellRow = row;
				cellColumn = column;
				// System.out.print(" "+column+" "); // debug
			} else {
				cellRow = row + cellAtt.getSpan(row, column)[CellSpan.ROW];
				cellColumn = column
						+ cellAtt.getSpan(row, column)[CellSpan.COLUMN];
				// System.out.print(" ("+column+")"); // debug
			}
			if (cellRect.intersects(rect)) {
				drawn = true;
				paintCell(g, cellRect, cellRow, cellColumn);
			} else {
				if (drawn)
					break;
			}
		}

	}
	
	private void paintCell(Graphics g, Rectangle cellRect, int row, int column) {
		int spacingHeight = table.getRowMargin();
		int spacingWidth = table.getColumnModel().getColumnMargin();
		
		Color c = g.getColor();
		g.setColor(table.getGridColor());
		g.drawRect(cellRect.x, cellRect.y, cellRect.width - 1,
				cellRect.height - 1);
		g.setColor(c);

		cellRect.setBounds(cellRect.x + spacingWidth / 2, cellRect.y
				+ spacingHeight / 2, cellRect.width - spacingWidth,
				cellRect.height - spacingHeight);

		if (table.isEditing() && table.getEditingRow() == row
				&& table.getEditingColumn() == column) {
			Component component = table.getEditorComponent();
			component.setBounds(cellRect);
			component.validate();
		} else {
			TableCellRenderer renderer = table.getCellRenderer(row, column);
			Component component = table.prepareRenderer(renderer, row, column);

			if (component.getParent() == null) {
				rendererPane.add(component);
			}
			rendererPane.paintComponent(g, component, table, cellRect.x,
					cellRect.y, cellRect.width, cellRect.height, true);
		}
	}
}
