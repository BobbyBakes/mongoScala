package org

/**
  * Created by deweirich on 3/10/17.
  */
object Main {

  def main(args: Array[String]): Unit = {
    import org.db.DatabaseWrapper.connectToServer


    connectToServer()

  }

}
