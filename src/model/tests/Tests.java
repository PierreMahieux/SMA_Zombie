package model.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import model.agents.Zombie;
import utils.MyMaths;

public class Tests {

	@Test
	public final void usableDirectionTest()
	{
		Zombie z = new Zombie(null);
		
		double [] a = MyMaths.normaliseVector(0, 2);

		assertEquals(0, z.getUsableDirection(a)[0]);
		assertEquals(2, z.getUsableDirection(a)[1]);
		
		a = MyMaths.normaliseVector(0, -2);
		
		double[] expected = {0,-1};

		assertArrayEquals(expected, a, 0.01);

		assertEquals(0, z.getUsableDirection(a)[0]);
		assertEquals(-2, z.getUsableDirection(a)[1]);
	}
}
