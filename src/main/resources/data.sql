INSERT INTO company (name) VALUES ('Tech Innovations');
INSERT INTO company (name) VALUES ('Creative Solutions');
INSERT INTO company (name) VALUES ('NextGen Enterprises');

INSERT INTO department (name, company_id) VALUES ('Research & Development', 1);
INSERT INTO department (name, company_id) VALUES ('Marketing', 1);
INSERT INTO department (name, company_id) VALUES ('IT Services', 2);
INSERT INTO department (name, company_id) VALUES ('Sales', 2);
INSERT INTO department (name, company_id) VALUES ('Customer Support', 3);

INSERT INTO manager (name, surname, phone_number, email) VALUES ('John', 'Doe', '123-456-7890', 'jdoe@techinnovations.com');
INSERT INTO manager (name, surname, phone_number, email) VALUES ('Jane', 'Smith', '234-567-8901', 'jsmith@creativesolutions.com');
INSERT INTO manager (name, surname, phone_number, email) VALUES ('Robert', 'Brown', '345-678-9012', 'rbrown@nextgenenterprises.com');

INSERT INTO project (manager_id) VALUES (1);
INSERT INTO project (manager_id) VALUES (2);
INSERT INTO project (manager_id) VALUES (3);

INSERT INTO team (name, department_id, project_id) VALUES ('Alpha Team', 1, 1);
INSERT INTO team (name, department_id, project_id) VALUES ('Beta Team', 2, 2);
INSERT INTO team (name, department_id, project_id) VALUES ('Gamma Team', 3, 3);

