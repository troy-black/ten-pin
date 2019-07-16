package troydblack.tenpin.controller;

import static troydblack.tenpin.model.ScoreCard.REGEX_PATTERN;

import java.util.logging.Logger;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import troydblack.tenpin.model.ScoreCard;

@Validated
@Controller
public class ScoreController {

  private static final Logger LOGGER = Logger.getLogger(ScoreController.class.getName());

  @GetMapping(path = "/")
  public @ResponseBody Integer getFinalScore(@Pattern(regexp = REGEX_PATTERN) @Valid @RequestParam String score) {
    ScoreCard scoreCard = new ScoreCard(score);
    LOGGER.info(() -> "scoreString={" + score + "}; finalScore=" + scoreCard.getFinalScore());
    return scoreCard.getFinalScore();
  }

}
