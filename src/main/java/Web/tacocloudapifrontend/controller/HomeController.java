package Web.tacocloudapifrontend.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import Web.tacocloudapifrontend.entity.IngradientEntity;

@Controller
public class HomeController {
	
   @GetMapping(path = "/taco")
   public ModelAndView tacoPage() {
	   ModelAndView mView = new ModelAndView("taco");
	   
	   RestTemplate restTemplate = new RestTemplate();
	   org.springframework.http.HttpHeaders headers = new HttpHeaders();
	   headers.add("Content-Type", "application/json");
	   headers.add("Accept", "*/*");
	   HttpEntity<IngradientEntity> requestEntity = new HttpEntity<>(null, headers);
	   ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8080/ingredients/getAll", HttpMethod.GET, requestEntity, Object.class);
	   mView.addObject("list",(List<IngradientEntity>)responseEntity.getBody());
	   return mView;
   }
}
