

object Main extends App {

  import java.nio.ByteOrder;

  if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
    println("BigEndian")
  } else {
    println("LittleEndian")
  }
}