package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.undo.UndoManager;

public class SystemGUI {
	private JComponent ui = null;
	private String choice = null;
	Controller controller = null; //controller pattern

    public SystemGUI() {
    	controller = new Controller(); //controller pattern
        run();
    }

    public void run() {
        if (ui!=null) return;

        /* --------
         * start establish gui layout
         * --------*/
        
        ui = new JPanel(new BorderLayout(4,4));
        //ui.setBorder(new TitledBorder("BorderLayout"));

        DrawingPanel draw_panel = new DrawingPanel();
        draw_panel.setBorder(new TitledBorder("Draw Panel"));
        ui.add(draw_panel);

        JPanel button_panel = new JPanel(new GridBagLayout());
        button_panel.setBorder(new TitledBorder("Buttons"));
        ui.add(button_panel, BorderLayout.LINE_START);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weighty = 1.0;

        JPanel buttonsCentered = new JPanel(new GridLayout(0, 1, 10, 10));
        button_panel.add(buttonsCentered, gbc);
        
        /* --------
         * end establish gui layout
         * --------*/
        
        /* --------
         * start setup button listeners
         * --------*/
        
        JButton line = new JButton("Line");
        buttonsCentered.add(line);
        line.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		choice = "line";
        		//controller.changeState();
        		//draw_panel.setChoice("line");
            	System.out.println("clicked line");
        	}
        });
        
        JButton box = new JButton("Box");
        buttonsCentered.add(box);
        box.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		choice = "box";
        		//controller.changeState(draw_panel);
        		//draw_panel.setChoice("box");
            	System.out.println("clicked box");
        	}
        });
        
        JButton circle = new JButton("Circle");
        buttonsCentered.add(circle);
        circle.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		choice = "circle";
        		//controller.changeState(draw_panel);
        		//draw_panel.setChoice("circle");
            	System.out.println("clicked circle");
        	}
        });
        
        JButton undo = new JButton("Undo");
        buttonsCentered.add(undo);
        undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked undo");
				controller.undoCommand(draw_panel);
			}
		});
        
        JButton redo = new JButton("Redo");
        buttonsCentered.add(redo);
        redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked redo");
				controller.redoCommand(draw_panel);
			}
		});
        
        /* --------
         * end setup button listeners
         * --------*/
        
        /* --------
         * start setup drawing panel listener
         * --------*/
        
        draw_panel.addMouseListener(new MouseAdapter() {
        	int click_count = 0;
        	Line temp_line = null;
        	
        	public void mouseClicked(MouseEvent e) {
            	if(choice!=null){
        	    	if(choice.compareTo("circle") == 0){
        	    		click_count = 0;
        	    		controller.drawCircle(draw_panel, e); //controller pattern
        	    		controller.UndoStackPush(choice);
        	    	}else if(choice.compareTo("line") == 0){
        	    		System.out.println("choice is line");
        				System.out.println("Coordinates (" + e.getX() + ", " + e.getY() + ")");
        				System.out.println("Click #: " + (click_count+1));
        	    		if(click_count == 0) {
        	    			temp_line = new Line(e.getX(), e.getY());
        	    			click_count+=1;
        	    		}else if (click_count == 1){
        	    			controller.drawLine(draw_panel, e, temp_line); //controller pattern
        	    			controller.UndoStackPush(choice);
        	    			click_count = 0;
        	    		}
        	    	}else if(choice.compareTo("box") == 0){
        	    		click_count = 0;
        	    		controller.drawBox(draw_panel, e); //controller pattern
        	    		controller.UndoStackPush(choice);
        	    	}
            	}
            }
        });
        
        /* --------
         * end setup drawing panel listener
         * --------*/
    }

    public JComponent getUI() {
        return ui;
    }

    public static void main(String[] args) {
    	Runnable r = new Runnable() {
    		@Override
    		public void run() {
    			try {
    				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    			} catch (Exception useDefault) {
    			}
    			SystemGUI gui = new SystemGUI();

    			JFrame frame = new JFrame(gui.getClass().getSimpleName());
    			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    			frame.setLocationByPlatform(true);

    			frame.setContentPane(gui.getUI());
    			frame.pack();
    			frame.setMinimumSize(frame.getSize());

    			frame.setVisible(true);
    		}
    	};
    	SwingUtilities.invokeLater(r);
    }
}

