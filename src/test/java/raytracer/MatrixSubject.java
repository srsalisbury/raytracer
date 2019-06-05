package raytracer;

import static com.google.common.truth.Truth.assertAbout;
import static raytracer.Testing.EPSILON;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class MatrixSubject extends Subject<MatrixSubject, Matrix> {

  public static MatrixSubject assertThat(@NullableDecl Matrix matrix) {
    return assertAbout(MATRIX_SUBJECT_FACTORY).that(matrix);
  }

  public static Subject.Factory<MatrixSubject, Matrix> matrixs() {
    return MATRIX_SUBJECT_FACTORY;
  }

  private static final Subject.Factory<MatrixSubject, Matrix> MATRIX_SUBJECT_FACTORY =
      MatrixSubject::new;

  private MatrixSubject(FailureMetadata failureMetadata, @NullableDecl Matrix subject) {
    super(failureMetadata, subject);
  }

  public void isApproximatelyEqualTo(Matrix other) {
    isApproximatelyEqualTo(EPSILON, other);
  }

  public void isApproximatelyEqualTo(double epsilon, Matrix other) {
    for (int row = 0; row < actual().numRows(); row++) {
      for (int col = 0; col < actual().numCols(); col++) {
        check(String.format("value at %d, %d", row, col))
            .that(actual().get(row, col))
            .isWithin(epsilon)
            .of(other.get(row, col));
      }
    }
  }
}
