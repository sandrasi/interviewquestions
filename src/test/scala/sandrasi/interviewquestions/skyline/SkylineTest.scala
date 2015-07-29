package sandrasi.interviewquestions.skyline

import org.scalatest.{FunSpec, Matchers}
import sandrasi.interviewquestions.skyline.Skyline.Point

class SkylineTest extends FunSpec with Matchers {

  it("creates an empty skyline") {
    Skyline().points shouldBe empty
  }

  it("creates a skyline from a single building") {
    Skyline(Building(1, 5, 11)).points should equal(Point(1, 11) :: Point(5, 0) :: Nil)
  }

  it("creates a skyline from many buildings") {
    Skyline(
      Building(1, 5, 11), Building(2, 7, 6), Building(3, 9, 13), Building(12, 16, 7), Building(14, 25, 3),
      Building(19, 22, 18), Building(23, 29, 13), Building(24, 28, 4)
    ).points should equal(
      Point(1, 11) :: Point(3, 13) :: Point(9, 0) :: Point(12, 7) :: Point(16, 3) :: Point(19, 18) :: Point(22, 3) ::
        Point(23, 13) :: Point(29, 0) :: Nil
    )
  }
}
