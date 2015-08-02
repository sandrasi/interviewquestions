package sandrasi.interviewquestions.diff

import scala.collection.mutable.ListBuffer

object Diff {

  def apply(lines1: Array[String], lines2: Array[String]): String = {
    val sequences = commonContinuousSequences(lines1, 0, lines1.length, lines2, 0, lines2.length) += SequenceMatch(lines1.length, lines2.length, 0)
    val (_, _, result) = sequences.foldLeft(0, 0, new StringBuilder()) { case ((from1, from2, sb), ccs) =>
      for (i <- from1 until ccs.startIndex1) sb ++= s"-${lines1(i)}\n"
      for (i <- from2 until ccs.startIndex2) sb ++= s"+${lines2(i)}\n"
      for (i <- ccs.startIndex1 until ccs.startIndex1 + ccs.length) sb ++= s" ${lines1(i)}\n"
      (ccs.startIndex1 + ccs.length, ccs.startIndex2 + ccs.length, sb)
    }
    result.toString()
  }

  private def commonContinuousSequences(lines1: Array[String], from1: Int, to1: Int, lines2: Array[String], from2: Int, to2: Int): ListBuffer[SequenceMatch] = {
    val sequenceMatch = longestCommonContinuousSequence(lines1, from1, to1, lines2, from2, to2)
    if (sequenceMatch.length == 0) ListBuffer.empty[SequenceMatch]
    else
      commonContinuousSequences(
        lines1, from1, sequenceMatch.startIndex1, lines2, to1, sequenceMatch.startIndex2
      ) ++=: ListBuffer[SequenceMatch](sequenceMatch) ++= commonContinuousSequences(
        lines1, sequenceMatch.startIndex1 + sequenceMatch.length, to1, lines2, sequenceMatch.startIndex2 + sequenceMatch.length, to2
      )
  }

  private def longestCommonContinuousSequence(lines1: Array[String], from1: Int, to1: Int, lines2: Array[String], from2: Int, to2: Int): SequenceMatch = {
    var memo = Array.ofDim[Int](lines2.length + 1)
    var startIdx1 = 0
    var startIdx2 = 0
    var maxLength = 0
    for (i <- from1 until to1) {
      val currentMemo = Array.ofDim[Int](lines2.length + 1)
      for (j <- from2 until to2) {
        if (lines1(i) == lines2(j)) {
          currentMemo(j + 1) = memo(j) + 1
          if (currentMemo(j + 1) > maxLength) {
            maxLength = currentMemo(j + 1)
            startIdx1 = i - maxLength + 1
            startIdx2 = j - maxLength + 1
          }
        }
      }
      memo = currentMemo
    }
    SequenceMatch(startIdx1, startIdx2, maxLength)
  }

  private case class SequenceMatch(startIndex1: Int, startIndex2: Int, length: Int)
}
