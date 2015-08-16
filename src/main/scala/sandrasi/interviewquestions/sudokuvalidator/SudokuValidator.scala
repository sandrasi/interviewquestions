package sandrasi.interviewquestions.sudokuvalidator

import scala.collection.mutable

object SudokuValidator {

  def isValidSolution(grid: Array[Array[Int]]): Boolean = {
    val rows = Array.ofDim[mutable.Set[Int]](9)
    val columns = Array.fill[mutable.Set[Int]](9)(mutable.Set.empty)
    val boxes = Array.fill[mutable.Set[Int]](9)(mutable.Set.empty)

    for (i <- 0 until grid.length) {
      rows(i) = mutable.Set(grid(i):_*)
      for (j <- 0 until grid(i).length) {
        columns(j) += grid(i)(j)
        boxes((i / 3) * 3 + j / 3) += grid(i)(j)
      }
    }
    rows.forall(isValid) && columns.forall(isValid) && boxes.forall(isValid)
  }

  private def isValid(numbers: mutable.Set[Int]) = numbers.size == 9 && numbers.sum == 45
}
