package sjsu.Bariana.cs146.project1.part1;

public class Matrix 
{	
	/*
	 * create the matrix
	 */
	public double[][] matrix;
	
	/*
	 * initialize the matrix
	 */
	public Matrix(double[][] now) {
		matrix = now;
	}
		
	/*
	 * Multiply the matrix using the O(n^3) algorithm
	 */
	public Matrix multiply(Matrix m2)
	{
		Matrix result = new Matrix(new double[matrix.length][matrix[0].length]);
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				for (int k = 0; k < matrix.length; k++)
				{
					result.matrix[i][j] = result.matrix[i][j] + matrix[i][k] * m2.matrix[k][j];
				}
			}	
		}
		return result;
	}
	
	public Matrix multiplyStrassen(Matrix m2)
	{
		//base case
		if(m2.matrix.length < 128)
		{
			return this.multiply(m2);
		}
		
		else
		{
			int length = m2.matrix.length/2;
			
			//create double arrays for m2 and matrix
			double[][] firstarray = new double[length][length];
			double[][] secondarray = new double[length][length];
			double[][] thirdarray = new double[length][length];
			double[][] fourtharray = new double[length][length];
			double[][] firstarray1 = new double[length][length];
			double[][] secondarray1 = new double[length][length];
			double[][] thirdarray1 = new double[length][length];
			double[][] fourtharray1 = new double[length][length];
			
			//split m2 and matrix into 4 arrays each
			for (int i = 0; i < length; i++)
			{
				for (int j = 0; j < length; j++)
				{
					firstarray[i][j] = m2.matrix[i][j];
					secondarray[i][j] = m2.matrix[i][j + length];
					thirdarray[i][j] = m2.matrix[i + length][j];
					fourtharray[i][j] = m2.matrix[i + length][j + length];
					firstarray1[i][j] = matrix[i][j];
					secondarray1[i][j] = matrix[i][j + length];
					thirdarray1[i][j] = matrix[i + length][j];
					fourtharray1[i][j] = matrix[i + length][j + length];
				}
			}
						
			//create the 8 Matrices
			Matrix a11 = new Matrix(firstarray1);
			Matrix a12 = new Matrix(secondarray1);
			Matrix a21 = new Matrix(thirdarray1);
			Matrix a22 = new Matrix(fourtharray1);
			Matrix b11 = new Matrix(firstarray);
			Matrix b12 = new Matrix(secondarray);
			Matrix b21 = new Matrix(thirdarray);
			Matrix b22 = new Matrix(fourtharray);

			//find the 7 P's
			Matrix p1 = a11.multiplyStrassen(b12.subtract(b22));
			Matrix p2 = a11.add(a12).multiplyStrassen(b22);
			Matrix p3 = a21.add(a22).multiplyStrassen(b11);
			Matrix p4 = a22.multiplyStrassen(b21.subtract(b11));
			Matrix p5 = a11.add(a22).multiplyStrassen(b11.add(b22));
			Matrix p6 = a12.subtract(a22).multiplyStrassen((b21.add(b22)));
			Matrix p7 = a11.subtract(a21).multiplyStrassen(b11.add(b12));
			
			//create the values for the C Matrix
			Matrix c11, c12, c21, c22 = new Matrix(new double[p1.matrix.length][p1.matrix.length]);
			

			//put all the P's into a c value
			c11 = p5.add(p4).subtract(p2).add(p6);
			c12 = p1.add(p2);
			c21 = p3.add(p4);
			c22 = (p5.add(p1)).subtract(p3).subtract(p7);
			
			// final matrix
			Matrix finalresult = new Matrix(new double[c11.matrix.length * 2][c12.matrix[0].length * 2]);
			
			//fill in the final matrix
			for (int i = 0; i < c11.matrix.length; i++)
			{
				for (int j = 0; j < c11.matrix.length; j++)
				{
					finalresult.matrix[i][j] = c11.matrix[i][j];
					finalresult.matrix[i][j + c11.matrix.length] = c12.matrix[i][j];
					finalresult.matrix[i + c11.matrix.length][j] = c21.matrix[i][j];
					finalresult.matrix[i + c11.matrix.length][j + c11.matrix.length] = c22.matrix[i][j];
				}
			}
			
			return finalresult;
		}
			
	}
	
	//create a method that adds matrices
	public Matrix add(Matrix m2)
	{
		Matrix result = new Matrix(new double[matrix.length][matrix.length]); 
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix.length; j++)
			{
				result.matrix[i][j] = matrix[i][j] + m2.matrix[i][j];
			}
		}
		return result;
	}
	
	//create a method that subtracts matrices
	public Matrix subtract(Matrix m2)
	{
		Matrix result = new Matrix(new double[m2.matrix.length][m2.matrix.length]); 
		for (int i = 0; i < m2.matrix.length; i++)
		{
			for (int j = 0; j < m2.matrix.length; j++)
			{
				result.matrix[i][j] = matrix[i][j] - m2.matrix[i][j];
			}
		}
		return result;
	}
	
	//create random method
	public Matrix random()
	{
		Matrix random = new Matrix(new double[matrix.length][matrix.length]);
		for (int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				random.matrix[i][j] = Math.random() * matrix.length * 2;
			}
		}
		return random;
	}
}

