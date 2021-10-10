package com.complexica.test.travelplanner.common;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class RedirectHandler {

  public static final String FLASH_MODEL_ATTRIBUTE_NAME = RedirectHandler.class + ".flashModel";

  public void addModelToRedirectAttributes(Model model, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute(FLASH_MODEL_ATTRIBUTE_NAME, model);
  }

  public boolean isRedirect(Model model) {
    Object flashModel = model.asMap().get(FLASH_MODEL_ATTRIBUTE_NAME);
    if (flashModel == null) {
      return false;
    } else {
      model.mergeAttributes(((Model) flashModel).asMap());
      return true;
    }
  }

}
