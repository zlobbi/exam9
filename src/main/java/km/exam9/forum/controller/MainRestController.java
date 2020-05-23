package km.exam9.forum.controller;

import km.exam9.forum.DTO.CommentDTO;
import km.exam9.forum.model.Comment;
import km.exam9.forum.service.CommentService;
import km.exam9.forum.service.ThemeService;
import km.exam9.forum.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class MainRestController {
    private final CommentService commentService;
    private final ThemeService themeService;
    private final UserService userService;

    @PostMapping("/comment")
    public CommentDTO comment(@RequestParam Map<String, String> map, Principal principal) {
        var user = userService.getUserByEmail(principal.getName());
        user.plusComment();
        userService.saveUser(user);
        var theme = themeService.getTheme(map.get("themeId"));
        var c = Comment.make(user, theme, map.get("comment"));
        commentService.saveComment(c);
        return CommentDTO.from(c);
    }

//    @GetMapping("/{id:\\d+}/foods")
//    public List<FoodDTO> getFoods(@PathVariable @Min(5) int id, Pageable pageable) {
//        return foodService.getFoods(id, pageable).getContent();
//    }
}
