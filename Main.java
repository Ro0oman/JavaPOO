/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/

abstract class Personaje
{

  //ATRIBUTOS:

  int edad;
  char sexo;
  private int vida = 100;
  boolean esEscudo = false;
  int ataque;
  int defensa;
  int velocidad;
  boolean esMuerto = false;

  //CONSTRUCTOR

  Personaje (int edad, char sexo,int ataque,int defensa){
      this.edad=edad;
      this.sexo=sexo;
      this.ataque=ataque;
      this.defensa=defensa;
  }
  
  //METODOS
  
  void beberpocion(String tipo) {
	  if(tipo.equalsIgnoreCase("sanadora")) {
		  this.vida = 100;
		  System.out.println("Sanadora");
	  }
	  if(tipo.equalsIgnoreCase("invencible")) {
		  this.esEscudo = true;
		  System.out.println("Invencible");
	  }
	  if(tipo.equalsIgnoreCase("superataque")) {
		  this.ataque+=100;
		  System.out.println("Superataque");
	  }
  }
  
    void atacar(Personaje p){
      
    	if(this.esMuerto == false && p.esMuerto == false) { //No estan muertos
    		this.gritoGuerra();
    		p.gritoGuerra();
    		//Compruebo si el atacante tiene arma, para hacer eso tengo que saber quien ataca
    		if(p.esEscudo == false) {
				if(this.getClass() == Arquero.class) {
					if(((Arquero)this).get_esArco()) {
						if(this.ataque < p.defensa) {//el atacante tiene menos ataque que defensa
							System.out.println("Tienes mucha defensa");
							}
						}
						else {//el atacante tiene mas ataque que defensa el enemigo
							if(p.getClass() == Arquero.class) {
								((Arquero)p).esconderse();
							}
							else if(p.getClass() == Caballero.class) {
								((Caballero)p).cabalgar();
							}
							else {
								((Amazonas)p).navegar();
							}
							if(p.esEscudo == false) {
								((Arquero)this).NumFlechas = ((Arquero)this).NumFlechas -1;
								p.vida = p.vida - 10; // Le quita la vida al enemigo si no tiene escudo
								if(((Arquero)this).NumFlechas>100) {
									p.vida = p.vida - 20;
								}
							}
							else {
								System.out.println("El atacado a huido exitosamente");
							}	
						}
					}else if(this.getClass() == Caballero.class) {
						if(((Caballero)this).get_esEspada()){
							if(this.ataque < p.defensa) {//el atacante tiene menos ataque que defensa
								System.out.println("Tienes mucha defensa");
								}
							else {
								if(p.getClass() == Arquero.class) {
									((Arquero)p).esconderse();
								}
								else if(p.getClass() == Caballero.class) {
									((Caballero)p).cabalgar();
								}
								else {
									((Amazonas)p).navegar();
								}
								if(p.esEscudo == false) {
									p.vida = p.vida - 50; // Le quita la vida al enemigo si no tiene escudo
									if(((Caballero)this).get_esCaballo() == true) { //Multiplica x2 el daño
										p.vida = p.vida - 100;
								}
							}
						}
					}else if(this.getClass() == Amazonas.class) {
						if(((Amazonas)this).jabalinas>0){//Si tienen jabalinas pelean
							if(this.ataque < p.defensa) {//el atacante tiene menos ataque que defensa
								System.out.println("Tienes mucha defensa");
								}
							else {
								if(p.getClass() == Arquero.class) {
									((Arquero)p).esconderse();
								}
								else if(p.getClass() == Caballero.class) {
									((Caballero)p).cabalgar();
								}
								else {
									((Amazonas)p).navegar();
								}
								if(p.esEscudo == false) {
									p.vida = p.vida - 20; // Le quita la vida al enemigo si no tiene escudo
									((Amazonas)this).jabalinas = ((Amazonas)this).jabalinas -1;
									if(((Amazonas)this).numeroTribu > 8) { //Multiplica x2 el daño si la tribu es +8
										p.vida = p.vida - 40;
										((Amazonas)this).jabalinas = ((Amazonas)this).jabalinas -1;
									}
								}	
							
							
							}
							
						}
					}

				}
    	}      
    }
    }
    
    abstract void gritoGuerra();
    
    void comer(int comida){
    	if((this.vida+comida)>100) {
			this.vida = 100;
		}
		else {
			this.vida = this.vida + comida;
		}
    }
}

class Arquero extends Personaje{
    
    private boolean esArco=true;
    int NumFlechas;
    
    public Arquero	(int edad, char sexo,int ataque, int defensa,
    				int NumFlechas) {
		super(edad, sexo, ataque, defensa);
		
		this.NumFlechas = NumFlechas;
	}
    
    Boolean get_esArco() {
    	return this.esArco;
    }
    
    void set_arco(Boolean valor) {
    	this.esArco = valor;
    }
    
    
    void gritoGuerra() {
    	System.out.println("Fiuuuuuu");
    }
    
    void darArma(Boolean valor) {
    	if(valor == true) {
    		this.esArco = true;
    	}
    	else {
    		this.esArco = false;
    	}
    }
    
    void recargar(int cantidad) {
    	this.NumFlechas = this.NumFlechas + cantidad;
    }
    
    void esconderse() {
    	double aleatorio = Math.random();
    	if (aleatorio == 1) {
    	    this.esEscudo = true;
    	  } 
    	else {
    	    this.esEscudo = false;
    	}
    }
}

class Caballero extends Personaje{
    
    private boolean esCaballo=true;
    private boolean esEspada=true;

    public Caballero(int edad, char sexo,int ataque, int defensa) {
		super(edad, sexo, ataque, defensa);
		
	}
    
    void set_caballo(Boolean valor) {
    	this.esCaballo = valor;
    }
    
    Boolean get_esCaballo() {
    	return this.esCaballo;
    }
    
    Boolean get_esEspada() {
    	return this.esEspada;
    }
    
    void gritoGuerra() {
    	System.out.println("Por Cristo Rey");
    }
    
    void darArma(Boolean valor) {
    	if(valor == true) {
    		this.esEspada = true;
    	}
    	else {
    		this.esEspada = false;
    	}
    }
    
    void cabalgar() {
    	this.esCaballo = true;
    }
    
}

class Amazonas extends Personaje{
	
    int jabalinas;
    boolean esCanoa=true;
    int numeroTribu = 0;
    
    public Amazonas(int edad, char sexo,int ataque, int defensa, int jabalinas) {
		super(edad, sexo, ataque, defensa);
		this.numeroTribu++;
		this.jabalinas = jabalinas;
	}
    
    void navegar() {
    	if(this.numeroTribu <= 3 && this.esCanoa) {
    		this.esEscudo = true;
    	}
    }
    
    void gritoGuerra() {
    	System.out.println("UAAAAA!!!");
    }

    void recargar(int cantidad) {
    	this.jabalinas = this.jabalinas + cantidad;
    }
    
}



public class Main
{
  public static void main (String[]args)
  {
    
	  Arquero Arquero1 = new Arquero(23,'H',60,70,20);
	  Caballero Caballero1 = new Caballero(30, 'H', 90, 40);
	  Amazonas Amazona1 = new Amazonas(19,'H', 50, 20, 4);
	  
	  //b
	  Arquero1.beberpocion("superataque");
	  //c
	  Arquero1.atacar(Caballero1);
	  //d
	  Caballero1.atacar(Arquero1);
	  //e
	  Caballero1.set_caballo(false);
	  //f
	  Amazona1.atacar(Caballero1);
	  //g
	  Caballero1.comer(20);
	  //h
	  Caballero1.atacar(Amazona1);
	  Caballero1.atacar(Amazona1);
	  //i
	  Caballero1.beberpocion("invencible");
	  //j
	  Arquero1.set_arco(false);
	  //k
	  
	  
	  
  }
}