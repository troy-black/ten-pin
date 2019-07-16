package troydblack.tenpin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScoreControllerTest {

  private static final Logger LOGGER = Logger.getLogger(ScoreControllerTest.class.getName());

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testValidScoreStrings() throws Exception {
    testScoreString(90, "9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||");
    testScoreString(167, "X|7/|9-|X|-8|8/|-6|X|X|X||81");
  }

  @Test
  public void testBadScoreString() {
    String scoreString = "/5|5/|5/|5/|5/5/|5/|5/|5K|5/||5";
    try {
      testScoreString(0, scoreString);
    } catch (Exception e) {
      LOGGER.info(() -> "scoreString={" + scoreString + "}; " + e.getCause().getMessage());
      return;
    }
    Assert.fail();
  }

  private void testScoreString(Integer expectedFinalScore, String scoreString) throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/").param("score", scoreString)).andReturn();
    Assert.assertEquals(expectedFinalScore.toString(), mvcResult.getResponse().getContentAsString());
  }


}