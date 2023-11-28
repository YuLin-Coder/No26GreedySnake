package snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SnakeFrame extends JFrame implements ActionListener{
	final SnakePanel p=new SnakePanel(this);		
	JMenuBar menubar=new JMenuBar();
	JMenu  a= new JMenu("文件");
	JMenuItem newgameitem=new JMenuItem("开始");
	JMenuItem stopitem=new JMenuItem("暂停");
	JMenuItem runitem=new JMenuItem("继续");
	JMenuItem exititem=new JMenuItem("退出");
    //"设置"菜单
	JMenu optionMenu=new JMenu("设置");

	 //等级选项
      ButtonGroup groupDegree = new ButtonGroup();
      JRadioButtonMenuItem oneItem= new JRadioButtonMenuItem("初级");      
      JRadioButtonMenuItem twoItem= new JRadioButtonMenuItem("中级");
      JRadioButtonMenuItem threeItem= new JRadioButtonMenuItem("高级");      	
      
      JMenu degreeMenu=new JMenu("等级");      
      JMenu helpMenu=new JMenu("帮助");
	  JMenuItem helpitem=new JMenuItem("操作指南");
      
	  final JCheckBoxMenuItem showGridItem = new JCheckBoxMenuItem("显示网格");
	  JLabel scorelabel;
	  public JTextField scoreField;
	  private long speedtime=200;
	  private String helpstr = "游戏说明：\n1 ：方向键控制蛇移动的方向."+
      "\n2 ：单击菜单'文件->开始'开始游戏."+
      "\n3 ：单击菜单'文件->暂停'或者单击键盘空格键暂停游戏."+
      "\n4 ：单击菜单'文件->继续'继续游戏."+
      "\n5 ：单击菜单'设置->等级'可以设置难度等级."+
      "\n6 ：单击菜单'设置->显示网格'可以设置是否显示网格."+
      "\n7 ：红色为食物,吃一个得10分同时蛇身加长."+
      "\n8 ：蛇不可以出界或自身相交，否则结束游戏.";
      
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
		      // 风格选项
		      showGridItem.setSelected(true); 			
			
		      optionMenu.add(showGridItem);			
			  menubar.add(optionMenu);			
		      helpMenu.add(helpitem);
		      menubar.add(helpMenu);
			
			Container contentpane=getContentPane(); 
			contentpane.setLayout(new FlowLayout());
			contentpane.add(p);
					
			scorelabel=new JLabel("得   分: ");
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
				 JOptionPane.showConfirmDialog(p,helpstr,"操纵说明",JOptionPane.PLAIN_MESSAGE);
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

