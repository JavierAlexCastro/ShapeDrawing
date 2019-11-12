package code;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import code.Controller;

public class DrawingPanel extends JPanel implements MouseListener{

	List<Circle> circles = new ArrayList<>(); //composite pattern
    List<Line> lines = new ArrayList<>(); //composite pattern
    List<Box> boxes = new ArrayList<>(); //composite pattern
    Undo undo = null;
    Redo redo = null;
    
    
    Shape state;
   
    
	public DrawingPanel() {
		//this.addMouseListener(this);
		//state = null;
		undo = new Undo();
		redo = new Redo();
	}
	
	/* ---------------------------------
	 * Handling Command pattern (undo)
	 * ---------------------------------
	 */
	
	public void undoLine() {
		undo.addLine(lines.get(lines.size()-1)); //add last item before removing
		lines.remove(lines.size()-1); //remove last item
		repaint(); //refresh drawing panel
	}
	
	public void undoCircle() {
		undo.addCircle(circles.get(circles.size()-1)); //add last item before removing
		circles.remove(circles.size()-1);
		repaint();
	}
	
	public void undoBox() {
		undo.addBox(boxes.get(boxes.size()-1)); //add last item before removing
		boxes.remove(boxes.size()-1);
		repaint();
	}
	
	/* ---------------------------------
	 * Handling Command pattern (redo)
	 * ---------------------------------
	 */
	
	public void redoLine() {
		lines.add(redo.getLine(undo)); //add last item before removing
		undo.removeLine(); //remove last item
		repaint(); //refresh drawing panel
	}
	
	public void redoCircle() {
		circles.add(redo.getCircle(undo)); //add last item before removing
		undo.removeCircle();
		repaint();
	}
	
	public void redoBox() {
		boxes.add(redo.getBox(undo)); //add last item before removing
		undo.removeBox();
		repaint();
	}
	
	/* ---------------------------------
	 * Handling Composite pattern
	 * ---------------------------------
	 */
	
	public void addCircle(Circle circle) {
        circles.add(circle); //composite pattern
        repaint();
        undo.clearOld();
    }
    
    public void addLine(Line line) {
        lines.add(line);//composite pattern
        repaint();
        undo.clearOld();
    }
    
    public void addBox(Box box) {
        boxes.add(box);//composite pattern
        repaint();
        undo.clearOld();
    }
    
    /* ---------------------------------
	 * 
	 * ---------------------------------
	 */
    
    public void setState(Shape state) {
		this.state = state;
	}
	
	public Shape getState(){
		return state;
	}
	
	

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Iterator<Circle> circle_iterator = circles.iterator(); //iterator pattern
        Iterator<Line> line_iterator = lines.iterator(); //iterator pattern
        Iterator<Box> box_iterator = boxes.iterator(); //iterator pattern
        
        while (circle_iterator.hasNext()) { //iterator pattern
        	circle_iterator.next().draw(g);
        }
        while (line_iterator.hasNext()) { //iterator pattern
        	line_iterator.next().draw(g);
        }
        while (box_iterator.hasNext()) { //iterator pattern
        	box_iterator.next().draw(g);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
    
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    
}
