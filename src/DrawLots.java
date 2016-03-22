

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.io.*;

import java.util.Random;

public class DrawLots extends JFrame

{

	final int NUMBER = 18;

	private JButton jbtnStart;

	private JButton jbtnStop;

	private JButton jbtnAgain;

	private JLabel  jlbTask,jlbID;

	private int [] a=new int [NUMBER];

	private Random rd=new Random();

	private MyThread t=new MyThread();

	private boolean start=false;

	private boolean startonce=false;

	private File file=new File("Result.txt");

	private String result="";

	public DrawLots()

	{

		jlbTask=new JLabel("Task"); 

		jlbTask.setFont(new   java.awt.Font("Dialog",   1,   30));

		jlbID=new JLabel("1");

		jlbID.setFont(new   java.awt.Font("Dialog",   1,   30));

		jbtnStart=new JButton("Start");

		jbtnStart.setFont(new   java.awt.Font("Dialog",   1,   30));

		jbtnStop=new JButton("Stop");

		jbtnStop.setFont(new   java.awt.Font("Dialog",   1,   30));

		jbtnAgain=new JButton("New Round");

		jbtnAgain.setFont(new   java.awt.Font("Dialog",   1,   30));



		JPanel panel=new JPanel();

		panel.setLayout(null);

		jlbTask.setBounds(100, 50, 200, 60);

		jbtnStart.setBounds(60,150,160,60);

		jlbID.setBounds(360, 50, 200, 60);

		jbtnStop.setBounds(300, 150, 160, 60);

		jbtnAgain.setBounds(150,270,260,60);

		panel.add(jlbTask);

		panel.add(jlbID);

		panel.add(jbtnStart);

		panel.add(jbtnStop);

		panel.add(jbtnAgain);

		this.add(panel);

		this.setSize(540, 440);
		
		this.setResizable(false);
		
		this.setLocation(400, 300);

		this.setVisible(true);

		this.setTitle("DrawLots");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		for (int i=0;i<a.length;i++){

			a[i]=1;

		}

		jbtnStart.addActionListener(new ActionListener(){

			    public void actionPerformed(ActionEvent event)

			    {

			    	if(!startonce){

			    		startonce=true;

			    		start=true;

			    		t.start();

			    	}

			    	else if(!start){

			    		start=true;

			    		t.resume();

			    	}

			 

			    	

			     }});

		jbtnStop.addActionListener(new ActionListener(){

		    public void actionPerformed(ActionEvent event)

		    {

		    	if(start){

		    		

					t.suspend();

					

		    		int index=rd.nextInt(NUMBER);

		    		boolean boo=true;

		    		while(boo){

			    	if (a[index]!=0){

			    		jlbID.setText(""+(index+1));

			    		result=result+(index+1)+"\r\n";

			    		a[index]=0;

			    		boo=false;

			    	}else{

			    		index=rd.nextInt(NUMBER);

			    	}

		    		}

		  

		    		

		    		boolean re=false;

		    		for (int i=0;i<a.length;i++){

		    			if (a[i]==1){

		    				re=true;

		    				break;

		    			}

		    		}

		    		if (!re){

		    			for (int i=0;i<a.length;i++){

			    			a[i]=1;

			    			}

			    		}

		    		

		    		try {

						FileOperation.writeTxtFile(result, file);

					} catch (Exception e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}

		    		start=false;

		    	}

		    	

		    	

		    	

		     }});

		jbtnAgain.addActionListener(new ActionListener(){

			    public void actionPerformed(ActionEvent event)

			    {

				//if(!start){

					t.suspend();

					jlbID.setText(""+1);		

					for (int i=0;i<a.length;i++){

			    			a[i]=1;

			    		}

					start=false;

					result = result + "stop" + "\r\n";

				//}	

					try {

						FileOperation.writeTxtFile(result, file);

					} catch (Exception e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}		 

			    	

			     }});



		

		try {

			FileOperation.createFile(file);

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		

	}

	

	

	

	class MyThread extends Thread{

        @Override

        public void run() {

        	while(true){

        	int index=rd.nextInt(NUMBER);

	    	if (a[index]!=0){

	    		jlbID.setText(""+(index+1));

	    		try {

                    

                    Thread.sleep(100);

                } catch (InterruptedException e) {

                    // TODO: handle exception

                }

	    	}

              

                

                

        }

        }

        

    }



	

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		new DrawLots();



	}



}



class FileOperation {  

	   

	 /** 

	  * 

	  * @param fileName 

	  * @return 

	  */  

	 public static boolean createFile(File fileName)throws Exception{  

	  boolean flag=false;  

	  try{  

	   if(!fileName.exists()){  

	    fileName.createNewFile();  

	    flag=true;  

	   }  

	  }catch(Exception e){  

	   e.printStackTrace();  

	  }  

	  return true;  

	 }   

	   

	

	   

	   

	 public static boolean writeTxtFile(String content,File  fileName)throws Exception{  

	  RandomAccessFile mm=null;  

	  boolean flag=false;  

	  FileOutputStream o=null;  

	  try {  

	   o = new FileOutputStream(fileName);  

	      o.write(content.getBytes());

	      o.flush();

	      o.close();  

	//   mm=new RandomAccessFile(fileName,"rw");  

	//   mm.writeBytes(content);  

	   flag=true;  

	  } catch (Exception e) {  

	   // TODO: handle exception  

	   e.printStackTrace();  

	  }finally{  

	   if(mm!=null){  

	    mm.close();  

	   }  

	  }  

	  return flag;  

	 }  

}

	  

	  