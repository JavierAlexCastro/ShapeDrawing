package code;

public class Redo {
	
	public Redo(){
		
	}
	
	public Line getLine(Undo undo){
		return undo.old_lines.get(undo.old_lines.size()-1);
	}
	
	public Circle getCircle(Undo undo){
		return undo.old_circles.get(undo.old_circles.size()-1);
	}
	
	public Box getBox(Undo undo){
		return undo.old_boxes.get(undo.old_boxes.size()-1);
	}

}
