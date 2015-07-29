package sandrasi.interviewquestions.skyline

import sandrasi.interviewquestions.skyline.Skyline.Point

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Skyline {

  def apply(buildings: Building*): Skyline = {
    def toSkylinePoints(from: Int, to: Int): List[Point] =
      if (to - from == 1) List(Point(buildings(from).left, buildings(from).height), Point(buildings(from).right, 0))
      else {
        val middle = (from + to) / 2
        mergeSkylinePoints(toSkylinePoints(from, middle), toSkylinePoints(middle, to), 0, 0, 0, ListBuffer.empty[Point])
      }

    new Skyline(if (buildings.length == 0) List.empty else toSkylinePoints(0, buildings.length))
  }

  @tailrec
  private def mergeSkylinePoints(
    points1: List[Point],
    points2: List[Point],
    previousHeight1: Int,
    previousHeight2: Int,
    currentHeight: Int,
    result: ListBuffer[Point]
  ): List[Point] =
    if (points1.isEmpty) (result ++= points2).toList
    else if (points2.isEmpty) (result ++= points1).toList
    else {
      val firstPoint1 = points1.head
      val firstPoint2 = points2.head
      val height1 = if (firstPoint1.x <= firstPoint2.x) firstPoint1.height else previousHeight1
      val height2 = if (firstPoint2.x <= firstPoint1.x) firstPoint2.height else previousHeight2
      val maxHeight = math.max(height1, height2)
      val restOfPoints1 = if (firstPoint1.x <= firstPoint2.x) points1.tail else points1
      val restOfPoints2 = if (firstPoint2.x <= firstPoint1.x) points2.tail else points2
      mergeSkylinePoints(
        restOfPoints1,
        restOfPoints2,
        height1,
        height2,
        maxHeight,
        if (maxHeight != currentHeight) result += Point(math.min(firstPoint1.x, firstPoint2.x), maxHeight) else result
      )
    }

  case class Point(x: Int, height: Int)
}
class Skyline private (val points: List[Point])

case class Building(left: Int, right: Int, height: Int) {

  require(left <= right, "left must not be greater than right")
  require(height >= 0, "height must be non-negative")
}
