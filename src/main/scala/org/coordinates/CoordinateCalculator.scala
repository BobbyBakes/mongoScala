package org.coordinates

/**
  * Created by deweirich on 3/9/17.
  */
import breeze.linalg._

    object CoordinateCalculator {

      case class Point2D(x: Double, y: Double)

      case class Rectangle2D(bottomLeft: Point2D, topLeft: Point2D, topRight: Point2D, bottomRight: Point2D)

      def calculateCoordinates(rectImage: Rectangle2D, rectMap: Rectangle2D, point: Point2D) : Point2D = {
        import CoordinateUtil.{point2DToHomogeneousVector, vectorToPoint2D}

        val v = point2DToHomogeneousVector(point)

        val A = computeProjectionMatrix(rectImage, rectMap)

        val result = A * v

    vectorToPoint2D(result)
  }

  def computeProjectionMatrix(rectIn: Rectangle2D, rectOut: Rectangle2D) : Matrix[Double] = {
    import CoordinateUtil._

    val A = createBigMatrix(rectIn, rectOut)
    val v = rectangleToVector(rectOut)

    val x : DenseVector[Double] = A \ v // Solve the equation Ax = v

    vector8To3x3Matrix(x)
  }
}

object CoordinateUtil {
  import CoordinateCalculator.{Point2D, Rectangle2D}

  def createBigMatrix(rectIn: Rectangle2D, rectOut: Rectangle2D) : DenseMatrix[Double] = {
    val x0 = rectIn.bottomLeft.x
    val y0 = rectIn.bottomLeft.y
    val x1 = rectIn.topLeft.x
    val y1 = rectIn.topLeft.y
    val x2 = rectIn.topRight.x
    val y2 = rectIn.topRight.y
    val x3 = rectIn.bottomRight.x
    val y3 = rectIn.bottomRight.y

    val u0 = rectOut.bottomLeft.x
    val v0 = rectOut.bottomLeft.y
    val u1 = rectOut.topLeft.x
    val v1 = rectOut.topLeft.y
    val u2 = rectOut.topRight.x
    val v2 = rectOut.topRight.y
    val u3 = rectOut.bottomRight.x
    val v3 = rectOut.bottomRight.y

    DenseMatrix(
      (x0,  y0,  1.0, 0.0, 0.0, 0.0, -x0*u0, -y0*u0),
      (0.0, 0.0, 0.0, x0,  y0,  1.0, -x0*v0, -y0*v0),
      (x1,  y1,  1.0, 0.0, 0.0, 0.0, -x1*u1, -y1*u1),
      (0.0, 0.0, 0.0, x1,  y1,  1.0, -x1*v1, -y1*v1),
      (x2,  y2,  1.0, 0.0, 0.0, 0.0, -x2*u2, -y2*u2),
      (0.0, 0.0, 0.0, x2,  y2,  1.0, -x2*v2, -y2*v2),
      (x3,  y3,  1.0, 0.0, 0.0, 0.0, -x3*u3, -y3*u3),
      (0.0, 0.0, 0.0, x3,  y3,  1.0, -x3*v3, -y3*v3)
    )
  }

  def rectangleToVector(rect: Rectangle2D) : DenseVector[Double] = DenseVector(
    rect.bottomLeft.x,  rect.bottomLeft.y,
    rect.topLeft.x,     rect.topLeft.y,
    rect.topRight.x,    rect.topRight.y,
    rect.bottomRight.x, rect.bottomRight.y)

  def vector8To3x3Matrix(v: DenseVector[Double]) : DenseMatrix[Double] = {
    val mat = DenseMatrix(DenseVector.vertcat(v, DenseVector(1.0)))

    mat.reshape(3, 3).t
  }

  def point2DToHomogeneousVector(p: CoordinateCalculator.Point2D) : DenseVector[Double] = DenseVector(p.x, p.y, 1.0)

  def vectorToPoint2D(v: Vector[Double]) : Point2D = Point2D(v(0)/v(2), v(1)/v(2))

}