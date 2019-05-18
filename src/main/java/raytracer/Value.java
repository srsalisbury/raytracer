package raytracer;

public class Value {
  public int v;

  public Value(int v) {
    this.v = v;
  }

  public Value add(Value x) {
    return new Value(v + x.v);
  }

  @Override
  public boolean equals(Object obj) {
  	if (obj == null) {
  		return false;
  	}
  	if (obj == this) {
  		return true;
  	}
  	if (!(obj instanceof Value)) {
  		return false;
  	}
  	Value other = (Value) obj;
  	return this.v == other.v;
  }
}
