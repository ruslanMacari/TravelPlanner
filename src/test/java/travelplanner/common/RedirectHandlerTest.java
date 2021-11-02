package travelplanner.common;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ExtendWith(MockitoExtension.class)
class RedirectHandlerTest {

  private RedirectHandler redirectHandler;

  @Mock
  private Model modelMock;
  @Mock
  private RedirectAttributes redirectAttributesMock;

  @BeforeEach
  public void setUp() {
    redirectHandler = new RedirectHandler();
  }

  @Test
  void addModelToRedirectAttributes_shouldAddModelToRedirectAttributesInFlashAttribute() {
    //when:
    redirectHandler.addModelToRedirectAttributes(modelMock, redirectAttributesMock);
    //then:
    verify(redirectAttributesMock).addFlashAttribute(RedirectHandler.FLASH_MODEL_ATTRIBUTE_NAME,
        modelMock);
    BDDMockito.then(modelMock).shouldHaveNoMoreInteractions();
  }

  @Test
  void isRedirect_givenFlashModelAttributePresent_shouldReturnTrueAndMergeToModel() {
    //given:
    Map<String, Object> map = new HashMap<>();
    Model flashModel = mock(Model.class);
    map.put(RedirectHandler.FLASH_MODEL_ATTRIBUTE_NAME, flashModel);
    given(modelMock.asMap()).willReturn(map);
    //when:
    boolean actual = redirectHandler.isRedirect(modelMock);
    //then:
    then(actual).isEqualTo(true);
    BDDMockito.then(modelMock).should().mergeAttributes(flashModel.asMap());
    BDDMockito.then(modelMock).shouldHaveNoMoreInteractions();
  }

  @Test
  void isRedirect_givenFlashModelAttributeNull_shouldReturnFalse() {
    //given:
    Map<String, Object> map = new HashMap<>();
    given(modelMock.asMap()).willReturn(map);
    //when:
    boolean actual = redirectHandler.isRedirect(modelMock);
    //then:
    then(actual).isEqualTo(false);
    BDDMockito.then(modelMock).shouldHaveNoMoreInteractions();
  }
}
