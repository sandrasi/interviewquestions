package sandrasi.interviewquestions.diff

import org.scalatest.{FunSpec, Matchers}

class DiffTest extends FunSpec with Matchers {

  it("returns the lines concatenated if all the lines are the same") {
    Diff(Array("line1", "line2"), Array("line1", "line2")) should equal(" line1\n line2\n")
  }

  it("marks all the lines as missing and new when the lines are different") {
    Diff(Array("line1", "line2"), Array("line3", "line4")) should equal("-line1\n-line2\n+line3\n+line4\n")
  }

  it("marks the missing lines with '-' and the new lines with '+'") {
    Diff(Array("line1", "line2"), Array("line1", "line3")) should equal(" line1\n-line2\n+line3\n")
  }
}
