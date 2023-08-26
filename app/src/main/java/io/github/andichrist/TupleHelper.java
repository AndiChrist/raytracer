package io.github.andichrist;

public class TupleHelper {

  public static NTuple add(String name1, String name2, Class clazz) {
    return new TupleFactory().create(clazz.getSimpleName(),
        TupleCache.get(name1).getX() + TupleCache.get(name2).getX(),
        TupleCache.get(name1).getY() + TupleCache.get(name2).getY(),
        TupleCache.get(name1).getZ() + TupleCache.get(name2).getZ(),
        TupleCache.get(name1).getW() + TupleCache.get(name2).getW()
    );
  }

  public static NTuple add(NTuple p, NTuple v, Class clazz) {
    return new TupleFactory().create(clazz.getSimpleName(),
        p.getX() + v.getX(),
        p.getY() + v.getY(),
        p.getZ() + v.getZ(),
        p.getW() + v.getW()
    );
  }

  public static NTuple subtract(String name1, String name2, Class clazz) {
    return new TupleFactory().create(clazz.getSimpleName(),
        TupleCache.get(name1).getX() - TupleCache.get(name2).getX(),
        TupleCache.get(name1).getY() - TupleCache.get(name2).getY(),
        TupleCache.get(name1).getZ() - TupleCache.get(name2).getZ(),
        TupleCache.get(name1).getW() - TupleCache.get(name2).getW()
    );
  }

  public static NTuple subtract(NTuple p, NTuple v, Class clazz) {
    return new TupleFactory().create(clazz.getSimpleName(),
        p.getX() - v.getX(),
        p.getY() - v.getY(),
        p.getZ() - v.getZ(),
        p.getW() - v.getW()
    );
  }

  public static NTuple multiply(double arg1, NTuple a, Class clazz) {
    return new TupleFactory().create(clazz.getSimpleName(),
        a.getX() * arg1,
        a.getY() * arg1,
        a.getZ() * arg1,
        a.getW() * arg1
    );
  }

  // Hadamard product (or Schur product)
  public static NTuple multiply(NTuple a, NTuple b, Class clazz) {
    return new TupleFactory().create(clazz.getSimpleName(),
        a.getX() * b.getX(),
        a.getY() * b.getY(),
        a.getZ() * b.getZ(),
        a.getW() * b.getW()
    );
  }

  public static NTuple divide(double arg0, NTuple a) {
    return new Tuple(
        a.getX() / arg0,
        a.getY() / arg0,
        a.getZ() / arg0,
        a.getW() / arg0
    );
  }

  public static double magnitude(NTuple v) {
    return Math.sqrt(
        v.getX() * v.getX() +
        v.getY() * v.getY() +
        v.getZ() * v.getZ() +
        v.getW() * v.getW()
    );
  }

  public static NTuple normalize(NTuple a, Class clazz) {
    return new TupleFactory().create(clazz.getSimpleName(),
        a.getX() / magnitude(a),
        a.getY() / magnitude(a),
        a.getZ() / magnitude(a),
        a.getW() / magnitude(a)
    );
  }

  public static double dot(NTuple a, NTuple b) {
    return a.getX() * b.getX() +
        a.getY() * b.getY() +
        a.getZ() * b.getZ() +
        a.getW() * b.getW();
  }

  public static Tuple cross(NTuple a, NTuple b) {
    return new Tuple(
        a.getY() * b.getZ() - a.getZ() * b.getY(),
        a.getZ() * b.getX() - a.getX() * b.getZ(),
        a.getX() * b.getY() - a.getY() * b.getX(),
        0
    );
  }
}
