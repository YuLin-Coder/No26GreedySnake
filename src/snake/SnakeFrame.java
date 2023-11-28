package snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SnakeFrame extends JFrame implements ActionListener{
	final SnakePanel p=new SnakePanel(this);		
	JMenuBar menubar=new JMenuBar();
	JMenu  a= new JMenu("�ļ�");
	JMenuItem newgameitem=new JMenuItem("��ʼ");
	JMenuItem stopitem=new JMenuItem("��ͣ");
	JMenuItem runitem=new JMenuItem("����");
	JMenuItem exititem=new JMenuItem("�˳�");
    //"����"�˵�
	JMenu optionMenu=new JMenu("����");

	 //�ȼ�ѡ��
      ButtonGroup groupDegree = new ButtonGroup();
      JRadioButtonMenuItem oneItem= new JRadioButtonMenuItem("����");      
      JRadioButtonMenuItem twoItem= new JRadioButtonMenuItem("�м�");
      JRadioButtonMenuItem threeItem= new JRadioButtonMenuItem("�߼�");      	
      
      JMenu degreeMenu=new JMenu("�ȼ�");      
      JMenu helpMenu=new JMenu("����");
	  JMenuItem helpitem=new JMenuItem("����ָ��");
      
	  final JCheckBoxMenuItem showGridItem = new JCheckBoxMenuItem("��ʾ����");
	  JLabel scorelabel;
	  public JTextField scoreField;
	  private long speedtime=200;
	  private String helpstr = "��Ϸ˵����\n1 ��������������ƶ��ķ���."+
      "\n2 �������˵�'�ļ�->��ʼ'��ʼ��Ϸ."+
      "\n3 �������˵�'�ļ�->��ͣ'���ߵ������̿ո����ͣ��Ϸ."+
      "\n4 �������˵�'�ļ�->����'������Ϸ."+
      "\n5 �������˵�'����->�ȼ�'���������Ѷȵȼ�."+
      "\n6 �������˵�'����->��ʾ����'���������Ƿ���ʾ����."+
      "\n7 ����ɫΪʳ��,��һ����10��ͬʱ����ӳ�."+
      "\n8 ���߲����Գ���������ཻ�����������Ϸ.";
      
	SnakeFrame()
	{
		      setJMenuBar(menubar);						      
			  menubar.add(a);	
			  a.add(newgameitem);
		      a.add(stopitem);
		      a.add(runitem);
		      a.add(exititem);
			  oneItem.setSelected(true);
		      groupDegree.add(oneItem);
		      groupDegree.add(twoItem);
		      groupDegree.add(threeItem);		      
		      degreeMenu.add(oneItem);	
		      degreeMenu.add(twoItem);
		      degreeMenu.add(threeItem); 
		      optionMenu.add(degreeMenu);			 
		      // ���ѡ��
		      showGridItem.setSelected(true); 			
			
		      optionMenu.add(showGridItem);			
			  menubar.add(optionMenu);			
		      helpMenu.add(helpitem);
		      menubar.add(helpMenu);
			
			Container contentpane=getContentPane(); 
			contentpane.setLayout(new FlowLayout());
			contentpane.add(p);
					
			scorelabel=new JLabel("��   ��: ");
			scoreField=new JTextField("0",15);
			scoreField.setEnabled(false);
			scoreField.setHorizontalAlignment(0);
			
			JPanel toolPanel=new JPanel();
			toolPanel.add(scorelabel);
			toolPanel.add(scoreField);
			contentpane.add(toolPanel);
			
			oneItem.addActionListener(this);
		    twoItem.addActionListener(this);
		    threeItem.addActionListener(this);
		    newgameitem.addActionListener(this);
		    stopitem.addActionListener(this);
		    runitem.addActionListener(this);
		    exititem.addActionListener(this);
		    helpitem.addActionListener(this);
		    showGridItem.addActionListener(this);
			
	}
	
	public void actionPerformed(ActionEvent e){ 
		 try{
			 if(e.getSource()==helpitem){
				 JOptionPane.showConfirmDialog(p,helpstr,"����˵��",JOptionPane.PLAIN_MESSAGE);
			 }
			 else if(e.getSource()==exititem)System.exit(0);
			 else if(e.getSource()==newgameitem)p.newGame(speedtime);
			 else if(e.getSource()==stopitem)p.stopGame();
			 else if(e.getSource()==runitem)p.returnGame();
			 else if(e.getSource()==showGridItem){
					if(!showGridItem.isSelected()){
						p.setBackground(Color.lightGray);
					}else{
						p.setBackground(Color.darkGray);
					}
						
				}
			 else if(e.getSource()==oneItem) speedtime=200;
			 else if(e.getSource()==twoItem) speedtime=100;
			 else if(e.getSource()==threeItem) speedtime=50;

		 }catch(Exception ee){ee.printStackTrace();}
	 } 
}

