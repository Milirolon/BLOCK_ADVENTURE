package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private boolean juego=true;
	private boolean gameover=false;
	private Timer timer;
	private Timer timerjuego;
	public static boolean permitirNivel2= false;
    public static boolean permitirNivel3=false;

	private Monstruo[] pingu;
	private int contpingu = 0;
	private int contllaves = 0;
	private int tiempojuego = 0;
	private int puntaje = 0;
	private int xjugador = 500;
	private int yjugador = 721;
	private int velocjugador = 5;
	
	List<Integer> llaveposx = new ArrayList<>();
	List<Integer> llaveposy = new ArrayList<>();	
	List<Bloque> bloques = new ArrayList<>();
	
	private boolean vida = true;
	private boolean plyr_left = false;
	private boolean plyr_right = false;
	private boolean plyr_up = false;
	private boolean plyr_down = false;
	
	public Gameplay() {
		timer = new Timer(40, this);
		timer.start();
		setFocusable(true);
		addKeyListener(this);
       
        agregarBloques();
		agregarLlaves();
		agregarEnemigos();
		
		timerjuego = new Timer(1000, new ActionListener() {  //temporizador de juego
		    public void actionPerformed(ActionEvent e) {
		        if (tiempojuego > 0) {
		        	tiempojuego--; 
		            repaint();  
		        } else if(tiempojuego < 1){
		        	juegoTerminado();  // Detiene el temporizador	            
		        }
		    }
		});
		timerjuego.start();
	}
	

	@Override
    public void paint(Graphics g) {
		moverJugador();
		verificarColision();
		
		ImageIcon PJ = new ImageIcon();
		ImageIcon PJ1 = new ImageIcon();
		ImageIcon PJ2 = new ImageIcon();
		ImageIcon PJ3 = new ImageIcon();
		ImageIcon PJ4 = new ImageIcon();
		if (Eligepj.PJvalor == 1) {  //establecer skin de finn
		    PJ = new ImageIcon(Gameplay.class.getResource("/Imagenes/finnFrente.gif"));
			PJ1 = new ImageIcon(Gameplay.class.getResource("/Imagenes/finnAtras.png"));
			PJ2 = new ImageIcon(Gameplay.class.getResource("/Imagenes/finnAbajo.gif"));
			PJ3 = new ImageIcon(Gameplay.class.getResource("/Imagenes/finnIzqui.gif"));
			PJ4 = new ImageIcon(Gameplay.class.getResource("/Imagenes/finnDerec.gif"));
		}
		else if(Eligepj.PJvalor == 2) {  //establecer skin de jake
			PJ = new ImageIcon(Gameplay.class.getResource("/Imagenes/jakeFrente.gif"));
			PJ1 = new ImageIcon(Gameplay.class.getResource("/Imagenes/jakeAtras.png"));
			PJ2 = new ImageIcon(Gameplay.class.getResource("/Imagenes/jakeAbajo.gif"));
			PJ3 = new ImageIcon(Gameplay.class.getResource("/Imagenes/jakeIzqui.gif"));
			PJ4 = new ImageIcon(Gameplay.class.getResource("/Imagenes/jakeDerec.gif"));
		}
		
		//Declaracion de imagenes utilizadas en gameplay
		ImageIcon pingu1 = new ImageIcon(Gameplay.class.getResource("/Imagenes/pinguFrente.gif"));
		ImageIcon fondo = new ImageIcon(Gameplay.class.getResource("/Imagenes/fondo1.jpeg"));
		ImageIcon bloqueimg = new ImageIcon(Gameplay.class.getResource("/Imagenes/hielo.jpg"));
		ImageIcon bloqueimg1 = new ImageIcon(Gameplay.class.getResource("/Imagenes/bloqhiel.png"));
		ImageIcon llave = new ImageIcon(Gameplay.class.getResource("/Imagenes/llave1.png"));
    	ImageIcon gameoverlogo = new ImageIcon(Gameplay.class.getResource("/Imagenes/gameover2.png"));
    	ImageIcon levellogo = new ImageIcon(Gameplay.class.getResource("/Imagenes/levelimg.png"));
    	ImageIcon finallogo = new ImageIcon(Gameplay.class.getResource("/Imagenes/findela.png"));
    	ImageIcon imgfinal = new ImageIcon(Gameplay.class.getResource("/Imagenes/gameCompl.jpg"));
		
		g.drawImage(fondo.getImage(), 0, 65, Main.ancho, Main.alto, this); //imagen de fondo
        
		 g.setColor(new Color(0, 109, 172)); //panel azul superior
         g.fillRect(0, 0, Main.ancho, 65);
		
	    g.setColor(Color.white );
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        
        g.drawString("NIVEL: "+Niveles.nivel, 50, 40); //texto de nivel actual
        g.drawString("LLAVES: "+contllaves, 300, 40); //llaves restantes
	    g.drawString("TIEMPO: "+tiempojuego, 580, 40); //tiempo limite
        g.drawString("PUNTAJE: " + puntaje, 860, 40); //texto de puntaje   
	            
        if(plyr_up==false && plyr_down==false && plyr_left==false && plyr_right==false) {  //cambios de imagenes para movimiento de jugador
        	g.drawImage(PJ.getImage(), xjugador, yjugador, 50, 40, this);
        }
        else if(plyr_up==true) {
        	g.drawImage(PJ1.getImage(), xjugador, yjugador, 50, 40, this);
        }
        else if(plyr_down==true) {
        	g.drawImage(PJ2.getImage(), xjugador, yjugador, 50, 40, this);
        }
        else if(plyr_left==true) {
        	g.drawImage(PJ3.getImage(), xjugador, yjugador, 50, 40, this);
        }
        else if(plyr_right==true) {
        	g.drawImage(PJ4.getImage(), xjugador, yjugador, 50, 40, this);
        }

	    for(int i=0 ; i<21 ; i++) {   //declarar limite de mapa arriba y abajo
	    	g.drawImage(bloqueimg.getImage(), 0+50*i, 65, 50, 50, this); //arriba
	    	g.drawImage(bloqueimg.getImage(), 0+50*i, 766, 50, 50, this); //abajo
	    }
	    
	    for(int i=0 ; i<13 ; i++) {   //declarar limite de mapa a la izquierda y derecha
	    	g.drawImage(bloqueimg.getImage(), 0, 115+50*i, 50, 50, this);
	    	g.drawImage(bloqueimg.getImage(), 1000, 115+50*i, 50, 50, this);
	    }
	    
	    for (Bloque bloque : bloques) {
	    	g.drawImage(bloqueimg1.getImage(), bloque.getX(), bloque.getY(), bloque.getAncho(), bloque.getAlto(), this);  //declarar bloques repartidos por el mapa
        }
	    
	    for (int i=0 ; i<llaveposx.size() ; i++) {  //dibujar llaves en la pantalla
	        int xLlave = llaveposx.get(i);
	        int yLlave = llaveposy.get(i);
	        
	        g.drawImage(llave.getImage(), xLlave, yLlave, 36, 35, this);
	    }
	    
	    moverMonstruo();
	    for (int i = 0; i < contpingu; i++) {
	        g.drawImage(pingu1.getImage(), pingu[i].getX(), pingu[i].getY(), 50, 40, this); // Dibujar pinguinos en mapa
	    }
	    
	    if(tiempojuego == 0 && contllaves>0 || vida==false) {  //panel de juego terminado cuando se acabe el tiempo o cuando el jugador toque al enemigo
	    	timer.stop();
	    	g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(0, 85, 151));
            g.fillRect(250, 200, 550, 350);
            g.setColor(new Color(0, 109, 172));
            g.fillRect(270, 220, 510, 310);
            g.drawImage(gameoverlogo.getImage(), 280, 100, 500, 400, this);
            
            g.setColor(new Color(159, 26, 26));
            g.setFont(new Font("Consolas", Font.BOLD, 30));
            g.drawString("PUNTAJE: "+puntaje, 440, 415);
            g.setFont(new Font("Consolas", Font.BOLD, 26));
            g.drawString("PRESIONE ENTER PARA VOLVER A JUGAR", 280, 465);
            g.drawString("PRESIONE ESCAPE PARA MENU PRINCIPAL", 280, 505);
	    }
	    
	    if(contllaves==0 && Niveles.nivel<3) {   //panel de nivel completado al recoger todas las llaves
	    	timer.stop();
	    	g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(0, 85, 151));
            g.fillRect(250, 200, 550, 350);
            g.setColor(new Color(0, 109, 172));
            g.fillRect(270, 220, 510, 310);
            g.drawImage(levellogo.getImage(), 255, 175, 540, 270, this);
            
            g.setColor(new Color(159, 26, 26));
            g.setFont(new Font("Consolas", Font.BOLD, 30));
            g.drawString("PUNTAJE: "+puntaje+" + 200!", 380, 435);
            g.setFont(new Font("Consolas", Font.BOLD, 24));
            g.drawString("PRESIONE ENTER PARA EL SIGUIENTE NIVEL", 280, 480);
	    }
	    if(contllaves==0 && Niveles.nivel==3) {
	    	timer.stop();
	    	g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(232, 232, 208));
            g.fillRect(255, 70, 550, 680);
            g.drawImage(finallogo.getImage(), 255, 30, 540, 270, this);
	    	g.drawImage(imgfinal.getImage(), 255, 245, 550, 350, this);
	    	g.setColor(new Color(159, 26, 26));
	    	g.setFont(new Font("Consolas", Font.BOLD, 30));
            g.drawString("PUNTAJE FINAL: "+puntaje, 385, 620);
            g.drawString("Mas niveles a desarrollar...", 305, 665);
            g.setFont(new Font("Consolas", Font.BOLD, 20));
            g.drawString("PRESIONE ESCAPE PARA MENU PRINCIPAL", 345, 710);
	    }
    }
    
    public void agregarLlaves() {  //funcion para establecer la posicion y la cantidad de llaves en el mapa segun el nivel
		
    	if(Niveles.nivel==1) {  //LLAVES NIVEL 1
    		tiempojuego = 25;
    		contllaves = 3;
    		llaveposx.add(108);
    		llaveposy.add(173);
    		
    		llaveposx.add(908);
    		llaveposy.add(173);
    		
    		llaveposx.add(508);
    		llaveposy.add(623);
    	}
    	
    	if(Niveles.nivel==2) {  //LLAVES NIVEL 2
    		tiempojuego = 40;
    		contllaves = 5;
    		llaveposx.add(108);
    		llaveposy.add(173);
    		
    		llaveposx.add(908);
    		llaveposy.add(173);
    		
    		llaveposx.add(108);
    		llaveposy.add(673);
    		
    		llaveposx.add(908);
    		llaveposy.add(673);
    		
    		llaveposx.add(508);
    		llaveposy.add(273);
    	}
    	
    	if(Niveles.nivel==3) {  //LLAVES NIVEL 3
    		tiempojuego = 60;
    		contllaves = 8;
    		llaveposx.add(58);
    		llaveposy.add(123);
    		
    		llaveposx.add(508);
    		llaveposy.add(123);
    		
    		llaveposx.add(958);
    		llaveposy.add(123);
    		
    		llaveposx.add(208);
    		llaveposy.add(323);
    		
    		llaveposx.add(508);
    		llaveposy.add(423);
    		
    		llaveposx.add(808);
    		llaveposy.add(323);
    		
    		llaveposx.add(58);
    		llaveposy.add(723);
    		
    		llaveposx.add(958);
    		llaveposy.add(723);
    	}
    }
    
    public void agregarBloques() { //funcion para establecer la posicion y la cantidad de bloques en el mapa segun el nivel
    	if(Niveles.nivel==1) {   		//BLOQUES NIVEL 1
    		for(int i=0 ; i<19 ; i++) {
    		    for(int j=0 ; j<11 ; j++) {
    				
    				if(j!=3 && j!=5 && j!=8 && j!=11 && j!=12) {
    					if(j==0) {
        					if(i<=2 || i>=16) {
        						bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
        				    }
    					}
    					else if(j==1) {
        					if(i!=1 && i!=17) {
        						bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
        					}
            			}
    					else if(j==6 || j==7) {
        					if(j==6 && i % 2 != 0) {
        						bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
        					}
        					if(j==7 && i % 2 == 0) {
        						bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
        					}
        				}
    					else if (j==10) {
        					if(i!=9) {
        						bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
        					}
        				}
    					else {
    						bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
            			}
    				}
    			}
    		}
    	}   	
    	if(Niveles.nivel==2) {  //BLOQUES NIVEL 2
    		for(int i=0 ; i<19 ; i++) {
    		    for(int j=0 ; j<13 ; j++) {
    		    	if(j==1) {
    					if(i!=1 && i!=4 && i!=9 && i!=14 && i!=17) {
    						bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    					}
        			}
    		    	else if(j==11) {
    		    		if(i!=1 && i!=4 && i!=9 && i!=14 && i!=17) {
    						bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    					}
    		    	}
    		    	else if(j==6) {
    		    		if(i>6 && i<12) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else if(i==9) {
    		    		if(j>3 && j<9) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else if(i==4) {
    		    		if(j==5) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    		if(j==7) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else if(i==14) {
    		    		if(j==5) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    		if(j==7) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else if(j==4) {
    		    		if(i>4 && i<14) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else if(j==8) {
    		    		if(i>4 && i<14) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else {
    		    		bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    	}
    		    }
    		}    
    	}
    	if(Niveles.nivel==3) {  //BLOQUES NIVEL 3
    		for(int i=0 ; i<19 ; i++) {
    		    for(int j=0 ; j<13 ; j++) {
    		    	if(j==0) {
    		    		if(i>3 && i<7 || i>11 && i<15) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}   		    	
    		    	}
    		    	else if(j==12) {
    		    		if(i!=0 && i!=9 && i!=18) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else if(j==4) {
    		    		if(!(i>6 && i<12)) {
                            if(i!=2 && i!=3 && i!=4 && i!=14 && i!=15 && i!=16) {
                            	bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    			}
    		    		}
    		    	}
    		    	else if(j==8) {
    		    		if(!(i>6 && i<12)) {
    		    			if(i!=2 && i!=3 && i!=4 && i!=14 && i!=15 && i!=16) {
                            	bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    			}
    		    		}
    		    	}
    		    	else if(i==7) {
    		    		if(!(j>3 && j<9)) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));    
    		    		}
    		    	}
    		    	else if(i==11) {
    		    		if(!(j>3 && j<9)) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else if(i>1 && i<5) {
    		    		if(!(j>3 && j<9)) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else if(i>13 && i<17) {
    		    		if(!(j>3 && j<9)) {
    		    			bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    		}
    		    	}
    		    	else if(!(i==9 && j==6)){
    		    		bloques.add(new Bloque(50+50*i, 115+50*j, 50, 50));
    		    	}
    		    }
    		}
    	}
    }
    
    public void agregarEnemigos() {  //funcion para agregar enemigos en mapa
    	if(Niveles.nivel==1) {  //ENEMIGOS NIVEL 1
    		contpingu = 1;
    		pingu = new Monstruo[1];
    		pingu[0] = new Monstruo(500, 521);
    	}
    	if(Niveles.nivel==2){  //ENEMIGOS NIVEL 2
    		contpingu = 4;
    		pingu = new Monstruo[4];
    		pingu[0] = new Monstruo(50, 321);
    		pingu[1] = new Monstruo(950, 321);
    		pingu[2] = new Monstruo(50, 521);
    		pingu[3] = new Monstruo(950, 521);
    	}
    	if(Niveles.nivel==3) {  //ENEMIGOS NIVEL 3
    		contpingu = 6;
    		pingu = new Monstruo[6];
    		pingu[0] = new Monstruo(50, 121);
    		pingu[1] = new Monstruo(500, 121);
    		pingu[2] = new Monstruo(950, 121);
    		pingu[3] = new Monstruo(200, 421);
    		pingu[4] = new Monstruo(500, 321);
    		pingu[5] = new Monstruo(800, 421);
    	}
    }
	
    public void moverJugador() {  //Funcion de movimiento de jugador con su velocidad en 4 direcciones
		if (juego==true) {
			if(plyr_left==true) {
				xjugador -= velocjugador;
				if(xjugador < 50) {
					xjugador=50;
				}
			}
			if(plyr_right==true) {
				xjugador += velocjugador;
				if(xjugador > getWidth() - 100) {
					xjugador = getWidth() - 100;
				}
			}
			if(plyr_up==true) {
				yjugador -= velocjugador;
				if(yjugador < 115) {
					yjugador=115;
				}
			}
			if(plyr_down==true) {
				yjugador += velocjugador;
				if(yjugador > getHeight() - 90) {
					yjugador = getHeight() - 90;
				}
			}
		}
	}
    
    public void moverMonstruo() {
        for (int i = 0; i < contpingu; i++) {
            pingu[i].moverMonstruoConColision(bloques, 1066, 855);
        }
    }
	
	public void verificarColision() {  //funcion de colisiones con enemigos, bloques, y llaves con el jugador
		if(contllaves==0) {
	    	juegoTerminado();
	    }
		Rectangle jugador = new Rectangle(xjugador, yjugador, 50, 40);
		
		 for (int i = 0; i < contpingu; i++) {  //colision con enemigos
	            Rectangle areaMonstruo = pingu[i].obtenerAreaMonstruo();
	            if (jugador.intersects(areaMonstruo)) {
	                vida=false;
	                juegoTerminado(); // Termina el juego si el jugador colisiona con el monstruo
	            }
	        }
		
		for (Bloque bloque : bloques) {  //colision con bloques
		    if (jugador.intersects(bloque.getX(), bloque.getY(), bloque.getAncho(), bloque.getAlto())) {
		    	Rectangle interseccion = jugador.intersection(new Rectangle(bloque.getX(), bloque.getY(), bloque.getAncho(), bloque.getAlto()));

		    	if (interseccion.height > interseccion.width){
		            if (jugador.getX() < bloque.getX()) { 
		                xjugador = bloque.getX() - 50;  //colision con bloque del lado izquierdo
		            } else {
		                xjugador = bloque.getX() + bloque.getAncho();  //colision con bloque del lado derecho
		            }
		        }
		    	else if (interseccion.height < interseccion.width) {
		            if (jugador.getY() < bloque.getY()) {
		                yjugador = bloque.getY() - 40;   //colision con bloque del lado superior
		            } else {
		                yjugador = bloque.getY() + bloque.getAlto();   //colision con bloque del lado inferior
		            }
		        }
		   }
		}    
	    
	    Iterator<Integer> iterX = llaveposx.iterator();
	    Iterator<Integer> iterY = llaveposy.iterator();
	    
	    while (iterX.hasNext() && iterY.hasNext()) {  //colisiones para quitar llaves cuando el jugador choca con ellas
	        int xLlave = iterX.next();
	        int yLlave = iterY.next();
	        
	        Rectangle areaLlave = new Rectangle(xLlave+10, yLlave+10, 20, 20);
	        
	        if (jugador.intersects(areaLlave)) {
	            iterX.remove(); 
	            iterY.remove();
	            puntaje+=25;
	            contllaves--;
	        }
	    }
	}
	
	public void reiniciar() {  //funcion para reiniciar juego si se termino el tiempo
		juego=true;
		gameover=false;
		bloques.clear();
		llaveposx.clear();
		llaveposy.clear();
		puntaje = 0;
        xjugador = 500;
		yjugador = 721;
		vida = true;
		plyr_left = false;
		plyr_right = false;
		plyr_up = false;
		plyr_down = false;
		agregarBloques();
        agregarLlaves();
        agregarEnemigos();
		
		timerjuego.start();
		timer.start();
	}
	
	public void pasarNivel() {  //funcion para pasar al siguiente nivel
		juego=true;
		gameover=false;
		bloques.clear();
		llaveposx.clear();
		llaveposy.clear();
		Niveles.nivel++;
		if(Niveles.nivel==2) {
			permitirNivel2=true;
		}
        if(Niveles.nivel==3) {
        	permitirNivel3=true;
		}
		xjugador = 500;
	    yjugador = 721;	
		plyr_left = false;
		plyr_right = false;
		plyr_up = false;
		plyr_down = false;
	    puntaje+=200;
	    agregarBloques();
		agregarLlaves();
		agregarEnemigos();

		timerjuego.start();
		timer.start();
	}
	
	public void juegoTerminado() {  //funcion de juego terminado, el jugador deja de moverse
		timer.stop();
		gameover=true;
		juego=false;
		timerjuego.stop();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {  //mover pinguinos dentro del limite de mapa, repintar cada movimiento del juego, y comenzar el timer de juego 
		 for (int i = 0; i < contpingu; i++) {
 	        pingu[i].moverMonstruoConColision(bloques, 1066, 855);
 	    }
		repaint();
		timer.start();
	}
	@Override
	public void keyPressed(KeyEvent e) {   //Eventos de teclado para mover al jugador en 4 direcciones
		if(juego==true) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				plyr_left=true;
			}
	        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        	plyr_right=true;
			}
	        if (e.getKeyCode() == KeyEvent.VK_UP) {
				plyr_up=true;
			}
	        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	        	plyr_down=true;
			}
	        if (e.getKeyCode() == KeyEvent.VK_SPACE) {  //quitar bloques al presionar espacio cuando el jugador colisiona con ellos
	        	Rectangle jugador = new Rectangle(xjugador, yjugador, 50, 40);
	            List<Bloque> eliminarBloques = new ArrayList<>();
	            
	            for (Bloque bloque : bloques) {
	                int xBloque = bloque.getX();
	                int yBloque = bloque.getY();             
	                Rectangle areaBloqueVert = new Rectangle(xBloque+20, yBloque-1, 10, 52);
	                Rectangle areaBloqueHori = new Rectangle(xBloque-1, yBloque+20, 52, 10);

	                if (jugador.intersects(areaBloqueVert) || jugador.intersects(areaBloqueHori)) {
	                	eliminarBloques.add(bloque);
	                }
	        	}
	            bloques.removeAll(eliminarBloques);
	            
			}
		}
        if (e.getKeyCode() == KeyEvent.VK_ENTER) { //boton "ENTER" para pasar de nivel
        	if(gameover==true && contllaves<=0 && Niveles.nivel<3) {
           		pasarNivel();
           	}
           	if(gameover==true && contllaves>0) {
           		reiniciar();
           	} 	
		}
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  //boton "ESCAPE" para volver al menu principal
        	if(gameover==true && contllaves>0) {
        		JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(this);
        		frameActual.dispose();
        		Inicio.main(new String[]{});
        	}
		}
        
	}

	@Override
	public void keyReleased(KeyEvent e) {  //dejar de mover al jugador al soltar las teclas
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			plyr_left=false;
		}
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	plyr_right=false;
		}
        if (e.getKeyCode() == KeyEvent.VK_UP) {
			plyr_up=false;
		}
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        	plyr_down=false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

}