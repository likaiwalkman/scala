package asyncMysql

import com.github.mauricio.async.db.mysql.MySQLConnection
import com.github.mauricio.async.db.mysql.util.URLParser
import com.github.mauricio.async.db.util.ExecutorServiceUtils.CachedExecutionContext
import com.github.mauricio.async.db.{Connection, QueryResult, RowData}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object BasicExample {

  def main(args: Array[String]) {
    val configuration = URLParser.parse("jdbc:mysql://127.0.0.1:3306?user=root&password=root")
    val connection: Connection = new MySQLConnection(configuration)
    Await.result(connection.connect, 5 seconds)
    val future: Future[QueryResult] = connection.sendQuery("SELECT 0")
    val mapResult: Future[Any] = future.map(queryResult => queryResult.rows match {
      case Some(resultSet) => {
        val row: RowData = resultSet.head
        row(0)
      }
      case None => -1
    }
    )

    val result = Await.result(mapResult, 5 seconds)

    println(result)

    connection.disconnect

  }

}

