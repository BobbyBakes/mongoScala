package org.coordinates

/**
  * Created by deweirich on 3/9/17.
  */

import breeze.linalg.DenseMatrix
import org.coordinates.CoordinateCalculator._
import org.scalatest._

class CoordinateCalculatorTest extends FlatSpec with Matchers {

  it should "return a 3x3 matrix" in {

    val rect = Rectangle2D(Point2D(0.0, 0.0), Point2D(0.0, 1.0), Point2D(1.0, 1.0), Point2D(1.0, 0.0))

    val result = computeProjectionMatrix(rect, rect)

    result.rows shouldBe 3
    result.cols shouldBe 3
  }

  it should "return the identity matrix for a basic input" in {

    val rect = Rectangle2D(Point2D(0.0, 0.0), Point2D(0.0, 1.0), Point2D(1.0, 1.0), Point2D(1.0, 0.0))

    val result = computeProjectionMatrix(rect, rect)
    val expected = DenseMatrix.eye[Double](3)

    result shouldBe expected
  }

  it should "return the correct matrix for double sized rectangle" in {

    val rectMap = Rectangle2D(Point2D(0.0, 0.0), Point2D(0.0, 1.0), Point2D(1.0, 1.0), Point2D(1.0, 0.0))
    val rectImage = Rectangle2D(Point2D(0.0, 0.0), Point2D(0.0, 2.0), Point2D(2.0, 2.0), Point2D(2.0, 0.0))

    val result = computeProjectionMatrix(rectImage, rectMap)
    val expected = DenseMatrix(
      (0.5, 0.0, 0.0),
      (0.0, 0.5, 0.0),
      (0.0, 0.0, 1.0)
    )

    result shouldBe expected
  }

  it should "give reasonable results" in {

    val rectMap = Rectangle2D(Point2D(0.0, 0.0), Point2D(0.0, 1000.0), Point2D(1000.0, 1000.0), Point2D(1000.0, 0.0))
    val rectImage = Rectangle2D(Point2D(10.0, 10.0), Point2D(10.0, 320.0), Point2D(450.0, 380.0), Point2D(450.0, 80.0))

    val inputPoint = Point2D(100.0, 100.0)

    val expected = Point2D(199.26199261992625, 245.7564575645757)

    val result = calculateCoordinates(rectImage, rectMap, inputPoint)

    result shouldBe expected
  }

  it should "give reasonable results again" in {

    val rectMap = Rectangle2D(Point2D(100.0, 70.0), Point2D(86.0, 672.0), Point2D(894.0, 670.0), Point2D(894.0, 70.0))
    val rectImage = Rectangle2D(Point2D(414.0, 172.0), Point2D(224.0, 368.0), Point2D(520.0, 508.0), Point2D(718.0, 388.0))

    val inputPoint = Point2D(390.0, 280.0)

    val expected = Point2D(201.72533490430632,272.8695587764526)

    val result = calculateCoordinates(rectImage, rectMap, inputPoint)

    result shouldBe expected
  }
}
