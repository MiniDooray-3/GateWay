package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import com.nhnacademy.edu.minidooray.domain.tag.TagRequest;
import com.nhnacademy.edu.minidooray.service.TagService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public String getTags(@SessionAttribute("project_id") Long projectId,
                          Model model) {
        List<TagRequest> tags = tagService.getTags(projectId);
        model.addAttribute("tags", tags);

        return "tagList";
    }

    @GetMapping("/register")
    public String tagRegisterForm(@SessionAttribute("projectId") Long projectId,
                                  Model model) {
        model.addAttribute("projectId", projectId);

        return "tagRegisterForm";
    }

    @PostMapping("/register")
    public String registerTag(@ModelAttribute RegisterTag tag) {
        tagService.registerTag(tag);

        return "redirect:/tags/list";
    }

    @GetMapping("/{tag_id}/modify")
    public String tagModifyForm(@PathVariable("tag_id") Long tagId,
                                Model model) {
        TagRequest tag = tagService.getTag(tagId);
        model.addAttribute("tagId", tagId);
        model.addAttribute("tagName", tag.getTageName());

        return "tagModifyForm";
    }

    @PostMapping("/{tag_id}/modify")
    public String modifyTag(@PathVariable("tag_id") Long tagId,
                            @ModelAttribute TagRequest tag) {
        tagService.modifyTag(tagId, tag);

        return "redirect:/tags/list";
    }

    @GetMapping("/{tag_id}/delete")
    public String deleteTag(@PathVariable("tag_id") Long tagId) {
        tagService.deleteTag(tagId);

        return "redirect:/tags/list";
    }
}
