package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.ColorsList;
public class Panel extends JPanel{


	Double cx=0.0;     // X coordinate of center of mandelbrot fractal
	Double cy=0.0;     // Y coordinate of center of mandelbrot fractal
	
	
	Double scale=0.004; // zoom faktor vanila
	//Double scale=0.02;
	
	Double limit; //divergence check factor - number that can not have a value greater then the moduo of our complex number
	Integer lp;  // convergence check factor - number of iterations that were possible to make for a given point

	Double a1,a2,b1,b2; // Z 

	int xIscitan =0;  // x dimension of the pannel on wich the fractal will be dravn
	int yIscitan =0;  // y dimension of the pannel on wich the fractal will be dravn
	HashMap<Integer,Color> boje = new HashMap<Integer,Color>(); 

	HashMap<Integer,Color> boje2 = new HashMap<Integer,Color>(); // Hash mapa of colours generated at the end of the constructor.
	
	//Double faktorZoom=0.9;
	Double faktorZoom=0.5;             // Faktor whit which we change scale
	Integer x,y;  // pixel that is drawn

	Double ax,ay; // The actual position of (x,y) in relation to the Mandelbrot set - cx, cy.

	public void paint(Graphics g){

		limit=2.0;  // max value for the moduo of our complex number
		a2=0.0;		//
		a2=0.0;		//

		int pomocIter =0;	
		// 320 * 200
		xIscitan = (int) this.getSize().getWidth();    
		yIscitan = (int) this.getSize().getHeight();   

		for( x = -xIscitan/2; x <= xIscitan/2;x++){            //Iterating trough x coordinate of all pixels

			for( y = -yIscitan/2; y <= yIscitan/2;y++){        //Iterating trough y coordinate of all pixels



				ax = cx + x*scale;  
				ay = cy + y*scale;     
		

				a1 = ax ;       
				b1 = ay;	
			
				lp =0;

				do{

					lp++;

					
					a2 = a1*a1 - b1*b1 +ax;
					b2 =(2*a1*b1) +ay;
					
					a1 = a2;
					b1 = b2;
				
				}while((lp <= 255 && (Math.sqrt((a1*a1)+(b1*b1)))<=limit));
				
				if(lp>=254){ g.setColor(Color.black);}else{
					
				g.setColor(boje2.get(lp));
				}
				
				g.drawLine(x+xIscitan/2, y+yIscitan/2, x+xIscitan/2, y+yIscitan/2);

			}

		}

	}

	public Panel() {
		super();
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {


			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(arg0)) {
					scale *= faktorZoom;                                   //decreasing scale = zoom out
					System.out.println("Kako se Scale menja: "+ scale);    

					cx += scale*(arg0.getPoint().getX() - xIscitan/2) ;
					cy += scale*(arg0.getPoint().getY() - yIscitan/2);
					repaint();
				}
				if (SwingUtilities.isRightMouseButton(arg0)) {
			
					scale /= faktorZoom;                                  
					cx +=scale*(arg0.getPoint().getX() - xIscitan/2) ;    
					cy += scale*(arg0.getPoint().getY() - yIscitan/2);
					repaint();
				}
			}
		});
		
		getColorBeta();
	}



	private void getColorBeta(){
		ArrayList<String> bojeL = new ArrayList<String>();
		bojeL = (ArrayList<String>) ColorsList.getColors();
		
		for(int i=0;i<bojeL.size();i++ ){
				boje2.put(i, hex2Rgb(bojeL.get(i)));  
		}
	}
	
	private Color hex2Rgb(String colorStr) {
		
		return new Color(
				Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
				Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
				Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}

}


