package km.exam9.forum.util;

import km.exam9.forum.model.Comment;
import km.exam9.forum.model.Theme;
import km.exam9.forum.model.User;
import km.exam9.forum.repository.CommentRepository;
import km.exam9.forum.repository.ThemeRepository;
import km.exam9.forum.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Configuration
public class DBPreload {
    private Random r = new Random();

    @Bean
    CommandLineRunner generateGibberish(UserRepository userRepo, ThemeRepository themeRepo, CommentRepository commentRepo) {
        return args -> {
            userRepo.deleteAll();
            themeRepo.deleteAll();
            commentRepo.deleteAll();

            //Users creating
            var users = Stream.generate(User::make).limit(5).collect(toList());
            var myUser = User.builder()
                    .login("kad")
                    .email("kad@gmail.com")
                    .password(new BCryptPasswordEncoder().encode("zlobbi"))
                    .build();
            users.add(myUser);

            //Themes creating
            int i = 0;
            List<Theme> themes = new ArrayList<>();
            while (i < 30) {
                var u = users.get(r.nextInt(users.size()));
                var t = Theme.make(u);
                themes.add(t);
                i++;
            }
            //Comments creating
            i = 0;
            List<Comment> comments = new ArrayList<>();
            while (i < 50) {
                var u = users.get(r.nextInt(users.size()));
                u.plusComment();
                users.add(u);
                var t = themes.get(r.nextInt(themes.size()));
                var c = Comment.make(u, t);
                t.plusComent();
                themes.add(t);
                comments.add(c);
                i++;
            }
            userRepo.saveAll(users);
            themeRepo.saveAll(themes);
            commentRepo.saveAll(comments);

        };
    }
}
