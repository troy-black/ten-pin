package troydblack.tenpin.model;

import static troydblack.tenpin.model.ScoreCard.REGEX_PATTERN;

import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreCardTest {

  private static final Logger LOGGER = Logger.getLogger(ScoreCardTest.class.getName());

  @Test
  public void testValidScoreStrings() {
    testScoreString(150, "5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5");
    testScoreString(300, "X|X|X|X|X|X|X|X|X|X||XX");
  }

  @Test
  public void testBadScoreString() {
    String scoreString = "this|is|a|test||ok";
    try {
      testScoreString(0, scoreString);
    } catch (Exception e) {
      LOGGER.info(() -> "scoreString={" + scoreString + "}; getFinalScore.score: must match \"" + REGEX_PATTERN + "\"");
      return;
    }
    Assert.fail();
  }

  private void testScoreString(int expectedFinalScore, String scoreString) {
    ScoreCard scoreCard = new ScoreCard(scoreString);
    LOGGER.info(() -> "scoreString={" + scoreString + "}; finalScore=" + scoreCard.getFinalScore());
    Assert.assertEquals(expectedFinalScore, scoreCard.getFinalScore());
  }

}