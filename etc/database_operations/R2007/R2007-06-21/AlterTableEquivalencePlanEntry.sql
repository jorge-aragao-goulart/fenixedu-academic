alter table EQUIVALENCE_PLAN_ENTRY add column ECTS_CREDITS double NULL;
alter table EQUIVALENCE_PLAN_ENTRY change NEW_CURRICULAR_COURSES_OPERATOR NEW_DEGREE_MODULES_OPERATOR varchar(255) null;
