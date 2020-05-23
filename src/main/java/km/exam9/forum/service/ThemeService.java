package km.exam9.forum.service;

import km.exam9.forum.DTO.ThemeDTO;
import km.exam9.forum.DTO.UserDTO;
import km.exam9.forum.forms.ThemeForm;
import km.exam9.forum.model.Theme;
import km.exam9.forum.model.User;
import km.exam9.forum.repository.ThemeRepository;
import km.exam9.forum.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ThemeService {
    private final ThemeRepository repository;
    private final UserRepository userRepository;

    public Page<ThemeDTO> getAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 10, Sort.by("time").descending());
        return repository.findAll(pageable).map(ThemeDTO::from);
    }

    public ThemeDTO getById(String themeId) {
        var t = repository.findById(themeId).get();
        return ThemeDTO.from(t);
    }

    public void saveTheme(ThemeForm form, User user) {
        var t = Theme.from(form, user);
        repository.save(t);
    }

    public Theme getTheme(String id) {
        var t = repository.findById(id).get();
        t.plusComent();
        repository.save(t);
        return t;
    }

    public Page<ThemeDTO> getUserThemes(UserDTO user, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 3, Sort.by("time").descending());
        return repository.findAllByUser(user.getId(), pageable).map(ThemeDTO::from);
    }
}
