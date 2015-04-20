package com.tictactoe.component.board;

public class CellPosition {

	private int yAxis;
	private int xAxis;
	
	public CellPosition(int yAxis, int xAxis) {
		this.yAxis = yAxis;
		this.xAxis = xAxis;
	}
	
	public int getyAxis() {
		return yAxis;
	}
	
	public int getxAxis() {
		return xAxis;
	}
	
	public boolean fallsOnAscendingDiaganol() {
		if (yAxis == 0 && xAxis == 2) { // Lower left
			return true;
		} else if (yAxis == 1 && xAxis == 1) { // Middle square
			return true;
		} else if (yAxis == 2 && xAxis == 0) { // Upper right
			return true;
		}
		return false;
	}
	
	public boolean fallsOnDescendingDiaganol() {
		if (yAxis == 0 && xAxis == 0) { // Upper left
			return true;
		} else if (yAxis == 1 && xAxis == 1) { // Middle square
			return true;
		} else if (yAxis == 2 && xAxis == 2) { // Lower right
			return true;
		}
		return false;
	}
	
	public String getStyleClass() {
		String xValue = "";
		String yValue = "";
		
		if (xAxis == 0) {
			xValue = "top";
		} else if (xAxis == 1) {
			xValue = "middle";
		} else if (xAxis == 2) {
			xValue = "bottom";
		}
		
		if (yAxis == 0) {
			yValue = "Left";
		} else if (yAxis == 1) {
			yValue = "Middle";
		} else if (yAxis == 2) {
			yValue = "Right";
		}
		
		return xValue + yValue;
	}
}
