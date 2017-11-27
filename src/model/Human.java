package model;

public class Human extends Agent{
	
	public Human(int x, int y, Perception perception) {
		super(x,y, perception);
	}

	public Human(Perception perception) {
		super(perception);
	}

	@Override
	public void live() {
		this.move((int)((Math.random()-0.5)*4.5), (int)((Math.random()-0.5)*4.5));
		this.perception.getNearestZombieFrom(this.getX(), this.getY());
	}
	
	@Override
	public Agent getNextState()
	{
		return new Zombie(this.getX(), this.getY(), this.perception);
	}

}
