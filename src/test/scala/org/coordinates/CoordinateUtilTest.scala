package org.coordinates

import breeze.linalg._
import org.coordinates.CoordinateCalculator.{Point2D, Rectangle2D}
import org.coordinates.CoordinateUtil._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by deweirich on 3/9/17.
  */

class CoordinateUtilTest extends FlatSpec with Matchers {

  it should "calculate the right big matrix" in {

    val rectIn = Rectangle2D(Point2D(1.0, 2.0), Point2D(3.0, 4.0), Point2D(5.0, 6.0), Point2D(7.0, 8.0))
    val rectOut = Rectangle2D(Point2D(9.0, 10.0), Point2D(11.0, 12.0), Point2D(13.0, 14.0), Point2D(15.0, 16.0))

    val expected = DenseMatrix(
      (1.0, 2.0, 1.0, 0.0, 0.0, 0.0,  -9.0, -18.0),
      (0.0, 0.0, 0.0, 1.0, 2.0, 1.0, -10.0, -20.0),
      (3.0, 4.0, 1.0, 0.0, 0.0, 0.0, -33.0, -44.0),
      (0.0, 0.0, 0.0, 3.0, 4.0, 1.0, -36.0, -48.0),
      (5.0, 6.0, 1.0, 0.0, 0.0, 0.0, -65.0, -78.0),
      (0.0, 0.0, 0.0, 5.0, 6.0, 1.0, -70.0, -84.0),
      (7.0, 8.0, 1.0, 0.0, 0.0, 0.0, -105.0, -120.0),
      (0.0, 0.0, 0.0, 7.0, 8.0, 1.0, -112.0, -128.0)
    )

    val result = createBigMatrix(rectIn, rectOut)

    result shouldBe expected
  }

  it should "give the correct 3x3 matrix from a vector" in {
    val input = DenseVector(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)

    val expected = DenseMatrix((1.0, 2.0, 3.0), (4.0, 5.0, 6.0), (7.0, 8.0, 1.0))

    val result = vector8To3x3Matrix(input)

    result shouldBe expected
  }

}
