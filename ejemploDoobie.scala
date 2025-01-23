import doobie._
import doobie.implicits._
import cats.effect.IO
import cats.effect.unsafe.implicits.global

object ejemploDoobie {
  def main(args: Array[String]): Unit = {
    // Definir configuración de la base de datos
    val xa = Transactor.fromDriverManager[IO](
      "com.mysql.cj.jdbc.Driver",
      "jdbc:mysql://localhost:3306/scala_test",
      "root",
      "kirby1150682860"
    )

    val query = sql"SELECT * FROM users".query[(Int, String, String)].to[List]

    val result = query.transact(xa).unsafeRunSync()

    result.foreach { case (id, name, email) =>
      println(s"ID: $id, Name: $name, Email: $email")
    }
  }
}