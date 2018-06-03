package org.wildcards.demo.project.scheduler.application.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.wildcards.demo.project.scheduler.domain.entities.Project;
import org.wildcards.demo.project.scheduler.domain.repositories.ProjectRepository;

/**
 * 
 * @author user
 *
 */
@Controller
public class ViewController {

  /**
   * 
   */
  private final ProjectRepository projectRepository;
  
  /**
   * 
   */
  public ViewController(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }
  /**
   * 
   * @return
   */
  @GetMapping("/")
  public String home(Model model) {
    
    List<Project> projects = StreamSupport
        .stream(this.projectRepository.findAll().spliterator(), true)
        .map(p -> p)
        .collect(Collectors.toList());
    
    model.addAttribute("projects", projects);
    
    return "viewProjects";
  }
  
}
