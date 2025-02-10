import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class Test {
  @org.junit.Test
  public void testPow2() {
    System.out.println(Math.pow(1.0061363892207218, 0.0005));
    System.out.println(Math.pow(1.0000046943914231, 2000));
    System.out.println(ApfloatMath.pow(new Apfloat(1.0061363892207218, 80), new Apfloat(0.0005, 80)).doubleValue());
    System.out.println(ApfloatMath.pow(new Apfloat(1.0000046943914231, 80), new Apfloat(2000, 80)).doubleValue());
  }
}
