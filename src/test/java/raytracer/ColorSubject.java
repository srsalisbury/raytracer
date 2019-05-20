package raytracer;

import static com.google.common.truth.Truth.assertAbout;
import static raytracer.Testing.EPSILON;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class ColorSubject extends Subject<ColorSubject, Color> {

  public static ColorSubject assertThat(@NullableDecl Color color) {
    return assertAbout(COLOR_SUBJECT_FACTORY).that(color);
  }

  public static Subject.Factory<ColorSubject, Color> colors() {
    return COLOR_SUBJECT_FACTORY;
  }

  private static final Subject.Factory<ColorSubject, Color> COLOR_SUBJECT_FACTORY =
      ColorSubject::new;

  private ColorSubject(FailureMetadata failureMetadata, @NullableDecl Color subject) {
    super(failureMetadata, subject);
  }

  public void isApproximatelyEqualTo(Color other) {
    isApproximatelyEqualTo(EPSILON, other);
  }

  public void isApproximatelyEqualTo(double epsilon, Color other) {
    check("r()").that(actual().r()).isWithin(epsilon).of(other.r());
    check("g()").that(actual().g()).isWithin(epsilon).of(other.g());
    check("b()").that(actual().b()).isWithin(epsilon).of(other.b());
  }
}
