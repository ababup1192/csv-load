package example

import java.io.{File, FileInputStream, InputStream, InputStreamReader}
import java.nio.charset.StandardCharsets

import com.github.tototoshi.csv._

object Hello extends App {
  val file = new File("src/main/resources/test.csv")
  val inputStream: InputStream = new FileInputStream(file)

  val csv =
    CSVReader.open(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
  val result = csv.toStream.foldLeft(Seq[String]()) {
    case (z, line) =>
      line match {
        case List(a: String, b: String, c: String, d: String) =>
          s"$a$b$c$d" +: z
        // CSVのフォーマット崩れてる
        case _ => z
      }
  }

  println(result.mkString("\n"))
}
