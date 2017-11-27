package model;

public class Zombie extends Agent{
	
	protected boolean miracleHealing = false;

	public Zombie(int x, int y, Perception perception) 
	{
		super(x,y,perception);
	}

	public Zombie(Perception perception)
	{
		super(perception);
	}

	@Override
	public void live()
	{
		this.move((int)((Math.random()-0.5)*4), (int)((Math.random()-0.5)*4));
		this.perception.getNearestHumanFrom(this.getX(), this.getY());
	}
	
	@Override
	public Agent getNextState()
	{
		if(miracleHealing) return new Human(this.getX(), this.getY(), this.perception);
		return null;
	}

}
