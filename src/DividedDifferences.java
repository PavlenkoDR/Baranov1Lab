import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DividedDifferences {

	public double X[];
	public double F[];
	public double tableOfFiniteDifferences[][];
	int size;
	Scanner in;
	public DividedDifferences(int _size, double[] _X, double[] _F) {
		X = _X;
		F = _F;
		size = _size;
		tableOfFiniteDifferences = new double[size][size];
		for (int i = 0; i < size; i++)
		{
			tableOfFiniteDifferences[i][0] = F[i];
			//System.out.print(tableOfFiniteDifferences[i][0] + "\t");
		}
		//System.out.println();
		//System.out.println();
		for (int i = 1; i < size; i ++)
		{
			for (int j = 0; j < size - i; j++)
			{
				tableOfFiniteDifferences[j][i] = tableOfFiniteDifferences[j + 1][i - 1] - tableOfFiniteDifferences[j][i - 1];
				//System.out.print(tableOfFiniteDifferences[j][i] + "\t");
			}
			//System.out.println();
		}
	}
	public DividedDifferences(File f) {
		try {
			in = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		size = in.nextInt();
		X = new double[size];
		F = new double[size];
		for (int i = 0; i < size; i++)
			X[i] = in.nextDouble();
		for (int i = 0; i < size; i++)
			F[i] = in.nextDouble();
		tableOfFiniteDifferences = new double[size][size];
		for (int i = 0; i < size; i++)
			tableOfFiniteDifferences[i][0] = F[i];
		for (int i = 1; i < size; i ++)
		{
			for (int j = 0; j < size - i; j++)
			{
				tableOfFiniteDifferences[i][j] = tableOfFiniteDifferences[i - 1][j + 1] - tableOfFiniteDifferences[i - 1][j];
				//System.out.print(tableOfFiniteDifferences[i][j]);
			}
			//System.out.println();
		}
	}
	public double GetDividedDifference(int k)
	{
		double sum = 0, mult = 1;
		for (int i = 0; i <= k; i++)
		{
			mult = 1;
			for (int j = 0; j <= k; j++)
			{
				if (i == j) continue;
				mult *= (X[i] - X[j]);
			}
			sum += F[i]/mult;
		}
		return sum;
	}

	public double NewtonsFormulaForUnequalIntervals(double x)
	{
		double differences[] = new double[size];
		for (int i = 0; i < size; i++)
			differences[i] = x - X[i];
		double sum = F[0], mult = 1;
		for (int i = 0; i < size; i++)
		{
			mult *= differences[i];
			sum += GetDividedDifference(i)*mult;
		}
		return sum;
	}
	public double NewtonsFirstFormula(double x)
	{
		if (size < 2)
		{
			System.out.println("ERROR: Size == 0. Newtons First Formula");
			return 0;
		}
		double h = X[1] - X[0];
		/*
		for (int i = 1; i < size; i++)
		{
			System.out.print(">" + (X[i] - X[i - 1]));
			if (X[i] - X[i - 1] != h)
			{
				System.out.println("ERROR: You used unequal intervals for Newtons First Formula");
				return 0;
			}
		}
		*/
		double t = (x - X[0]) / h;
		System.out.println("@" + t + "@");
		double sum = tableOfFiniteDifferences[0][0];
		double mult = 1;
		for (int i = 1; i < size; i++)
		{
			mult *= (t + i - 1)/i;
			sum += mult*tableOfFiniteDifferences[0][i];
			//System.out.println(mult + "|" + sum);
		}
		return sum;
	}
}