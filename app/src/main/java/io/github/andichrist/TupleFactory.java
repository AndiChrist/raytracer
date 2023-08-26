package io.github.andichrist;

public class TupleFactory implements AbstractFactory<NTuple> {

  @Override
  public NTuple create(String tupleType, double x, double y, double z, double w) {
    return switch (tupleType) {
      case "Point" -> new Point(x, y, z);
      case "Vector" -> new Vector(x, y, z);
      case "Tuple" -> new Tuple(x, y, z, w);
      case "Color" -> new Color(x, y, z); // RGB
      default -> null;
    };
  }
}
