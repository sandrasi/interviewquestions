package sandrasi.interviewquestions.sudokuvalidator

import org.scalatest.{FunSpec, Matchers}

class SudokuValidatorTest extends FunSpec with Matchers {

  it("accepts valid sudoku solutions") {
    val solution = Array(
      Array(1, 2, 3, 4, 5, 6, 7, 8, 9),
      Array(4, 5, 6, 7, 8, 9, 1, 2, 3),
      Array(7, 8, 9, 1, 2, 3, 4, 5, 6),
      Array(2, 3, 4, 5, 6, 7, 8, 9, 1),
      Array(5, 6, 7, 8, 9, 1, 2, 3, 4),
      Array(8, 9, 1, 2, 3, 4, 5, 6, 7),
      Array(3, 4, 5, 6, 7, 8, 9, 1, 2),
      Array(6, 7, 8, 9, 1, 2, 3, 4, 5),
      Array(9, 1, 2, 3, 4, 5, 6, 7, 8)
    )
    SudokuValidator.isValidSolution(solution) shouldBe true
  }

  it("rejects invalid sudoku solutions") {
    val solution = Array(
      Array(1, 1, 1, 1, 1, 1, 1, 1, 1),
      Array(4, 5, 6, 7, 8, 9, 1, 2, 3),
      Array(7, 8, 9, 1, 2, 3, 4, 5, 6),
      Array(2, 3, 4, 5, 6, 7, 8, 9, 1),
      Array(5, 6, 7, 8, 9, 1, 2, 3, 4),
      Array(8, 9, 1, 2, 3, 4, 5, 6, 7),
      Array(3, 4, 5, 6, 7, 8, 9, 1, 2),
      Array(6, 7, 8, 9, 1, 2, 3, 4, 5),
      Array(9, 1, 2, 3, 4, 5, 6, 7, 8)
    )
    SudokuValidator.isValidSolution(solution) shouldBe false
  }
}
