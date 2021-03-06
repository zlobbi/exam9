package km.exam9.forum.controller;

import km.exam9.forum.annotations.ApiPageable;
import km.exam9.forum.service.CommentService;
import km.exam9.forum.service.ThemeService;
import km.exam9.forum.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MainController {
    private final UserService userService;
    private final ThemeService themeService;
    private final CommentService commentService;

    @GetMapping
    @ApiPageable
    public String root(Model model, @ApiIgnore Pageable pageable,
                       HttpServletRequest uriBuilder, Principal principal) {
        var themes = themeService.getAll(pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(themes, 4 , model, uri, "themes");
        if(uriBuilder.getUserPrincipal() != null) {
            var user = userService.getByEmail(uriBuilder.getUserPrincipal().getName());
            model.addAttribute("user", user);
        }
        return "index";
    }

    @GetMapping("/theme/{id}")
    public String theme(Model model, Pageable pageable, HttpServletRequest uriBuilder,
                        @PathVariable("id") String themeId, Principal principal) {
        model.addAttribute("theme", themeService.getById(themeId));
        if(principal.getName() != null) {
            model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        }
        var uri = uriBuilder.getRequestURI();
        var comments = commentService.getThemeComments(pageable, themeId);
        constructPageable(comments, 4, model, uri, "comments");
        return "theme";
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
