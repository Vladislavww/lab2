package bsu.rfe.java.group6.lab2.Yatskou.varC;

import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Image; 
import java.awt.Toolkit; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.BorderFactory; 
import javax.swing.Box; 
import javax.swing.ButtonGroup; 
import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JRadioButton; 
import javax.swing.JTextField;

public class MainFrame extends JFrame{
	private static final int WIDTH = 600;  
	private static final int HEIGHT = 600;
	private ButtonGroup radioButtons = new ButtonGroup();
	private Box hboxFormulaType = Box.createHorizontalBox();
	private JTextField textFieldX;  
	private JTextField textFieldY; 
	private JTextField textFieldZ; 
	private ImageIcon icon1 = new ImageIcon("f1.png");
	private ImageIcon icon2 = new ImageIcon("f2.png");
	private JLabel figure;
	private JTextField textFieldResult; 
	private int formulaId = 1;
	private double mem1;
	private double mem2;
	private double mem3;
	private int current_mem = 1;
	private JTextField textField_mem1;  
	private JTextField textField_mem2; 
	private JTextField textField_mem3; 
	private ButtonGroup radioButtons_mem = new ButtonGroup();
	private Box r_buttons_memory_box = Box.createHorizontalBox();
	private void addRadioButton(String buttonName, final int formulaId){   
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				MainFrame.this.formulaId = formulaId;
				if(formulaId == 1){
					figure.setIcon(icon1);
				}
				else{
					figure.setIcon(icon2);
				}
			}
		});
		radioButtons.add(button);   
		hboxFormulaType.add(button); 
	}
	private void addMemoryRadioButton(String buttonName, final int button_num){
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				MainFrame.this.current_mem = button_num;
			}
		});
		radioButtons_mem.add(button);
		r_buttons_memory_box.add(button);
	}
	public void drawImage(String url){
		Image icon = new ImageIcon(url).getImage();
		Graphics pict = getGraphics();
		pict.drawImage(icon, 0, 20, WIDTH, 100, null);
	}
	public Double calculate1(Double x, Double y, Double z) {   
		return Math.sin(Math.log(y)+Math.sin(Math.PI*y*y)*Math.pow(x*x+Math.sin(z)+Math.exp(Math.cos(z)),0.25));  
	} 
	public Double calculate2(Double x, Double y, Double z) {   
		return Math.pow(Math.cos(Math.pow(Math.E, x))+Math.log(Math.pow(1+y, 2))+Math.sqrt(Math.exp(Math.cos(x))+Math.pow(Math.sin(Math.PI*z), 2))+Math.sqrt(1/x)+Math.cos(y*y), Math.sin(z));  
	}  
	
	public void update_mem_fields(){
		Double m1 = mem1;
		Double m2 = mem2;
		Double m3 = mem3;
		textField_mem1.setText(m1.toString());
		textField_mem2.setText(m2.toString());
		textField_mem3.setText(m3.toString());
	}
	public MainFrame(){
		super("Вычисление");
		setSize(WIDTH, HEIGHT);  
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
		///
		hboxFormulaType.add(Box.createHorizontalGlue());
		addRadioButton("Формула 1", 1);
		addRadioButton("Формула 2", 2);
		radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
		hboxFormulaType.add(Box.createHorizontalGlue()); 
		hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW)); 
		///////
		JLabel labelForX = new JLabel("X:");   
		textFieldX = new JTextField("0", 10);   
		textFieldX.setMaximumSize(textFieldX.getPreferredSize());   
		JLabel labelForY = new JLabel("Y:");  
		textFieldY = new JTextField("0", 10);  
		textFieldY.setMaximumSize(textFieldY.getPreferredSize());
		JLabel labelForZ = new JLabel("Z:");   
		textFieldZ = new JTextField("0", 10);   
		textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
		Box hboxVariables = Box.createHorizontalBox(); 
		hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));   
		hboxVariables.add(Box.createHorizontalGlue());  
		hboxVariables.add(labelForX);  
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldX); 
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(labelForY);  
		hboxVariables.add(Box.createHorizontalStrut(10)); 
		hboxVariables.add(textFieldY);
		hboxVariables.add(Box.createHorizontalStrut(10)); 
		hboxVariables.add(labelForZ);  
		hboxVariables.add(Box.createHorizontalStrut(10)); 
		hboxVariables.add(textFieldZ);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(Box.createHorizontalGlue()); 
		//////
		JLabel labelForResult = new JLabel("Результат:");   
		textFieldResult = new JTextField("0", 10);
		textFieldResult.setMaximumSize( textFieldResult.getPreferredSize());  
		Box hboxResult = Box.createHorizontalBox(); 
		hboxResult.add(Box.createHorizontalGlue()); 
		hboxResult.add(labelForResult); 
		hboxResult.add(Box.createHorizontalStrut(10));
		hboxResult.add(textFieldResult); 
		hboxResult.add(Box.createHorizontalGlue()); 
		hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE)); 
		/////
		JButton buttonCalc = new JButton("Вычислить");
		buttonCalc.addActionListener(new ActionListener() {    
			public void actionPerformed(ActionEvent ev) {   
				try { 
					 Double x = Double.parseDouble(textFieldX.getText());     
					 Double y = Double.parseDouble(textFieldY.getText());
					 Double z = Double.parseDouble(textFieldZ.getText());
					 Double result;    
					 if (formulaId==1){ 
						 result = calculate1(x, y, z);
					 }
					 else{    
						 result = calculate2(x, y, z);
					 }
					 textFieldResult.setText(result.toString()); 
					 } catch (NumberFormatException ex){ 
						 JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);    
					 }  
				 } 
		});
		JButton buttonReset = new JButton("Очистить поля");  
		buttonReset.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ev) {  
				textFieldX.setText("0"); 
				textFieldY.setText("0"); 
				textFieldZ.setText("0"); 
				textFieldResult.setText("0"); 
		   }
		});
		Box hboxButtons = Box.createHorizontalBox();  
		hboxButtons.add(Box.createHorizontalGlue()); 
		hboxButtons.add(buttonCalc); 
		hboxButtons.add(Box.createHorizontalStrut(30)); 
		hboxButtons.add(buttonReset);
		hboxButtons.add(Box.createHorizontalGlue()); 
		hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN)); 
		///
		Box hboxmemory = Box.createHorizontalBox();
		JButton MC = new JButton("MC");
		MC.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent ev){
				if(current_mem == 1){
					mem1 = 0;
				}
				if(current_mem == 2){
					mem2 = 0;
				}
				if(current_mem == 3){
					mem3 = 0;
				}
				update_mem_fields();
			}
		});
		JButton M = new JButton("M+");
		M.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent ev){
				if(current_mem == 1){
					mem1 += Double.parseDouble(textFieldResult.getText());
					
				}
				if(current_mem == 2){
					mem2 += Double.parseDouble(textFieldResult.getText());
				}
				if(current_mem == 3){
					mem3 += Double.parseDouble(textFieldResult.getText());
				}
				update_mem_fields();
			}
		});
		hboxmemory.add(MC);
		hboxmemory.add(M);
		//
		figure = new JLabel(icon1);
		Box hboxfigure = Box.createHorizontalBox();
		hboxfigure.add(Box.createHorizontalGlue());
		hboxfigure.add(figure);
		hboxfigure.add(Box.createHorizontalGlue());
		//
		Box memory_box = Box.createHorizontalBox();
		JLabel memory_label = new JLabel("Memory:");
		textField_mem1 = new JTextField("0", 10);
		textField_mem2 = new JTextField("0", 10);
		textField_mem3 = new JTextField("0", 10);
		textField_mem1.setMaximumSize(textField_mem1.getPreferredSize());  
		textField_mem2.setMaximumSize(textField_mem2.getPreferredSize());  
		textField_mem3.setMaximumSize(textField_mem3.getPreferredSize());  
		memory_box.add(Box.createHorizontalGlue());
		memory_box.add(memory_label);
		memory_box.add(textField_mem1);
		memory_box.add(textField_mem2);
		memory_box.add(textField_mem3);
		memory_box.add(Box.createHorizontalGlue());
		//
		addMemoryRadioButton("M1", 1);
		addMemoryRadioButton("M2", 2);
		addMemoryRadioButton("M3", 3);
		r_buttons_memory_box.add(Box.createHorizontalGlue());
		radioButtons_mem.setSelected(radioButtons_mem.getElements().nextElement().getModel(), true);
		r_buttons_memory_box.add(Box.createHorizontalGlue()); 
		//
		Box contentBox = Box.createVerticalBox();
		contentBox.add(hboxfigure);
		contentBox.add(Box.createVerticalGlue());
		contentBox.add(hboxFormulaType);
		contentBox.add(hboxVariables);
		contentBox.add(hboxResult);
		contentBox.add(hboxButtons);
		contentBox.add(memory_box);
		contentBox.add(hboxmemory);
		contentBox.add(r_buttons_memory_box);
		contentBox.add(Box.createVerticalGlue());
		////
		getContentPane().add(contentBox, BorderLayout.CENTER);
		
		////
	}
	private static String getResource(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args){
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		frame.setVisible(true);
		//System.out.println("finish");

	}

}////////
