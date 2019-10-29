package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	UndoManager manager = new UndoManager();

    public SystemGUI() {
        run();
    }

    public void run() {
        if (ui!=null) return;

        ui = new JPanel(new BorderLayout(4,4));
        //ui.setBorder(new TitledBorder("BorderLayout"));

        DrawingPanel draw = new DrawingPanel();
        draw.setBorder(new TitledBorder("Draw Panel"));
        ui.add(draw);

        JPanel button_panel = new JPanel(new GridBagLayout());
        button_panel.setBorder(new TitledBorder("Buttons"));
        ui.add(button_panel, BorderLayout.LINE_START);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weighty = 1.0;

        JPanel buttonsCentered = new JPanel(new GridLayout(0, 1, 10, 10));
        button_panel.add(buttonsCentered, gbc);
        
        JButton line = new JButton("Line");
        buttonsCentered.add(line);
        line.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		draw.setChoice("line");
            	System.out.println("clicked line");
        	}
        });
        
        JButton box = new JButton("Box");
        buttonsCentered.add(box);
        box.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		draw.setChoice("box");
            	System.out.println("clicked box");
        	}
        });
        
        JButton circle = new JButton("Circle");
        buttonsCentered.add(circle);
        circle.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		draw.setChoice("circle");
            	System.out.println("clicked circle");
        	}
        });
        
        JButton undo = new JButton("Undo");
        buttonsCentered.add(undo);
        undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked undo");
			}
		});
        
        JButton redo = new JButton("Redo");
        buttonsCentered.add(redo);
        redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked redo");
			}
		});
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

