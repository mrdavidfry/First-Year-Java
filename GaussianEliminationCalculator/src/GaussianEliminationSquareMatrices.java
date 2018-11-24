import java.util.Arrays;

public class GaussianEliminationSquareMatrices {

  public static final double DEFAULT_TOLERANCE = Math.pow(10, -10);
  private final double tolerance;

  public GaussianEliminationSquareMatrices() {
    this(GaussianEliminationSquareMatrices.DEFAULT_TOLERANCE);
  }

  public GaussianEliminationSquareMatrices(double tolerance) {
    this.tolerance = tolerance;
  }

  private static void swapRows(double[][] matrix, int row1, int row2) {
    double[] temp = matrix[row1];
    matrix[row1] = matrix[row2];
    matrix[row2] = temp;
  }

  // PREs: A and b contain n x n and n elements, respectively; n>0
  public double[] solve(double[][] A, double[] b) {
    assert (A != null && b != null) :
        "The coefficients matrix and known " +
            "terms of the system of equations should not be null";
    assert (A.length > 0) :
        "The coefficients matrix should not be empty";
    assert (A.length == A[0].length) :
        "The coefficients matrix should be square";
    assert (A.length == b.length) :
        "The coefficients matrix and known terms " +
            "vector should have compatible sizes";

    double[][] augmentedMatrix = augmentedMatrix(A, b);
    double[][] rowEchelonForm = reducedRowEchelonForm(augmentedMatrix);

    if (rowEchelonForm != null) {
      double[] result = backSubstitution(rowEchelonForm);
      return result;
    } else {
      return null;
    }
  }

  protected double[][] augmentedMatrix(final double[][] A, final double[] b) {
    int dimension = b.length;
    double[][] augMatrix = new double[dimension][dimension + 1];
    for (int i = 0; i < dimension; i++) {
      System.arraycopy(A[i], 0, augMatrix[i], 0, A.length);
    }
    for (int i = 0; i < dimension; i++) {
      augMatrix[i][dimension] = b[i];
    }
    return augMatrix;
  }

  protected int findNextRowWithLargestElementInCol(double[][] augmentedMatrix, int pivot) {
    int dimension = augmentedMatrix.length;
    double maxValue = augmentedMatrix[0][pivot];
    int rowIndex = pivot;
    double current;
    for (int i = pivot; i < dimension; i++) {
      current = augmentedMatrix[i][pivot];
      if (current > maxValue) {
        maxValue = current;
        rowIndex = i;
      }
    }
    return rowIndex;
  }

  protected double[][] reducedRowEchelonForm(double[][] augmentedMatrix) {
    assert augmentedMatrix != null :
        "The augmented matrix should not be null";
    assert augmentedMatrix.length > 0 :
        "The augmented matrix should not be empty";
    assert augmentedMatrix[0].length == augmentedMatrix.length + 1 :
        "The number of columns in the augmented matrix " +
            "should be the number of rows + 1";

    int numRows = augmentedMatrix.length;
    for (int pivot = 0; pivot < numRows; pivot++) {
      int indexOfLargestRow
          = findNextRowWithLargestElementInCol(augmentedMatrix, pivot);
      swapRows(augmentedMatrix, pivot, indexOfLargestRow);
      if (isAlmostZero(augmentedMatrix[pivot][pivot])) {
        return null; // The system has no solutions
      }
      reduceRow(augmentedMatrix, pivot);
    }
    return augmentedMatrix;
  }

  protected void reduceRow(double[][] augmentedMatrix, int pivot) {
    assert augmentedMatrix != null :
        "The augmented matrix should not be null";
    assert augmentedMatrix.length > 0 :
        "The augmented matrix should not be empty";
    assert augmentedMatrix[0].length == augmentedMatrix.length + 1 :
        "The number of columns in the augmented " +
            "matrix should be the number of rows + 1";
    assert pivot >= 0 && pivot < augmentedMatrix[0].length :
        "The pivot element should be between 0 and the number " +
            "of columns of the augmented matrix";

    for (int row = pivot + 1; row < augmentedMatrix.length; row++) {
      double coefficient
          = augmentedMatrix[row][pivot] / augmentedMatrix[pivot][pivot];
      for (int col = pivot; col < augmentedMatrix[0].length; col++) {
        augmentedMatrix[row][col] -= coefficient * augmentedMatrix[pivot][col];
      }
    }
  }

  protected double[] backSubstitution(double[][] augmentedSquareMatrix) {
    int numRows = augmentedSquareMatrix.length;
    double[] solution = new double[numRows];
    for (int row = numRows - 1; row >= 0; row--) {
      double[] multiply = matrixVectorMultiply(augmentedSquareMatrix, solution);
      double rowMult = multiply[row];
      solution[row] =
          (augmentedSquareMatrix[row][numRows] - rowMult) / augmentedSquareMatrix[row][row];
    }
    return solution;
  }

  private double[][] copyOf(double[][] matrix) {
    double[][] result = new double[matrix.length][matrix[0].length];
    for (int row = 0; row < matrix.length; row++) {
      result[row] = Arrays.copyOf(matrix[row],
          matrix[row].length);
    }
    return result;
  }

  private boolean isAlmostZero(double value) {
    return Math.abs(value) < this.tolerance;
  }

  /********************* For your convenience *******************/
  //Check if A.x = b
  public boolean checkSolution(double[][] A, double[] b, double[] x) {
    double[] product = matrixVectorMultiply(A, x);
    double sum = 0;
    for (int i = 0; i < product.length; i++) {
      sum += Math.abs(product[i] - b[i]);
    }
    return sum <= tolerance;
  }

  // Compute the scalar product A.v, with v assumed to be a column vector
  public double[] matrixVectorMultiply(double[][] A, double[] v) {
    double[] result = new double[v.length];
    for (int row = 0; row < A.length; row++) {
      double sum = 0;
      for (int i = 0; i < result.length; i++) {
        sum += A[row][i] * v[i];
      }
      result[row] = sum;
    }
    return result;
  }

}