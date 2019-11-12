package code;

import java.util.ArrayList;
import java.util.List;

public class Undo {

	List<Circle> old_circles = new ArrayList<>(); //composite pattern
    List<Line> old_lines = new ArrayList<>(); //composite pattern
    List<Box> old_boxes = new ArrayList<>(); //composite pattern
    
	public Undo(){
		
	}
	
	public void addLine(Line line){
		old_lines.add(line);
	}
	
	public void addCircle(Circle circle) {
		old_circles.add(circle);
	}
	
	public void addBox(Box box) {
		old_boxes.add(box);
	}
	
	public void removeLine() {
		old_lines.remove(old_lines.size()-1);
	}
	
	public void removeCircle() {
		old_circles.remove(old_circles.size()-1);
	}
	
	public void removeBox() {
		old_boxes.remove(old_boxes.size()-1);
	}
	
	public void clearOld(){
		old_lines.clear();
		old_circles.clear();
		old_boxes.clear();
	}
}
