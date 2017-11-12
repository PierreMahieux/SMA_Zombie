package model;

public class Zombie extends Agent{

	public Zombie(int x, int y) {
		super(x,y);
	}

	public Zombie() {
		super();
	}

	@Override
	public void live() {
		this.move((int)((Math.random()-0.5)*4), (int)((Math.random()-0.5)*4));
	}
	
	@Override
	public Agent getNextState()
	{
		return null; //Definitive
	}

}
