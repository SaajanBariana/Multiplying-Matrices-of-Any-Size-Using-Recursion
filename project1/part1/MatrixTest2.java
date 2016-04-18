package sjsu.Bariana.cs146.project1.part1;
import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


public class MatrixTest2 extends TestCase
{
	
	//Create the instance variables
	private Matrix normal;
	private Matrix normal2;
	private Matrix normalresult;
	private Matrix Strassenresult;

	@Before
	public void setUp() throws Exception
	{
		normal = new Matrix(new double[4][4]);
		normal2 = new Matrix(new double[4][4]);
	}
   
	/*
	 * Test the matrices for size 4
	 */
	@Test
	public void testfor4()
	{
		normal = normal.random();
		normal2 = normal2.random();
		System.out.println("Testing for a random array with length of 4:");
		long now = System.nanoTime();
		normalresult = normal.multiply(normal2);
		long time = System.nanoTime() - now;
		System.out.println("Normal time: " + time + " nanoseconds");
		now = System.nanoTime();
		Strassenresult = normal.multiplyStrassen(normal2);
		time = System.nanoTime() - now;
		System.out.println("Strassen time: " + time + " nanoseconds");
		for (int i = 0; i < normalresult.matrix.length; i++)
		{
			assertArrayEquals(normalresult.matrix[i], Strassenresult.matrix[i],  0.0001);
		}
		System.out.println("");
	}
		
	/*
	 * Test the matrices for 8 with a random array
	 */
	@Test
	public void testfor8Unknown()
	{
		normal = new Matrix(new double[8][8]);
		normal2 = new Matrix(new double[8][8]);
		normal =normal.random();
		normal2 = normal2.random();
		System.out.println("Testing for a random array with length of 8:");
		long now = System.nanoTime();
		normalresult = normal.multiply(normal2);
		long time = System.nanoTime() - now;
		System.out.println("Normal time: " + time + " nanoseconds");
		now = System.nanoTime();
		Strassenresult = normal.multiplyStrassen(normal2);
		time = System.nanoTime() - now;
		System.out.println("Strassen time: " + time + " nanoseconds");
		for (int i = 0; i < normal.matrix.length; i++)
		{
			assertArrayEquals(normalresult.matrix[i], Strassenresult.matrix[i],  0.0001);
		}
		System.out.println("");
	}
	
	/*
	 * Test the matrices for 8 with an expected array
	 */
	@Test
	public void testfor8KnownStrassen()
	{
		double[][] expected  = {
				{762, 732, 702, 672, 642, 612, 582, 552},
				{2884, 2792, 2700, 2608, 2516, 2424, 2332, 2240}, 
				{5124, 4968, 4812, 4656, 4500, 4344, 4188, 4032},
				{7364, 7144, 6924, 6704, 6484, 6264, 6044, 5824},
				{9604, 9320, 9036, 8752, 8468, 8184, 7900, 7616},
				{11844, 11496, 11148, 10800, 10452, 10104, 9756, 9408}, 
				{14084, 13672, 13260, 12848, 12436, 12024, 11612, 11200}, 
				{16324, 15848, 15372, 14896, 14420, 13944, 13468, 12992},
				};
		double[][] temp1 = {
				{1, 2, 2, 3, 4, 5, 6, 7},
				{8, 9, 10, 11, 12, 13, 14, 15},
				{16, 17, 18, 19, 20, 21, 22, 23},
				{24, 25, 26, 27, 28, 29, 30, 31},
				{32, 33, 34, 35, 36, 37, 38, 39},
				{40, 41, 42, 43, 44, 45, 46, 47},
				{48, 49, 50, 51, 52, 53, 54, 55},
				{56, 57, 58, 59, 60, 61, 62, 63}
				};
		double[][] temp2 = {
				{63, 62, 61, 60, 59, 58, 57, 56},
				{55, 54, 53, 52, 51, 50, 49, 48},
				{47, 46, 45, 44, 43, 42, 41, 40},
				{39, 38, 37, 36, 35, 34, 33, 32},
				{31, 30, 29, 28, 27, 26, 25, 24},
				{23, 22, 21, 20, 19, 18, 17, 16},
				{15, 14, 13, 12, 11, 10, 9, 8},
				{7, 6, 5, 4, 3, 2, 1, 0}
				};
		normal = new Matrix(temp1);
		normal2 = new Matrix(temp2);
		System.out.println("Testing for a array length of 8 with a known array using the Strassen Method:");
		long now = System.nanoTime();
		Strassenresult = normal.multiplyStrassen(normal2);
		long time = System.nanoTime() - now;
		System.out.println("Strassen time: " + time + " nanoseconds");
		for (int i = 0; i < normal.matrix.length; i++)
		{
			assertArrayEquals(expected[i], Strassenresult.matrix[i], 0.0);
		}
		System.out.println("");
	}
	
	@Test
	public void testfor8KnownNormal()
	{
		double[][] expected  = {
				{762, 732, 702, 672, 642, 612, 582, 552},
				{2884, 2792, 2700, 2608, 2516, 2424, 2332, 2240}, 
				{5124, 4968, 4812, 4656, 4500, 4344, 4188, 4032},
				{7364, 7144, 6924, 6704, 6484, 6264, 6044, 5824},
				{9604, 9320, 9036, 8752, 8468, 8184, 7900, 7616},
				{11844, 11496, 11148, 10800, 10452, 10104, 9756, 9408}, 
				{14084, 13672, 13260, 12848, 12436, 12024, 11612, 11200}, 
				{16324, 15848, 15372, 14896, 14420, 13944, 13468, 12992},
				};
		double[][] temp1 = {
				{1, 2, 2, 3, 4, 5, 6, 7},
				{8, 9, 10, 11, 12, 13, 14, 15},
				{16, 17, 18, 19, 20, 21, 22, 23},
				{24, 25, 26, 27, 28, 29, 30, 31},
				{32, 33, 34, 35, 36, 37, 38, 39},
				{40, 41, 42, 43, 44, 45, 46, 47},
				{48, 49, 50, 51, 52, 53, 54, 55},
				{56, 57, 58, 59, 60, 61, 62, 63}
				};
		double[][] temp2 = {
				{63, 62, 61, 60, 59, 58, 57, 56},
				{55, 54, 53, 52, 51, 50, 49, 48},
				{47, 46, 45, 44, 43, 42, 41, 40},
				{39, 38, 37, 36, 35, 34, 33, 32},
				{31, 30, 29, 28, 27, 26, 25, 24},
				{23, 22, 21, 20, 19, 18, 17, 16},
				{15, 14, 13, 12, 11, 10, 9, 8},
				{7, 6, 5, 4, 3, 2, 1, 0}
				};
		normal = new Matrix(temp1);
		normal2 = new Matrix(temp2);
		System.out.println("Testing for a array length of 8 with a known array using the normal mathod:");
		long now = System.nanoTime();
		normalresult = normal.multiply(normal2);
		long time = System.nanoTime() - now;
		System.out.println("Normal time: " + time + " nanoseconds");
		for (int i = 0; i < normal.matrix.length; i++)
		{
			assertArrayEquals(expected[i], normalresult.matrix[i], 0.0);
		}
		System.out.println("");
	}
	
	/*
	 * Test the matrices for 512
	 */
	@Test
	public void testfor512()
	{
		normal = new Matrix(new double[512][512]);
		normal2 = new Matrix(new double[512][512]);
		normal =normal.random();
		normal2 = normal2.random();
		System.out.println("Testing for a random array with length of 512:");
		long now = System.currentTimeMillis();
		normalresult = normal.multiply(normal2);
		long time = System.currentTimeMillis() - now;
		System.out.println("Normal time: " + time + " milliseconds");
		now = System.currentTimeMillis();
		Strassenresult = normal.multiplyStrassen(normal2);
		time = System.currentTimeMillis() - now;
		System.out.println("Strassen time: " + time + " milliseconds");
		for (int i = 0; i < normal.matrix.length; i++)
		{
			assertArrayEquals(normalresult.matrix[i], Strassenresult.matrix[i],  0.0001);
		}
		System.out.println("");
	}
	
	/*
	 * Test the matrices for 1024
	 */
	@Test
	public void testfor1024()
	{
		normal = new Matrix(new double[1024][1024]);
		normal2 = new Matrix(new double[1024][1024]);
		normal =normal.random();
		normal2 = normal2.random();
		System.out.println("Testing for a random array with length of 1024:");
		long now = System.currentTimeMillis();
		normalresult = normal.multiply(normal2);
		long time = System.currentTimeMillis() - now;
		System.out.println("Normal time: " + time + " milliseconds");
		now = System.currentTimeMillis();
		Strassenresult = normal.multiplyStrassen(normal2);
		time = System.currentTimeMillis() - now;
		System.out.println("Strassen time: " + time + " milliseconds");
		for (int i = 0; i < normal.matrix.length; i++)
		{
			assertArrayEquals(normalresult.matrix[i], Strassenresult.matrix[i],  1);
		}
		System.out.println("");
	}
	
	
	
}
