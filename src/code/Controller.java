package code;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Controller {
	Stack<String> undo_stack = null;
	Stack<String> redo_stack = null;
    
	public Controller() {
		undo_stack = new Stack<>();
		redo_stack = new Stack<>();
	}
	
	public void drawCircle(DrawingPanel draw_panel, MouseEvent e){
		System.out.println("choice is circle");
		System.out.println("Coordinates (" + e.getX() + ", " + e.getY() + ")");
		Circle circle = new Circle(e.getX(), e.getY(), 100, 100);
		draw_panel.addCircle(circle); //expert pattern
		redo_stack.clear();
	}
	
	public void drawLine(DrawingPanel draw_panel, MouseEvent e, Line temp_line) {
		temp_line.setX2(e.getX()); //expert pattern
		temp_line.setY2(e.getY()); //expert pattern
		draw_panel.addLine(temp_line); //expert pattern
		redo_stack.clear();
	}
	
	public void drawBox(DrawingPanel draw_panel, MouseEvent e){
		System.out.println("choice is box");
		System.out.println("Coordinates (" + e.getX() + ", " + e.getY() + ")");
		Box box = new Box(e.getX(), e.getY(), 100, 100);
		draw_panel.addBox(box); //expert pattern
		redo_stack.clear();
	}
	
	public void handleUndoCommand(String command, DrawingPanel draw_panel){
		if(command.compareTo("line") == 0){
			draw_panel.undoLine();
		}else if(command.compareTo("circle") == 0) {
			draw_panel.undoCircle();
		}else if(command.compareTo("box") == 0){
			draw_panel.undoBox();
		}
	}
	
	public void handleRedoCommand(String command, DrawingPanel draw_panel) {
		if(command.compareTo("line") == 0){
			draw_panel.redoLine();
		}else if(command.compareTo("circle") == 0) {
			draw_panel.redoCircle();
		}else if(command.compareTo("box") == 0){
			draw_panel.redoBox();
		}
	}
		
	public void undoCommand(DrawingPanel draw_panel) {
		if(undo_stack.size()>0){
			handleUndoCommand(UndoStackPop(), draw_panel);
		}else {
			System.out.println("Nothing to Undo");
		}
	}
	
	public void redoCommand(DrawingPanel draw_panel) {
		if(redo_stack.size()>0){
			handleRedoCommand(RedoStackPop(), draw_panel);
		}else {
			System.out.println("Nothing to Redo");
		}
	}
	
	public void changeState(DrawingPanel draw_panel, Shape state) {
		draw_panel.setState(state);
	}
	
	public void UndoStackPush(String command) {
		undo_stack.push(command);
	}
	
	public void RedoStackPush(String command) {
		redo_stack.push(command);
	}
	
	public String UndoStackPop() {
		String command = undo_stack.pop();
		RedoStackPush(command);
		return command;
	}
	
	public String RedoStackPop() {
		String command = redo_stack.pop();
		UndoStackPush(command);
		return command;
	}
	
}
