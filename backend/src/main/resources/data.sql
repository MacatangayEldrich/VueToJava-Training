INSERT INTO employee (employee_id, employee_name, department, position, work_days, work_hours) VALUES
(1, 'Ava Martinez', 'Engineering', 'Software Engineer', 'Monday-Friday', '09:00-17:00'),
(2, 'Liam Chen', 'Engineering', 'QA Engineer', 'Monday-Friday', '09:00-17:00'),
(3, 'Noah Patel', 'Engineering', 'Backend Developer', 'Monday-Friday', '09:00-17:00'),
(4, 'Mia Johnson', 'Engineering', 'Frontend Developer', 'Monday-Friday', '09:00-17:00'),
(5, 'Ethan Garcia', 'Human Resources', 'HR Specialist', 'Monday-Friday', '08:00-16:00'),
(6, 'Sophia Kim', 'Human Resources', 'Recruiter', 'Monday-Friday', '08:00-16:00'),
(7, 'Lucas Brown', 'Human Resources', 'Training Coordinator', 'Monday-Friday', '08:00-16:00'),
(8, 'Isabella Davis', 'Human Resources', 'Benefits Analyst', 'Monday-Friday', '08:00-16:00'),
(9, 'Oliver Wilson', 'Finance', 'Financial Analyst', 'Monday-Friday', '08:30-16:30'),
(10, 'Amelia Moore', 'Finance', 'Accountant', 'Monday-Friday', '08:30-16:30'),
(11, 'Elijah Taylor', 'Finance', 'Payroll Specialist', 'Monday-Friday', '08:30-16:30'),
(12, 'Harper Anderson', 'Finance', 'Budget Analyst', 'Monday-Friday', '08:30-16:30'),
(13, 'James Thomas', 'Sales', 'Account Executive', 'Monday-Friday', '09:00-17:00'),
(14, 'Evelyn Jackson', 'Sales', 'Sales Representative', 'Monday-Friday', '09:00-17:00'),
(15, 'Benjamin White', 'Sales', 'Sales Operations Analyst', 'Monday-Friday', '09:00-17:00'),
(16, 'Abigail Harris', 'Sales', 'Customer Success Associate', 'Monday-Friday', '09:00-17:00'),
(17, 'Henry Martin', 'Operations', 'Operations Analyst', 'Monday-Friday', '07:00-15:00'),
(18, 'Ella Thompson', 'Operations', 'Logistics Coordinator', 'Monday-Friday', '07:00-15:00'),
(19, 'Alexander Lee', 'Operations', 'Facilities Specialist', 'Monday-Friday', '07:00-15:00'),
(20, 'Scarlett Clark', 'Operations', 'Process Improvement Analyst', 'Monday-Friday', '07:00-15:00');

ALTER TABLE employee 
ALTER COLUMN employee_id RESTART WITH 21;

INSERT INTO manager (manager_id, manager_name) VALUES
(1, 'Grace Thompson'),
(2, 'Daniel Rodriguez'),
(3, 'Chloe Wilson'),
(4, 'Michael Lee'),
(5, 'Nora Williams');

ALTER TABLE manager 
ALTER COLUMN manager_id RESTART WITH 6;

INSERT INTO reporting_lines (employee_id, manager_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 4),
(2, 5),
(3, 1),
(3, 2),
(3, 3),
(4, 2),
(4, 3),
(4, 4),
(5, 2),
(5, 3),
(5, 4),
(6, 2),
(6, 3),
(6, 4),
(7, 2),
(7, 3),
(7, 4),
(8, 2),
(8, 3),
(8, 4),
(9, 3),
(9, 4),
(9, 5),
(10, 3),
(10, 4),
(10, 5),
(11, 4),
(11, 5),
(12, 3),
(12, 4),
(12, 5),
(13, 4),
(13, 5),
(14, 4),
(15, 4),
(16, 4),
(17, 5),
(18, 5),
(19, 5),
(20, 5);

INSERT INTO workday_type (workday_id, workday_name, factor_rate) VALUES
(1, 'Regular Day', 1.25),
(2, 'Special Working Day', 1.25),
(3, 'Rest Day', 1.30),
(4, 'Rest Day (>8)', 1.69),
(5, 'Special Non-working Day', 1.30),
(6, 'Special Non-working Day (>8)', 1.69),
(7, 'Regular Holiday', 2.00),
(8, 'Regular Holiday (>8)', 2.60);

ALTER TABLE workday_type 
ALTER COLUMN workday_id RESTART WITH 9;

INSERT INTO planned_overtime (po_id, employee_id, workday_id, manager_id, activity, planned_hours, filling_date, planned_date, status) VALUES
(1, 4, 1, 1, 'Release preparation support', 2, '2026-04-05', '2026-04-10', 'APPROVED'),
(2, 4, 2, 1, 'Regression test execution', 3, '2026-04-06', '2026-04-11', 'APPROVED'),
(3, 4, 3, 1, 'Database migration monitoring', 4, '2026-04-07', '2026-04-12', 'APPROVED'),
(4, 4, 4, 1, 'User interface launch support', 2, '2026-04-08', '2026-04-13', 'APPROVED'),
(5, 4, 5, 2, 'New hire onboarding session', 2, '2026-04-09', '2026-04-14', 'APPROVED'),
(6, 4, 6, 2, 'Interview schedule coordination', 3, '2026-04-10', '2026-04-15', 'APPROVED'),
(7, 4, 7, 2, 'Training materials preparation', 2, '2026-04-11', '2026-04-16',  'FOR APPROVAL'),
(8, 4, 8, 2, 'Benefits enrollment support', 3, '2026-04-12', '2026-04-17', 'APPROVED'),
(9, 4, 1, 3, 'Month-end reconciliation', 4, '2026-04-13', '2026-04-18', 'FOR APPROVAL'),
(10, 4, 1, 3, 'Quarterly account review', 3, '2026-04-14', '2026-04-19', 'FOR APPROVAL'),
(11, 11, 2, 3, 'Payroll system maintenance', 2, '2026-04-15', '2026-04-20', 'APPROVED'),
(12, 12, 3, 3, 'Annual budget consolidation', 4, '2026-04-16', '2026-04-21', 'APPROVED'),
(13, 13, 4, 4, 'Client proposal deadline support', 3, '2026-04-17', '2026-04-22', 'APPROVED'),
(14, 14, 5, 4, 'Customer campaign follow-up', 2, '2026-04-18', '2026-04-23', 'APPROVED'),
(15, 15, 6, 4, 'Sales reporting preparation', 3, '2026-04-19', '2026-04-24', 'APPROVED'),
(16, 16, 7, 4, 'Customer renewal assistance', 2, '2026-04-20', '2026-04-25', 'APPROVED'),
(17, 17, 8, 5, 'Warehouse inventory count', 4, '2026-04-21', '2026-04-26', 'APPROVED'),
(18, 18, 4, 5, 'Shipment delay coordination', 3, '2026-04-22', '2026-04-27', 'APPROVED'),
(19, 19, 6, 5, 'Facilities maintenance coverage', 2, '2026-04-23', '2026-04-28', 'APPROVED'),
(20, 20, 5, 5, 'Process audit preparation', 3, '2026-04-24', '2026-04-29', 'APPROVED');

ALTER TABLE planned_overtime 
ALTER COLUMN po_id RESTART WITH 21;

INSERT INTO actual_overtime (ao_id, po_id, manager_id, status, activity, rendered_hours, earned_hours, filling_date, actual_date, expiration_date, remarks) VALUES
(1, 1, 1, 'APPROVED', 'Completed release preparation', 2, 2.00, '2026-04-11', '2026-04-10', '2026-07-10', 'Verified by manager'),
(2, 2, 1, 'APPROVED', 'Completed regression testing', 3, 4.50, '2026-04-12', '2026-04-11', '2026-07-11', 'Test results recorded'),
(3, 3, 1, 'APPROVED', 'Monitored database migration', 4, 8.00, '2026-04-13', '2026-04-12', '2026-07-12', 'Migration completed'),
(4, 4, 1, 'APPROVED', 'Supported user interface launch', 2, 2.50, '2026-04-14', '2026-04-13', '2026-07-13', 'Launch support completed'),
(5, 5, 2, 'APPROVED', 'Delivered onboarding session', 2, 3.00, '2026-04-15', '2026-04-14', '2026-07-14', 'Attendance recorded'),
(6, 6, 2, 'APPROVED', 'Coordinated interview schedule', 3, 5.25, '2026-04-16', '2026-04-15', '2026-07-15', 'Interviews completed'),
(7, 8, 2, 'APPROVED', 'Supported benefits enrollment', 3, 5.25, '2026-04-18', '2026-04-17', '2026-07-17', 'Enrollment period supported'),
(8, 11, 3, 'APPROVED', 'Maintained payroll system', 2, 4.50, '2026-04-21', '2026-04-20', '2026-07-20', 'Maintenance completed'),
(9, 12, 3, 'APPROVED', 'Consolidated annual budget', 4, 10.00, '2026-04-22', '2026-04-21', '2026-07-21', 'Budget submitted'),
(10, 13, 4, 'APPROVED', 'Finalized client proposal', 3, 6.00, '2026-04-23', '2026-04-22', '2026-07-22', 'Proposal submitted'),
(11, 14, 4, 'APPROVED', 'Completed campaign follow-up', 2, 4.00, '2026-04-24', '2026-04-23', '2026-07-23', 'Customer responses logged'),
(12, 15, 4, 'APPROVED', 'Prepared sales reports', 3, 3.75, '2026-04-25', '2026-04-24', '2026-07-24', 'Reports published'),
(13, 16, 4, 'APPROVED', 'Assisted customer renewals', 2, 3.50, '2026-04-26', '2026-04-25', '2026-07-25', 'Renewal requests handled'),
(14, 17, 5, 'APPROVED', 'Completed warehouse inventory', 4, 8.00, '2026-04-27', '2026-04-26', '2026-07-26', 'Inventory count confirmed'),
(15, 18, 5, 'APPROVED', 'Coordinated delayed shipments', 3, 5.25, '2026-04-28', '2026-04-27', '2026-07-27', 'Shipments rescheduled'),
(16, 19, 5, 'APPROVED', 'Covered facilities maintenance', 2, 4.00, '2026-04-29', '2026-04-28', '2026-07-28', 'Maintenance requests closed'),
(17, 20, 5, 'APPROVED', 'Prepared process audit', 3, 5.25, '2026-04-30', '2026-04-29', '2026-07-29', 'Audit documents prepared');

ALTER TABLE actual_overtime 
ALTER COLUMN ao_id RESTART WITH 18;

INSERT INTO use_overtime (uo_id, employee_id, manager_id, status, use_hours, filling_date, use_date, remarks) VALUES
(1,4, 1, 'APPROVED', 2, '2025-02-01', '2025-02-07', 'Personal appointment'),
(2,4, 1, 'APPROVED', 1, '2025-02-02', '2025-02-08', 'Medical appointment'),
(3,4, 1, 'APPROVED', 3, '2025-02-03', '2025-02-09', 'Family commitment'),
(4,4, 1, 'APPROVED', 2, '2025-02-04', '2025-02-10', 'Home maintenance'),
(5,4, 2, 'APPROVED', 4, '2025-02-05', '2025-02-11', 'Rest after extended shift'),
(6,4, 2, 'APPROVED', 1, '2025-02-06', '2025-02-12', 'School meeting'),
(7,4, 2, 'APPROVED', 2, '2025-02-07', '2025-02-13', 'Personal errands'),
(8,4, 2, 'APPROVED', 3, '2025-02-08', '2025-02-14', 'Family event'),
(9,4, 3, 'APPROVED', 2, '2025-02-09', '2025-02-15', 'Personal appointment'),
(10,4, 3, 'APPROVED', 1, '2025-02-10', '2025-02-16', 'Health appointment'),
(11,4, 3, 'APPROVED', 4, '2025-02-11', '2025-02-17', 'Recovery following closing week'),
(12,4, 3, 'APPROVED', 2, '2025-02-12', '2025-02-18', 'Family commitment'),
(13,13, 4, 'APPROVED', 3, '2025-02-13', '2025-02-19', 'Personal day'),
(14,14, 4, 'APPROVED', 2, '2025-02-14', '2025-02-20', 'Medical appointment'),
(15,13, 4, 'APPROVED', 1, '2025-02-15', '2025-02-21', 'School event'),
(16,14, 4, 'APPROVED', 4, '2025-02-16', '2025-02-22', 'Family commitment'),
(17,17, 5, 'APPROVED', 2, '2025-02-17', '2025-02-23', 'Personal appointment'),
(18,18, 5, 'APPROVED', 3, '2025-02-18', '2025-02-24', 'Home maintenance'),
(19,19, 5, 'APPROVED', 1, '2025-02-19', '2025-02-25', 'Personal errands'),
(20,17, 5, 'APPROVED', 2, '2025-02-20', '2025-02-26', 'Family event');

ALTER TABLE use_overtime 
ALTER COLUMN uo_id RESTART WITH 21;
