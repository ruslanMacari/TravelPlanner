package com.complexica.test.travelplanner;

import static com.complexica.test.travelplanner.trips.TripController.FORECASTS_ATTRIBUTE;
import static com.complexica.test.travelplanner.trips.TripController.TRIP_ATTRIBUTE;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.complexica.test.travelplanner.common.RedirectHandler;
import com.complexica.test.travelplanner.trips.TripController;
import com.complexica.test.travelplanner.trips.TripDto;
import com.complexica.test.travelplanner.trips.TripFacade;
import com.complexica.test.travelplanner.trips.weather.ForecastDto;
import com.complexica.test.travelplanner.trips.weather.ForecastDto.ForecastDtoBuilder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ExtendWith(MockitoExtension.class)
class TripControllerTest {

  public static final List<ForecastDto> FORECASTS = Arrays.asList(
      new ForecastDtoBuilder()
          .city("London")
          .country("UK")
          .temp(20)
          .clouds(50)
          .createForecastDto(),
      new ForecastDtoBuilder()
          .city("Moscow")
          .country("RU")
          .temp(15)
          .clouds(20)
          .createForecastDto());
  private TripController controller;

  @Mock
  private Model modelMock;
  @Mock
  private TripFacade tripFacadeMock;
  @Mock
  private RedirectHandler redirectHandlerMock;
  @Mock(lenient = true)
  private BindingResult errorsMock;
  @Mock
  private RedirectAttributes redirectAttributesMock;

  @BeforeEach
  public void setUp() {
    controller = new TripController(tripFacadeMock, redirectHandlerMock);
  }

  @Test
  void list_isNotRedirect_shouldReturnListAndAddTripDtoAndForecastsDto() {
    // given:
    given(tripFacadeMock.getAllForecasts()).willReturn(FORECASTS);
    given(redirectHandlerMock.isRedirect(modelMock)).willReturn(false);
    // when:
    String actual = controller.list(modelMock);
    // then:
    then(actual).isEqualTo("list");
    BDDMockito.then(modelMock)
        .should().addAttribute(eq(TRIP_ATTRIBUTE), refEq(new TripDto(LocalDate.now())));
    BDDMockito.then(modelMock)
        .should().addAttribute(eq(FORECASTS_ATTRIBUTE), refEq(FORECASTS));
    BDDMockito.then(modelMock).shouldHaveNoMoreInteractions();
  }

  @Test
  void list_isRedirect_shouldReturnListAndMergeAttributesFromFlashModel() {
    // given:
    given(tripFacadeMock.getAllForecasts()).willReturn(FORECASTS);
    given(redirectHandlerMock.isRedirect(modelMock)).willReturn(true);
    // when:
    String actual = controller.list(modelMock);
    // then:
    then(actual).isEqualTo("list");
    BDDMockito.then(modelMock)
        .should().addAttribute(eq(FORECASTS_ATTRIBUTE), refEq(FORECASTS));
    BDDMockito.then(modelMock).shouldHaveNoMoreInteractions();
  }

  @Test
  void add_givenHasErrors_shouldAddModelToRedirectAttributes() {
    //given:
    given(errorsMock.hasErrors()).willReturn(true);
    //when:
    String actual = controller.addTrip(new TripDto(LocalDate.now()), errorsMock,
        redirectAttributesMock, modelMock);
    //then:
    then(actual).isEqualTo("redirect:/trips");
    verify(redirectHandlerMock).addModelToRedirectAttributes(modelMock, redirectAttributesMock);
    BDDMockito.then(redirectHandlerMock).shouldHaveNoMoreInteractions();
  }

  @Test
  void add_givenHasNoErrors_shouldAddTrip() {
    //given:
    given(errorsMock.hasErrors()).willReturn(false);
    //when:
    TripDto tripDto = new TripDto(LocalDate.now());
    String actual = controller.addTrip(tripDto, errorsMock, redirectAttributesMock, modelMock);
    //then:
    then(actual).isEqualTo("redirect:/trips");
    BDDMockito.then(tripFacadeMock).should().addForecast(eq(tripDto));
  }
}
