package raytracer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TupleTest {
  @Test
  public void hasTuple() {
    Tuple a = Tuple.create(10);
    assertThat(a.v()).isEqualTo(10);
  }

  @Test
  public void addTuple() {
    Tuple a = Tuple.create(2);
    Tuple b = Tuple.create(4);
    assertThat(a.add(b).v()).isEqualTo(6);
  }

  @Test
  public void isTupleEqual() {
    Tuple a = Tuple.create(2);
    Tuple b = Tuple.create(2);
    assertThat(a).isEqualTo(b);
  }
}
