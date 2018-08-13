package com.sorbSoft.CabAcademie.Controladores;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
//
//	@Autowired
//	FacebookProvider facebookProvider;
//
//	@Autowired
//	GoogleProvider googleProvider;
//
//	@Autowired
//	LinkedInProvider linkedInProvider;
//
//	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
//	public String loginToFacebook(Model model) {
//		return facebookProvider.getFacebookUserData(model, new LocalUser());
//	}
//
//	@RequestMapping(value = "/google", method = RequestMethod.GET)
//	public String loginToGoogle(Model model) {
//		return googleProvider.getGoogleUserData(model, new LocalUser());
//	}
//
//	@RequestMapping(value = { "/login" })
//	public String login() {
//		return "login";
//	}
//
//
//	@RequestMapping(value = "/linkedin", method = RequestMethod.GET)
//	public String helloFacebook(Model model) {
//		return linkedInProvider.getLinkedInUserData(model, new LocalUser());
//	}

@RequestMapping(value = { "/course" }, method = RequestMethod.GET)
public String course() {
    return "course";
}



}
