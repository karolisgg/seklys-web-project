package lt.baltictalents.followeveryone.controller;

import lt.baltictalents.followeveryone.DAO.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

   @Autowired
   private APIService apiService;

   @GetMapping("/")
   public String loadMap(Model model) {
      model.addAttribute("devices", apiService.getAllDevices());
      return "home";
   }
}