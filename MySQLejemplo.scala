import java.sql.{Connection, DriverManager, ResultSet}

object MySQLejemplo {
  def main(args: Array[String]): Unit = {
    val url = "jdbc:mysql://localhost:3306/scala_test"
    val user = "root"
    val password = "kirby1150682860"

    var connection: Connection = null

    try {
      Class.forName("com.mysql.cj.jdbc.Driver")

      connection = DriverManager.getConnection(url, user, password)
      println("Conexión exitosa.")

      val statement = connection.createStatement()
      val resultSet: ResultSet = statement.executeQuery("SELECT * FROM users")

      while (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val email = resultSet.getString("email")
        println(s"ID: $id, Nombre: $name, Email: $email")
      }

    } catch {
      case e: Exception => e.printStackTrace()
    } finally {

      if (connection != null) connection.close()
    }
  }
}
