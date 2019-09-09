package com.example.controller;

import com.example.model.Issue;
import com.example.repository.IssueRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class IssueController {

    private final  IssueRepository issueRepository;

    public IssueController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @GetMapping("/issue")
    public String issue(Model model){
        model.addAttribute("issue", new Issue());
        return "issue";
    }

    @GetMapping("/issues")
    public String issuesList(Model model){
        model.addAttribute("issues", issueRepository.findAll());
        return "issues";
    }

    @PostMapping("/issues")
    public String updateDescription(String[] data){
        String description = data[0];
        int id = Integer.parseInt(data[1]);
        Issue issue = issueRepository.getOne(id);
        issue.setUpdate_date(new Date());
        issue.setDescription(description);
        issueRepository.save(issue);
        return "redirect:/issues";
    }

    @PostMapping("/issue")
    public String createIssue(@ModelAttribute Issue issue){
        issue.setCreate_date(new Date());
        issueRepository.save(issue);
        return "redirect:/issues";
    }
}
