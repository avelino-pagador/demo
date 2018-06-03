
/**
 * 
 */
$(document).ready(function() {
  console.log("hehehe");
  
  
  $("#addProject").click(function(e) {
    var project = {};
    project.projectName = $("#projectName").val();
    project.projectDescription = $("#projectDescription").val();
    
    if ("" === project.projectName || "" === project.projectDescription) {
      alert("Please fillup both project and description.")
      return;
    }
    
    var response = confirm("Add project?");
    if (response === true) {
      $.ajax({
        url: "/projects",
        method: "POST",
        data: JSON.stringify(project),
        contentType: "application/json;charset=UTF-8"
      }).done(function() {
        window.location.reload();
      }).fail(function() {
        alert("Error encountered while adding new project.")
      });
    } else {
       $("#projectName").val("");
       $("#projectDescription").val("");
    }
    
  });
  
  /**
   * 
   */
  $("#clearProject").on("click", function(e) {
    $("#projectName").val("");
    $("#projectDescription").val("");
  });
  
  
  /**
   * 
   */
  $(".generate-schedule").on("click", function(e) {
    var response = confirm("Generate project schedule?");
      var request = {"action" : "GENERATE"}
      var projectId = $(this).attr("projectid");
        if (response === true) {
            $.ajax({
              url: "/projects/" + projectId,
              method: "POST",
              data: JSON.stringify(request),
              contentType: "application/json;charset=UTF-8"
            }).done(function() {
              window.location.reload();
            }).fail(function(error) {
            	console.log(error);
            	console.log(error.responseJSON.message);
              alert("Error: " + error.responseJSON.message);
            });
          } else {
             $("#projectName").val("");
             $("#projectDescription").val("");
          }
  });
  
  /**
   * 
   */
  $(".reset-schedule").on("click", function(e) {
    var response = confirm("Reset project schedule?");
    var request = {"action" : "RESET"};
    var projectId = $(this).attr("projectid");
    if (response === true) {
      $.ajax({
        url: "/projects/" + projectId,
        method: "POST",
        data: JSON.stringify(request),
        contentType: "application/json;charset=UTF-8"
      }).done(function() {
        window.location.reload();
      }).fail(function() {
        alert("Error encountered while resetting project schedule.")
      });
    } else {
       $("#projectName").val("");
       $("#projectDescription").val("");
    }
  });
  
  
  /**
   * 
   */
  $(".delete-project").on("click", function(e) {
    var projectId = $(this).attr("projectid");
    console.log(projectId);
    var response = confirm("Delete project?");
    if (response === true) {
        $.ajax({
          url: "/projects/" + projectId,
          method: "DELETE",
        }).done(function() {
          $("#section" + projectId).remove();
        }).fail(function() {
          alert("Error encountered while adding new project.")
        });
      } else {
         $("#projectName").val("");
         $("#projectDescription").val("");
      }
  })
  
  /**
   * 
   */
  $(".delete-task").on("click", function(e) {
    var projectId = $(this).attr("projectid");
    var taskId = $(this).attr("taskid");
    var response = confirm("Delete task?");
    if (response === true) {
        $.ajax({
          url: "/projects/" + projectId + "/tasks/" + taskId,
          method: "DELETE",
        }).done(function() {
          $("#task-row-" + projectId + "-" + taskId).remove();
        }).fail(function() {
          alert("Error encountered while adding new project.")
        });
      } else {
         $("#projectName").val("");
         $("#projectDescription").val("");
      }
  })
  
  /**
   * 
   */
   $(".show-add-task").on("click", function(e) {
    var projectId = $(this).attr("projectid");
    $("#show-add-task-" + projectId).prop('disabled', true);
    $("#task-name-" + projectId).val("");
    $("#task-duration-" + projectId).val("");
    $("#task-dependencies-" + projectId).val("");
    $("#add-task-" + projectId).show();
    $("#task-name-" + projectId).focus();
    
    $("#action").val('add');
    $("#taskid").val(0);
    
  })
  
  /**
   * 
   */
   $(".save-add-task").on("click", function(e) {
    var projectId = $(this).attr("projectid");
    var taskName = $("#task-name-" + projectId).val();
    var taskDuration = $("#task-duration-" + projectId).val();
    var taskDependencies =  $("#task-dependencies-" + projectId).val();
    if ("" === taskName) {
      alert("Please fillup task name.")
      return;
    }
    
    if ("" === taskDuration || 1 > taskDuration) {
      alert("Please specify duration value greater than 0.")
      return;
    }
    
    var taskId = $("#taskid").val();
    var action = $("#action").val();
    var task = {};
    task.taskName = taskName;
    task.duration = taskDuration
    task.dependencies = taskDependencies;
    
    if ("edit" === action) {
    	var response = confirm("Update task?");
        if (response === true) {
          task.id = taskId;
          $.ajax({
            url: "/projects/" + projectId + "/tasks/" + taskId,
            method: "PUT",
            data: JSON.stringify(task),
            contentType: "application/json;charset=UTF-8"
          }).done(function() {
            window.location.reload();
          }).fail(function() {
            alert("Error encountered while adding new project.")
          });
        }  
    } else {
    	var response = confirm("Add new task?");
        if (response === true) {
          $.ajax({
            url: "/projects/" + projectId + "/tasks",
            method: "POST",
            data: JSON.stringify(task),
            contentType: "application/json;charset=UTF-8"
          }).done(function() {
            window.location.reload();
          }).fail(function() {
            alert("Error encountered while adding new project.")
          });
        }  
    }
    
    
    
  })
  
  /**
   * 
   */
   $(".hide-add-task").on("click", function(e) {
    var projectId = $(this).attr("projectid");
    var taskId = $("#taskid").val();
    var action = $("#action").val();
    console.log(action);
    console.log(taskId);
    
    $("#add-task-" + projectId).hide();
    $("#show-add-task-" + projectId).prop('disabled', false);
    
//    if ("edit" === action) {
//      $("#task-row-" + projectId + "-" + taskId).css('background-color', '');    	
//    }

  })
  
  /**
   * 
   */
   $(".reset-add-task").on("click", function(e) {
    var projectId = $(this).attr("projectid");
    var taskId = $("#taskid").val();
    var action = $("#action").val();
    if ("edit" === action) {
    	var taskName = $("#task-taskname-" + projectId + "-" + taskId).text();
        var duration = $("#task-duration-" + projectId + "-" + taskId).text();
        var dependencies = JSON.parse($("#task-dependencies-" + projectId + "-" + taskId).attr("dependencies"));
        var deps = [];
        $.each(dependencies, function() {
        	console.log(this);
        	deps.push(this.id);
        });
        $("#task-name-" + projectId).val(taskName);
        $("#task-duration-" + projectId).val(duration);
        $("#task-dependencies-" + projectId).val(deps);
        
    } else {
    	$("#task-name-" + projectId).val("");
        $("#task-duration-" + projectId).val("");
        $("#task-dependencies-" + projectId).val("");
    }
    
    $("#task-name-" + projectId).focus();
    
  })
  
  
  /**
   * 
   */
   $(".edit-task").on("click", function(e) {
    var projectId = $(this).attr("projectid");
    var taskId = $(this).attr("taskid");
    var taskName = $("#task-taskname-" + projectId + "-" + taskId).text();
    var duration = $("#task-duration-" + projectId + "-" + taskId).text();
    var dependencies = JSON.parse($("#task-dependencies-" + projectId + "-" + taskId).attr("dependencies"));
    var deps = [];
    $.each(dependencies, function() {
    	console.log(this);
    	deps.push(this.id);
    });
    
    
    console.log(deps);
    
    $("#show-add-task-" + projectId).prop('disabled', true);
    
    $("#action").val('edit');
    $("#taskid").val(taskId);
    
//    $("#task-row-" + projectId + "-" + taskId).css('background-color', '#FFCC00');
    $("#task-name-" + projectId).val(taskName);
    $("#task-duration-" + projectId).val(duration);
    $("#task-dependencies-" + projectId).val(deps);
    $("#add-task-" + projectId).show();
    $("#task-name-" + projectId).focus();
    
    
  })
});