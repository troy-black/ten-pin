package troydblack.tenpin.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScoreCard {

  public static final String REGEX_PATTERN = "^((([0-9-]{2}|X|[0-9-]/)\\|){9})((([0-9-]{2})\\|\\|)|([0-9-]/\\|\\|([0-9-]|X))|(X\\|\\|([0-9-]{2}|[0-9-]/|XX)))$";

  private String scoreString;

  private Map<Integer, Integer> frameScores;

  public ScoreCard(String scoreString) {
    if (scoreString.matches(REGEX_PATTERN)) {
      this.scoreString = scoreString;
      char[] scoreChars = scrubString().toCharArray();
      frameScores = parseScoreCharsIntoFrameScores(scoreChars);
    }
  }

  public int getFinalScore() {
    return frameScores.values().stream().mapToInt(Integer::intValue).sum();
  }

  private String scrubString() {
    return scoreString.replaceAll("\\|", "")    // Remove Frame Parser Char (|)
        .replaceAll("-", "0");                  // Replace Misses Char (-)
  }

  private Map<Integer, Integer> parseScoreCharsIntoFrameScores(char[] chars) {
    Map<Integer, Integer> frames = new HashMap<>();
    int frame = 1;
    for (int c = 0; c < chars.length && frame <= 10; c = c + nextFrame(chars[c])) {
      frames.put(frame++,
          scoreFrame(Arrays.copyOfRange(chars, c, c + ballCount(chars[c], chars[c + 1]))));
    }
    return frames;
  }

  private int scoreFrame(char[] chars) {
    int score = 0;
    for (int c = 0; c < chars.length; c++) {
      if (chars[c] == 'X') {
        score += 10;
      } else if (chars[c] == '/') {
        score += 10 - Character.getNumericValue(chars[c - 1]);
      } else {
        score += Character.getNumericValue(chars[c]);
      }
    }
    return score;
  }

  private int nextFrame(char c) {
    if (c == 'X') {
      return 1;
    }
    return 2;
  }

  private int ballCount(char... chars) {
    if (chars[0] == 'X' || chars[1] == '/') {
      return 3;
    }
    return 2;
  }

}
