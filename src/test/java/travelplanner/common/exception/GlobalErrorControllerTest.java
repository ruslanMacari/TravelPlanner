package travelplanner.common.exception;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class GlobalErrorControllerTest {

  private GlobalErrorController globalErrorController;
  @Mock(lenient = true)
  private HttpServletRequest requestMock;
  @Mock
  private Model modelMock;

  @BeforeEach
  public void setUp() {
    globalErrorController = new GlobalErrorController();
  }

  @Test
  public void handleError_givenStatusIsNull_returnException() {
    // given:
    given(requestMock.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).willReturn(null);
    // when:
    String actual = globalErrorController.handleError(requestMock, modelMock);
    // then:
    then(actual).isEqualTo("exception");
    BDDMockito.then(modelMock).should().addAttribute("errorMessage", null);
    BDDMockito.then(modelMock).should().addAttribute("statusCode", null);
  }

  @Test
  public void handleError_givenStatusIs500_returnException() {
    // given:
    given(requestMock.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).willReturn(500);
    RuntimeException exception = new RuntimeException();
    given(requestMock.getAttribute(RequestDispatcher.ERROR_EXCEPTION)).willReturn(exception);
    // when:
    String actual = globalErrorController.handleError(requestMock, modelMock);
    // then:
    then(actual).isEqualTo("exception");
    BDDMockito.then(modelMock).should().addAttribute("errorMessage", exception);
    BDDMockito.then(modelMock).should().addAttribute("statusCode", 500);
  }

  @Test
  public void handleError_givenStatusIs404_returnResourceNotFound() {
    // given:
    given(requestMock.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).willReturn(404);
    // when:
    String actual = globalErrorController.handleError(requestMock, modelMock);
    // then:
    then(actual).isEqualTo("resource-not-found");
    BDDMockito.then(modelMock).should().addAttribute("statusCode", 404);
  }

}
