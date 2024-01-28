package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.tag.ModifyTag;
import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import com.nhnacademy.edu.minidooray.domain.tag.GetTag;
import com.nhnacademy.edu.minidooray.exception.ValidationFailedException;
import com.nhnacademy.edu.minidooray.service.TagService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    private final static String REDIRECT_URL = "redirect:/tags/list";

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public String getTags(@SessionAttribute("projectId") Long projectId,
                          Model model) {
        List<GetTag> tags = tagService.getTags(projectId);
        model.addAttribute("tags", tags);

        return "tagList";
    }

    @PostMapping("/register")
    public String registerTag(@Valid @ModelAttribute RegisterTag tag,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        tagService.registerTag(tag);

        return REDIRECT_URL;
    }

    @GetMapping("/{tag_id}/modify")
    public String tagModifyForm(@PathVariable("tag_id") Long tagId,
                                @RequestParam("tagName") String tagName,
                                Model model) {

        model.addAttribute("tagId", tagId);
        model.addAttribute("tagName", tagName);

        return "tagModifyForm";
    }

    @PostMapping("/{tag_id}/modify")
    public String modifyTag(@PathVariable("tag_id") Long tagId,
                            @Valid  @ModelAttribute ModifyTag tag,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        tagService.modifyTag(tagId, tag);

        return REDIRECT_URL;
    }

    @GetMapping("/{tag_id}/delete")
    public String deleteTag(@PathVariable("tag_id") Long tagId) {
        tagService.deleteTag(tagId);

        return REDIRECT_URL;
    }
}
