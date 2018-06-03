INSERT INTO project (project_name, project_description) values ('My First Project', 'My first test project');
INSERT INTO project (project_name, project_description) values ('My Second Project', 'My second test project');
INSERT INTO project (project_name, project_description) values ('My Third Project', 'My third test project');
INSERT INTO project (project_name, project_description) values ('My Fourth Project', 'My fourth test project');
INSERT INTO project (project_name, project_description) values ('My Fifth Project', 'My fifth test project');


INSERT INTO task (task_name, duration) values('Task 1', 1);
INSERT INTO task (task_name, duration) values('Task 2', 2);
INSERT INTO task (task_name, duration) values('Task 3', 3);
INSERT INTO task (task_name, duration) values('Task 4', 4);
INSERT INTO task (task_name, duration) values('Task 5', 5);
INSERT INTO task (task_name, duration) values('Task 6', 6);
INSERT INTO task (task_name, duration) values('Task 7', 7);
INSERT INTO task (task_name, duration) values('Task 8', 8);
INSERT INTO task (task_name, duration) values('Task 9', 9);
INSERT INTO task (task_name, duration) values('Task 10', 10);


INSERT INTO project_tasks(project_id, tasks_id) values(1,1);
INSERT INTO project_tasks(project_id, tasks_id) values(1,2);
INSERT INTO project_tasks(project_id, tasks_id) values(1,3);
INSERT INTO project_tasks(project_id, tasks_id) values(1,4);
INSERT INTO project_tasks(project_id, tasks_id) values(1,5);
INSERT INTO project_tasks(project_id, tasks_id) values(1,6);
INSERT INTO project_tasks(project_id, tasks_id) values(1,7);
INSERT INTO project_tasks(project_id, tasks_id) values(1,8);
INSERT INTO project_tasks(project_id, tasks_id) values(1,9);
INSERT INTO project_tasks(project_id, tasks_id) values(1,10);


INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (10, 9);
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (9, 8);
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (8, 7);
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (7, 6);
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (6, 5);
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (5, 4);
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (4, 3);
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (3, 2);
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (2, 1);
/*
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (1, 2);
*/
/*
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (1, 5);
*/
/*
INSERT INTO task_dependencies (task_id, dependencies_id) VALUES (4, 5);
*/

