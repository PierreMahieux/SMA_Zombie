package model;

public class Human extends Agent{
	
	public Human(int x, int y) {
		super(x,y);
	}

	public Human() {
		super();
	}

	@Override
	public void live() {
		this.move((int)((Math.random()-0.5)*4), (int)((Math.random()-0.5)*4));
	}
	
	@Override
	public Agent getNextState()
	{
		return new Zombie(this.getX(), this.getY());
	}

}
