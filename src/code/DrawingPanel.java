package code;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import code.Controller;

public class DrawingPanel extends JPanel implements MouseListener{

	List<Circle> circles = new ArrayList<>();
    List<Line> lines = new ArrayList<>();
    List<Box> boxes = new ArrayList<>();
    
	public DrawingPanel() {
		this.addMouseListener(this);
	}
	
    
    //Controller control = new Controller();
    
    Line temp_line = null;
    String choice = null;
    int click_count = 0;
    
    
    
    public void setChoice(String choice) {
    	this.choice = choice;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circle circle : circles) {
            circle.draw(g);
        }
        for (Box box : boxes) {
        	box.draw(g);
        }
        for (Line line : lines) {
        	line.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	if(choice!=null){
	    	if(choice.compareTo("circle") == 0){
	    		drawCircle(e.getX(), e.getY());
	    		
	    	}else if(choice.compareTo("line") == 0){
	    		click_count+=1;
	    		//Line line = null;
	    		System.out.println("choice is line");
	    		System.out.println("Coordinates (" + e.getX() + ", " + e.getY() + ")");
	    		System.out.println("Click #: " + click_count);
	    		if(click_count == 1) {
	    			temp_line = new Line(e.getX(), e.getY());
	    		}else if(click_count == 2) {
	    			temp_line.setX2(e.getX());
	    			temp_line.setY2(e.getY());
	    			addLine(temp_line);
	    			click_count=0;
	    		}
	    	}else if(choice.compareTo("box") == 0){
	    		click_count=0;
	    		System.out.println("choice is box");
	    		System.out.println("Coordinates (" + e.getX() + ", " + e.getY() + ")");
	    		Box box = new Box(e.getX(), e.getY(), 100, 100);
	    		addBox(box);
	    	}
    	}
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
    
    public String drawCircle(int x, int y) {
		String msg = "";
		click_count=0;
		System.out.println("choice is circle");
		System.out.println("Coordinates (" + x + ", " + y + ")");
		Circle circle = new Circle(x, y, 100, 100);
        addCircle(circle);
		return msg;
	}
	
	private void addCircle(Circle circle) {
        circles.add(circle);
        repaint(); //must implement jpanel
    }
    
    private void addLine(Line line) {
        lines.add(line);
        repaint();
    }
    
    private void addBox(Box box) {
        boxes.add(box);
        repaint();
    }
}
