package com.complexica.test.travelplanner.trips;

import com.complexica.test.travelplanner.common.RedirectHandler;
import java.time.LocalDate;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/trips")
public class TripController {

  public static final String TRIP_ATTRIBUTE = "trip";
  public static final String FORECASTS_ATTRIBUTE = "forecasts";
  private static final Logger logger = LoggerFactory.getLogger(TripController.class);
  private final TripFacade tripFacade;
  private final RedirectHandler redirectHandler;


  public TripController(TripFacade tripFacade, RedirectHandler redirectHandler) {
    this.tripFacade = tripFacade;
    this.redirectHandler = redirectHandler;
  }

  @GetMapping
  public String list(Model model) {
    if (!redirectHandler.isRedirect(model)) {
      model.addAttribute(TRIP_ATTRIBUTE, new TripDto(LocalDate.now()));
    }
    model.addAttribute(FORECASTS_ATTRIBUTE, tripFacade.getForecasts());
    return "list";
  }

  @PostMapping("/add")
  public String addTrip(@Valid @ModelAttribute(TRIP_ATTRIBUTE) TripDto tripDto,
      BindingResult errors,
      RedirectAttributes redirectAttributes,
      Model model) {
    if (errors.hasErrors()) {
      logger.error("Attribute: '{}' ({}) contains errors: {}", TRIP_ATTRIBUTE, errors.getTarget(),
          errors.getAllErrors());
      redirectHandler.addModelToRedirectAttributes(model, redirectAttributes);
      return "redirect:/trips";
    }
    //tripFacade.add(tripDto);
    return "redirect:/trips";
  }

}
