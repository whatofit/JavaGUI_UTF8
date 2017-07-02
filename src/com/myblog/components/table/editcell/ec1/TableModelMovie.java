package com.myblog.components.table.editcell.ec1;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * 
 Created with IntelliJ IDEA.
 * 
 * User: Edison
 * 
 * Date: 13-8-16
 * 
 * Time: 下午10:46
 * 
 * A table model for movies.
 */
public class TableModelMovie extends DefaultTableModel {
	private static final int COL_NAME = 0;
	private static final int COL_PREMIERE = COL_NAME + 1;
	private static final int COL_DIRCTOR = COL_PREMIERE + 1;

	public TableModelMovie() {
		init();
		mockData();
	}

	private void init() {
		columnIdentifiers.add("Name");
		columnIdentifiers.add("Premiere");
		columnIdentifiers.add("Director");
	}

	/**
	 * 
	 make some movies.
	 */
	private void mockData() {
		List<Movie> list = new ArrayList<>();
		list.add(new Movie("The Shawshank Redemption", new Date(),
				"Frank Darabont"));
		list.add(new Movie("The Godfather", new Date(), " Francis Ford Coppola"));
		list.add(new Movie("Pulp Fiction", new Date(), "Quentin Tarantino"));
		list.add(new Movie("The Dark Knight", new Date(), "Frank Darabont"));
		fillTableData(list);
	}

	private void fillTableData(List<Movie> movies) {
		dataVector.clear();
		for (Movie movie : movies) {
			Vector rowVector = new Vector();
			rowVector.add(movie);
			dataVector.add(rowVector);
		}
		fireTableDataChanged();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Object ret = "";
		if (row > dataVector.size()) {
			return ret;
		}
		// Get one row data.
		Vector rowData = (Vector) dataVector.elementAt(row);
		Movie movie = (Movie) rowData.elementAt(0);
		switch (column) {
		case COL_NAME:
			ret = movie.getName();
			break;
		case COL_PREMIERE:
			ret = movie.getPremiere().toString();
			break;
		case COL_DIRCTOR:
			ret = movie.getDirector();
			break;
		default:
			break;
		}
		return ret;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (row > dataVector.size()) {
			return;
		}
		// Get one row data.
		Vector rowData = (Vector) dataVector.elementAt(row);
		Movie movie = (Movie) rowData.elementAt(0);
		switch (column) {
		case COL_NAME:
			movie.setName(aValue.toString());
			break;
		case COL_DIRCTOR:
			movie.setDirector(aValue.toString());
			break;
		default:
			break;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column == COL_NAME || column == COL_DIRCTOR;
	}
}