package km.exam9.forum.controller;

import km.exam9.forum.DTO.CaptchaResponseDTO;
import km.exam9.forum.forms.ThemeForm;
import km.exam9.forum.forms.UserRegisterForm;
import km.exam9.forum.service.ThemeService;
import km.exam9.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;

@Controller
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/register")
    public String userRegisterPage(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new UserRegisterForm());
        }
        return "/register";
    }

    @RequestMapping("/register")
    public String register(@Valid UserRegisterForm form, @RequestParam("g-recaptcha-response") String captcha,
                           BindingResult validationResult,
                           RedirectAttributes attributes) {
        attributes.addFlashAttribute("form");
        String url = String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", secret, captcha);
        CaptchaResponseDTO responseDTO = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
        }
        if (service.checkUser(form)) {
            attributes.addFlashAttribute("user", form);
        }
        if (!responseDTO.isSuccess()) {
            attributes.addFlashAttribute("captchaError", "captcha error");
        }
        if (validationResult.hasFieldErrors() || service.checkUser(form) || !responseDTO.isSuccess()) {
            return "redirect:/register";
        }
        service.register(form);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal, Pageable pageable,
                                      HttpServletRequest uriBuilder) {
        var user = service.getByEmail(principal.getName());
        var themes = themeService.getUserThemes(user, pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(themes, 1, model, uri,"themes");
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/theme/add")
    public String addTheme(Model model, Principal principal) {
        if(principal.getName() != null) {
            model.addAttribute("user", service.getUserByEmail(principal.getName()));
        }
        model.addAttribute("form", new ThemeForm());
        return "newtheme";
    }

    @RequestMapping("/theme/add")
    public String addTheme(ThemeForm form, Principal principal, @RequestParam("g-recaptcha-response") String captcha,
                           BindingResult bindingResult, RedirectAttributes attributes) {
        attributes.addFlashAttribute("form");
        String url = String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", secret, captcha);
        CaptchaResponseDTO responseDTO = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);
        if (bindingResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", bindingResult.getFieldErrors());
        }
        if (!responseDTO.isSuccess()) {
            attributes.addFlashAttribute("captchaError", "captcha error");
        }
        if (bindingResult.hasFieldErrors() || !responseDTO.isSuccess()) {
            return "redirect:/theme/add";
        }

        themeService.saveTheme(form, service.getUserByEmail(principal.getName()));
        return "redirect:/";
    }


    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri, String content) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute(content, list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

}
