CREATE TABLE employee (
    employee_id BIGINT PRIMARY KEY,
    employee_name VARCHAR(255) NOT NULL,
    department VARCHAR(100),
    position VARCHAR(100),
    work_days VARCHAR(100),
    work_hours VARCHAR(100)
);

CREATE TABLE manager (
    manager_id BIGINT PRIMARY KEY,
    manager_name VARCHAR(255) NOT NULL
);

CREATE TABLE reporting_lines (
    employee_id BIGINT NOT NULL,
    manager_id BIGINT NOT NULL,
    PRIMARY KEY (employee_id, manager_id),
    CONSTRAINT fk_rl_employee
        FOREIGN KEY (employee_id)
        REFERENCES employee(employee_id),
    CONSTRAINT fk_rl_manager
        FOREIGN KEY (manager_id)
        REFERENCES manager(manager_id)
);

CREATE TABLE workday_type (
    workday_id BIGINT PRIMARY KEY,
    workday_name VARCHAR(100),
    factor_rate DECIMAL(10,2)
);

    
CREATE TABLE planned_overtime (
    po_id BIGINT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    workday_id BIGINT NOT NULL,
    manager_id BIGINT,
    ot_description VARCHAR(255),
    planned_hours INT NOT NULL,
    filling_date DATE NOT NULL,
    planned_date DATE NOT NULL,
    po_status VARCHAR(20) NOT NULL,
    
    CONSTRAINT fk_po_employee
        FOREIGN KEY (employee_id)
        REFERENCES employee(employee_id),
    CONSTRAINT fk_po_workday_type
        FOREIGN KEY (workday_id)
        REFERENCES workday_type(workday_id),
    CONSTRAINT fk_po_manager
        FOREIGN KEY (manager_id)
        REFERENCES manager(manager_id)
);

CREATE TABLE actual_overtime (
    ao_id BIGINT NOT NULL PRIMARY KEY,
    po_id BIGINT NOT NULL,
    manager_id BIGINT,
    ao_status VARCHAR(20) NOT NULL,
    ao_description VARCHAR(255),
    rendered_hours INT NOT NULL,
    earned_hours DECIMAL(10,2) NOT NULL,
    filling_date DATE NOT NULL,
    actual_date DATE NOT NULL,
    expiration_date DATE NOT NULL,
    remarks  VARCHAR(255) NOT NULL,
    
    CONSTRAINT fk_ao_planned_overtime
        FOREIGN KEY (po_id)
        REFERENCES planned_overtime(po_id),
    CONSTRAINT fk_ao_manager
        FOREIGN KEY (manager_id)
        REFERENCES manager(manager_id)
);


CREATE TABLE use_overtime (
    uo_id BIGINT NOT NULL PRIMARY KEY,
    manager_id BIGINT,
    uo_status VARCHAR(20) NOT NULL,
    use_hours INT NOT NULL,
    filling_date DATE NOT NULL,
    use_date DATE NOT NULL,
    remarks  VARCHAR(255) NOT NULL,
    CONSTRAINT fk_uo_manager
        FOREIGN KEY (manager_id)
        REFERENCES manager(manager_id)
);
