package sjsu.Bariana.cs146.project1.part1;
public class MatrixMultiplicatonTest 
{
	public static void main(String[] args) 
	{
		// create two double 2-D arrays   
		double[][] a = new double[][] {
									   {1, 2, 3, 4, 3, 4, 5, 8},
									   {3, 4, 5, 7, 3, 4, 5, 8},
									   {1, 2, 3, 7, 3, 4, 5, 8}, 
									   {3, 4, 5, 8, 3, 4, 5, 8},
									   {3, 4, 5, 8, 3, 4, 5, 8},
									   {3, 4, 5, 8, 3, 4, 5, 8},
									   {3, 4, 5, 8, 3, 4, 5, 8},
									   {3, 4, 5, 8, 3, 4, 5, 8}
									   };
									   
		double[][] b = new double[][] {
									   {5, 6, 7, 8, 7, 8, 9, 7},
									   {3, 4, 2, 1, 7, 8, 9, 7},
									   {4, 5, 6, 5, 7, 8, 9, 7},
									   {7, 8, 9, 7, 7, 8, 9, 7},
									   {3, 4, 5, 8, 3, 4, 5, 8},
									   {3, 4, 5, 8, 3, 4, 5, 8},
									   {3, 4, 5, 8, 3, 4, 5, 8},
									   {3, 4, 5, 8, 3, 4, 5, 8}
									   };
		Matrix m1 = new Matrix(a);  
		Matrix m2 = new Matrix(b);   
		Matrix productRegular=m1.multiply(m2);  
		Matrix productStrassen=m1.multiplyStrassen(m2); 
		boolean same = true;
		while(same)
		{
			for (int i = 0; i < productRegular.matrix.length; i++)
			{
				for(int j = 0; j < productRegular.matrix.length; j++)
				{
					System.out.print(productRegular.matrix[i][j] + "-" + productStrassen.matrix[i][j] + " ");
					same = productRegular.matrix[i][j] == productStrassen.matrix[i][j];
				}
				System.out.println(" ");
			}
			break;
		}
		System.out.println("Are the matrices the same? " + same);
	}
}

